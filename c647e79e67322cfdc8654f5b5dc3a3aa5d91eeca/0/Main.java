/*
 * MurderMystery - Find the murderer, kill him and survive!
 * Copyright (C) 2020  Plugily Projects - maintained by Tigerpanzer_02, 2Wild4You and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package plugily.projects.murdermystery;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import me.tigerhix.lib.scoreboard.ScoreboardLib;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import plugily.projects.murdermystery.api.StatsStorage;
import plugily.projects.murdermystery.arena.Arena;
import plugily.projects.murdermystery.arena.ArenaEvents;
import plugily.projects.murdermystery.arena.ArenaRegistry;
import plugily.projects.murdermystery.arena.ArenaUtils;
import plugily.projects.murdermystery.arena.special.SpecialBlockEvents;
import plugily.projects.murdermystery.arena.special.mysterypotion.MysteryPotionRegistry;
import plugily.projects.murdermystery.arena.special.pray.PrayerRegistry;
import plugily.projects.murdermystery.commands.arguments.ArgumentsRegistry;
import plugily.projects.murdermystery.events.ChatEvents;
import plugily.projects.murdermystery.events.Events;
import plugily.projects.murdermystery.events.JoinEvent;
import plugily.projects.murdermystery.events.LobbyEvent;
import plugily.projects.murdermystery.events.QuitEvent;
import plugily.projects.murdermystery.events.spectator.SpectatorEvents;
import plugily.projects.murdermystery.events.spectator.SpectatorItemEvents;
import plugily.projects.murdermystery.handlers.BowTrailsHandler;
import plugily.projects.murdermystery.handlers.BungeeManager;
import plugily.projects.murdermystery.handlers.ChatManager;
import plugily.projects.murdermystery.handlers.CorpseHandler;
import plugily.projects.murdermystery.handlers.PermissionsManager;
import plugily.projects.murdermystery.handlers.PlaceholderManager;
import plugily.projects.murdermystery.handlers.items.SpecialItem;
import plugily.projects.murdermystery.handlers.language.LanguageManager;
import plugily.projects.murdermystery.handlers.party.PartyHandler;
import plugily.projects.murdermystery.handlers.party.PartySupportInitializer;
import plugily.projects.murdermystery.handlers.rewards.RewardsFactory;
import plugily.projects.murdermystery.handlers.sign.ArenaSign;
import plugily.projects.murdermystery.handlers.sign.SignManager;
import plugily.projects.murdermystery.user.User;
import plugily.projects.murdermystery.user.UserManager;
import plugily.projects.murdermystery.user.data.MysqlManager;
import plugily.projects.murdermystery.utils.Debugger;
import plugily.projects.murdermystery.utils.ExceptionLogHandler;
import plugily.projects.murdermystery.utils.MessageUtils;
import plugily.projects.murdermystery.utils.UpdateChecker;
import plugily.projects.murdermystery.utils.Utils;
import plugily.projects.murdermystery.utils.services.ServiceRegistry;
import pl.plajerlair.commonsbox.database.MysqlDatabase;
import pl.plajerlair.commonsbox.minecraft.configuration.ConfigUtils;
import pl.plajerlair.commonsbox.minecraft.serialization.InventorySerializer;

/**
 * @author Plajer
 * <p>
 * Created at 03.08.2018
 */
public class Main extends JavaPlugin {

  private ExceptionLogHandler exceptionLogHandler;
  private String version;
  private boolean forceDisable = false;
  private BungeeManager bungeeManager;
  private RewardsFactory rewardsHandler;
  private MysqlDatabase database;
  private SignManager signManager;
  private CorpseHandler corpseHandler;
  private PartyHandler partyHandler;
  private ConfigPreferences configPreferences;
  private ArgumentsRegistry argumentsRegistry;
  private HookManager hookManager;
  private UserManager userManager;

  @Override
  public void onEnable() {
    if (!validateIfPluginShouldStart()) {
      return;
    }

    long start = System.currentTimeMillis();

    ServiceRegistry.registerService(this);
    exceptionLogHandler = new ExceptionLogHandler(this);
    LanguageManager.init(this);
    saveDefaultConfig();

    Debugger.setEnabled(getDescription().getVersion().contains("b") ? true : getConfig().getBoolean("Debug", false));

    Debugger.debug(Level.INFO, "[System] Initialization start");
    if (getConfig().getBoolean("Developer-Mode", false)) {
      Debugger.deepDebug(true);
      Debugger.debug(Level.FINE, "Deep debug enabled");
      for (String listenable : new ArrayList<>(getConfig().getStringList("Performance-Listenable"))) {
        Debugger.monitorPerformance(listenable);
      }
    }

    configPreferences = new ConfigPreferences(this);
    setupFiles();
    initializeClasses();
    checkUpdate();
    Debugger.debug(Level.INFO, "[System] Initialization finished took {0}ms", System.currentTimeMillis() - start);

    Debugger.debug(Level.INFO, "Plugin loaded! Hooking into soft-dependencies in a while!");
    //start hook manager later in order to allow soft-dependencies to fully load
    Bukkit.getScheduler().runTaskLater(this, () -> hookManager = new HookManager(), 20L * 5);
    if (configPreferences.getOption(ConfigPreferences.Option.NAMETAGS_HIDDEN)) {
      Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
        for (Player player : Bukkit.getOnlinePlayers()) {
          ArenaUtils.updateNameTagsVisibility(player);
        }
      }, 60, 140);
    }
  }

  private boolean validateIfPluginShouldStart() {
    version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    if (!(version.equalsIgnoreCase("v1_12_R1") || version.equalsIgnoreCase("v1_13_R1")
      || version.equalsIgnoreCase("v1_13_R2") || version.equalsIgnoreCase("v1_14_R1") || version.equalsIgnoreCase("v1_15_R1") || version.equalsIgnoreCase("v1_16_R1"))) {
      MessageUtils.thisVersionIsNotSupported();
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Your server version is not supported by Murder Mystery!");
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Sadly, we must shut off. Maybe you consider changing your server version?");
      forceDisable = true;
      getServer().getPluginManager().disablePlugin(this);
      return false;
    }
    try {
      Class.forName("org.spigotmc.SpigotConfig");
    } catch (Exception e) {
      MessageUtils.thisVersionIsNotSupported();
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Your server software is not supported by Murder Mystery!");
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "We support only Spigot and Spigot forks only! Shutting off...");
      forceDisable = true;
      getServer().getPluginManager().disablePlugin(this);
      return false;
    }
    return true;
  }

  @Override
  public void onDisable() {
    if (forceDisable) {
      return;
    }
    Debugger.debug(Level.INFO, "System disable initialized");
    long start = System.currentTimeMillis();

    Bukkit.getLogger().removeHandler(exceptionLogHandler);
    saveAllUserStatistics();
    if (hookManager != null && hookManager.isFeatureEnabled(HookManager.HookFeature.CORPSES)) {
      HologramsAPI.getHolograms(this).forEach(Hologram::delete);
    }
    if (configPreferences.getOption(ConfigPreferences.Option.DATABASE_ENABLED)) {
      getMysqlDatabase().shutdownConnPool();
    }

    for (Arena arena : ArenaRegistry.getArenas()) {
      arena.getScoreboardManager().stopAllScoreboards();
      for (Player player : arena.getPlayers()) {
        arena.doBarAction(Arena.BarAction.REMOVE, player);
        arena.teleportToEndLocation(player);
        player.setFlySpeed(0.1f);
        if (configPreferences.getOption(ConfigPreferences.Option.INVENTORY_MANAGER_ENABLED)) {
          InventorySerializer.loadInventory(this, player);
        } else {
          player.getInventory().clear();
          player.getInventory().setArmorContents(null);
          player.getActivePotionEffects().forEach(pe -> player.removePotionEffect(pe.getType()));
          player.setWalkSpeed(0.2f);
        }
      }
      arena.teleportAllToEndLocation();
      arena.cleanUpArena();
    }
    Debugger.debug(Level.INFO, "System disable finished took {0}ms", System.currentTimeMillis() - start);
  }

  private void initializeClasses() {
    ScoreboardLib.setPluginInstance(this);
    if (getConfig().getBoolean("BungeeActivated", false)) {
      bungeeManager = new BungeeManager(this);
    }
    if (configPreferences.getOption(ConfigPreferences.Option.DATABASE_ENABLED)) {
      FileConfiguration config = ConfigUtils.getConfig(this, "mysql");
      database = new MysqlDatabase(config.getString("user"), config.getString("password"), config.getString("address"));
    }
    argumentsRegistry = new ArgumentsRegistry(this);
    userManager = new UserManager(this);
    Utils.init(this);
    ArenaSign.init(this);
    SpecialItem.loadAll();
    PermissionsManager.init();
    new ChatManager(ChatManager.colorMessage("In-Game.Plugin-Prefix"), this);
    new ArenaEvents(this);
    new SpectatorEvents(this);
    new QuitEvent(this);
    new JoinEvent(this);
    new ChatEvents(this);
    registerSoftDependenciesAndServices();
    User.cooldownHandlerTask();
    ArenaRegistry.registerArenas();
    new Events(this);
    new LobbyEvent(this);
    new SpectatorItemEvents(this);
    rewardsHandler = new RewardsFactory(this);
    signManager = new SignManager(this);
    corpseHandler = new CorpseHandler(this);
    partyHandler = new PartySupportInitializer().initialize(this);
    new BowTrailsHandler(this);
    MysteryPotionRegistry.init(this);
    PrayerRegistry.init(this);
    new SpecialBlockEvents(this);
  }

  private void registerSoftDependenciesAndServices() {
    Debugger.debug(Level.INFO, "Hooking into soft dependencies");
    long start = System.currentTimeMillis();

    startPluginMetrics();
    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
      Debugger.debug(Level.INFO, "Hooking into PlaceholderAPI");
      new PlaceholderManager().register();
    }
    Debugger.debug(Level.INFO, "Hooked into soft dependencies took {0}ms", System.currentTimeMillis() - start);
  }

  private void startPluginMetrics() {
    Metrics metrics = new Metrics(this);
    metrics.addCustomChart(new Metrics.SimplePie("database_enabled", () -> String.valueOf(configPreferences.getOption(ConfigPreferences.Option.DATABASE_ENABLED))));
    metrics.addCustomChart(new Metrics.SimplePie("bungeecord_hooked", () -> String.valueOf(configPreferences.getOption(ConfigPreferences.Option.BUNGEE_ENABLED))));
    metrics.addCustomChart(new Metrics.SimplePie("locale_used", () -> LanguageManager.getPluginLocale().getPrefix()));
    metrics.addCustomChart(new Metrics.SimplePie("update_notifier", () -> {
      if (getConfig().getBoolean("Update-Notifier.Enabled", true)) {
        return getConfig().getBoolean("Update-Notifier.Notify-Beta-Versions", true) ? "Enabled with beta notifier" : "Enabled";
      }
      return getConfig().getBoolean("Update-Notifier.Notify-Beta-Versions", true) ? "Beta notifier only" : "Disabled";
    }));
  }

  private void checkUpdate() {
    if (!getConfig().getBoolean("Update-Notifier.Enabled", true)) {
      return;
    }
    UpdateChecker.init(this, 66614).requestUpdateCheck().whenComplete((result, exception) -> {
      if (!result.requiresUpdate()) {
        return;
      }
      if (result.getNewestVersion().contains("b")) {
        if (getConfig().getBoolean("Update-Notifier.Notify-Beta-Versions", true)) {
          Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[MurderMystery] Your software is ready for update! However it's a BETA VERSION. Proceed with caution.");
          Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[MurderMystery] Current version %old%, latest version %new%".replace("%old%", getDescription().getVersion()).replace("%new%",
            result.getNewestVersion()));
        }
        return;
      }
      MessageUtils.updateIsHere();
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Your MurderMystery plugin is outdated! Download it to keep with latest changes and fixes.");
      Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Disable this option in config.yml if you wish.");
      Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Current version: " + ChatColor.RED + getDescription().getVersion() + ChatColor.YELLOW + " Latest version: " + ChatColor.GREEN + result.getNewestVersion());
    });
  }

  private void setupFiles() {
    for (String fileName : Arrays.asList("arenas", "bungee", "rewards", "stats", "lobbyitems", "mysql", "specialblocks")) {
      File file = new File(getDataFolder() + File.separator + fileName + ".yml");
      if (!file.exists()) {
        saveResource(fileName + ".yml", false);
      }
    }
  }

  public boolean is1_12_R1() {
    return version.equalsIgnoreCase("v1_12_R1");
  }

  public boolean is1_14_R1() {
    return version.equalsIgnoreCase("v1_14_R1");
  }

  public boolean is1_15_R1() {
    return version.equalsIgnoreCase("v1_15_R1");
  }

  public boolean is1_16_R1() {
    return version.equalsIgnoreCase("v1_16_R1");
  }

  public RewardsFactory getRewardsHandler() {
    return rewardsHandler;
  }

  public BungeeManager getBungeeManager() {
    return bungeeManager;
  }

  public PartyHandler getPartyHandler() {
    return partyHandler;
  }

  public ConfigPreferences getConfigPreferences() {
    return configPreferences;
  }

  public MysqlDatabase getMysqlDatabase() {
    return database;
  }

  public SignManager getSignManager() {
    return signManager;
  }

  public CorpseHandler getCorpseHandler() {
    return corpseHandler;
  }

  public ArgumentsRegistry getArgumentsRegistry() {
    return argumentsRegistry;
  }

  public HookManager getHookManager() {
    return hookManager;
  }

  public UserManager getUserManager() {
    return userManager;
  }

  private void saveAllUserStatistics() {
    for (Player player : getServer().getOnlinePlayers()) {
      User user = userManager.getUser(player);
      StringBuilder update = new StringBuilder(" SET ");
      for (StatsStorage.StatisticType stat : StatsStorage.StatisticType.values()) {
        if (!stat.isPersistent()) continue;
        if (update.toString().equalsIgnoreCase(" SET ")){
          update.append(stat.getName()).append("=").append(user.getStat(stat));
        }
        update.append(", ").append(stat.getName()).append("=").append(user.getStat(stat));
      }
      String finalUpdate = update.toString();
      //copy of userManager#saveStatistic but without async database call that's not allowed in onDisable method.
      if (userManager.getDatabase() instanceof MysqlManager) {
        ((MysqlManager) userManager.getDatabase()).getDatabase().executeUpdate("UPDATE "+((MysqlManager) getUserManager().getDatabase()).getTableName()+ finalUpdate + " WHERE UUID='" + user.getPlayer().getUniqueId().toString() + "';");
        continue;
      }
      userManager.getDatabase().saveAllStatistic(user);
    }
  }


}