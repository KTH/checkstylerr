/*
 * MurderMystery - Find the murderer, kill him and survive!
 * Copyright (C) 2019  Plajer's Lair - maintained by Tigerpanzer_02, Plajer and contributors
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

package pl.plajer.murdermystery.handlers.language;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import pl.plajer.murdermystery.Main;
import pl.plajerlair.commonsbox.minecraft.migrator.MigratorUtils;

/*
  NOTE FOR CONTRIBUTORS - Please do not touch this class if you don't now how it works! You can break migrator modyfing these values!
 */
public class LanguageMigrator {

  public static final int CONFIG_FILE_VERSION = 8;
  private Main plugin;

  public LanguageMigrator(Main plugin) {
    this.plugin = plugin;

    //initializes migrator to update files with latest values
    configUpdate();
  }

  private void configUpdate() {
    if (plugin.getConfig().getInt("Version") == CONFIG_FILE_VERSION) {
      return;
    }
    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Murder Mystery] System notify >> Your config file is outdated! Updating...");
    File file = new File(plugin.getDataFolder() + "/config.yml");

    int version = plugin.getConfig().getInt("Version", CONFIG_FILE_VERSION - 1);

    for (int i = version; i < CONFIG_FILE_VERSION; i++) {
      switch (version) {
        case 1:
          MigratorUtils.addNewLines(file, "# How many blocks per tick sword thrown by murderer should fly\r\n" +
            "# Please avoid high values as it might look like the sword is\r\n" +
            "# blinking each tick\r\n" +
            "Murderer-Sword-Speed: 0.65");
          break;
        case 2:
          MigratorUtils.addNewLines(file, "# Should players' name tags in game be hidden?\r\n" +
            "Nametags-Hidden: true");
          break;
        case 3:
          MigratorUtils.addNewLines(file, "# Lobby waiting time set when lobby max players number is reached, used to start game quicker.\r\n" +
            "Start-Time-On-Full-Lobby: 15");
          break;
        case 4:
          MigratorUtils.addNewLines(file, "# Should players get no fall damage?\r\n" +
            "Disable-Fall-Damage: false");
          break;
        case 5:
          MigratorUtils.addNewLines(file, "#How long should be the sword attack after throw cooldown in seconds?\r\n" +
            "#Its normal lower than Murderer-Sword-Fly-Cooldown!\r\n" +
            "Murderer-Sword-Attack-Cooldown: 1\r\n" +
            "\r\n" +
            "#How long should be the sword fly cooldown in seconds?\r\n" +
            "Murderer-Sword-Fly-Cooldown: 5\r\n" +
            "\r\n" +
            "#How long should be the bow shoot cooldown in seconds?\r\n" +
            "Detective-Bow-Cooldown: 5");
          break;
        case 6:
          MigratorUtils.addNewLines(file, "# Which item should be your Murderer sword?\r\n" +
            "Murderer-Sword-Material: IRON_SWORD");
          break;
        case 8:
          MigratorUtils.addNewLines(file, "#How much arrows should a player with bow gets when he pick up a gold ingot?\r\n" +
            "Detective-Gold-Pick-Up-Arrows: 1\r\n" +
            "\r\n" +
            "#How much arrows should the detective gets on game start or when a player get a bow?\r\n" +
            "Detective-Default-Arrows: 3\r\n" +
            "\r\n" +
            "#How much arrows should the player get when the prayer gives a bow to him?\r\n" +
            "Detective-Prayer-Arrows: 2\r\n");
          break;
        default:
          break;
      }
      version++;
    }
    updateConfigVersionControl(version);
    plugin.reloadConfig();
    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Murder Mystery] [System notify] Config updated, no comments were removed :)");
    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Murder Mystery] [System notify] You're using latest config file version! Nice!");
  }

  private void updateConfigVersionControl(int oldVersion) {
    File file = new File(plugin.getDataFolder() + "/config.yml");
    MigratorUtils.removeLineFromFile(file, "# Don't modify");
    MigratorUtils.removeLineFromFile(file, "Version: " + oldVersion);
    MigratorUtils.removeLineFromFile(file, "# No way! You've reached the end! But... where's the dragon!?");
    MigratorUtils.addNewLines(file, "# Don't modify\r\nVersion: " + CONFIG_FILE_VERSION + "\r\n\r\n# No way! You've reached the end! But... where's the dragon!?");
  }

}