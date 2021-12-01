/*
 * TheBridge - Defend your base and try to wipe out the others
 * Copyright (C)  2021  Plugily Projects - maintained by Tigerpanzer_02, 2Wild4You and contributors
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
 *
 */

package plugily.projects.thebridge.kits.premium;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.plajerlair.commonsbox.minecraft.compat.XMaterial;
import pl.plajerlair.commonsbox.minecraft.helper.WeaponHelper;
import plugily.projects.thebridge.arena.Arena;
import plugily.projects.thebridge.arena.ArenaRegistry;
import plugily.projects.thebridge.arena.ArenaState;
import plugily.projects.thebridge.handlers.PermissionsManager;
import plugily.projects.thebridge.kits.KitRegistry;
import plugily.projects.thebridge.kits.basekits.PremiumKit;
import plugily.projects.thebridge.utils.Utils;

import java.util.List;

/**
 * Created by Tom on 28/07/2015.
 */
public class PremiumHardcoreKit extends PremiumKit {

  public PremiumHardcoreKit() {
    setName(getPlugin().getChatManager().colorMessage("Kits.Premium-Hardcore.Name"));
    List<String> description = Utils.splitString(getPlugin().getChatManager().colorMessage("Kits.Premium-Hardcore.Description"), 40);
    this.setDescription(description.toArray(new String[0]));
    KitRegistry.registerKit(this);
  }

  @Override
  public boolean isUnlockedByPlayer(Player player) {
    return PermissionsManager.gotKitsPerm(player) || player.hasPermission("thebridge.kit.premiumhardcore");
  }

  @Override
  public void giveKitItems(Player player) {
    player.getInventory().addItem(WeaponHelper.getEnchanted(new ItemStack(getMaterial()),
      new Enchantment[]{Enchantment.DAMAGE_ALL}, new int[]{5}));
    player.getInventory().addItem(WeaponHelper.getEnchanted(XMaterial.DIAMOND_PICKAXE.parseItem(), new Enchantment[] {
      Enchantment.DURABILITY, Enchantment.DIG_SPEED}, new int[] {10, 5}));
    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(6);
    Arena arena = ArenaRegistry.getArena(player);
    if (arena == null || arena.getArenaState() != ArenaState.IN_GAME) {
      return;
    }
    player.getInventory().addItem(new ItemStack(XMaterial.matchXMaterial(arena.getBase(player).getColor().toUpperCase() + getPlugin().getConfigPreferences().getColoredBlockMaterial()).get().parseMaterial(), 64));

  }

  @Override
  public Material getMaterial() {
    return Material.DIAMOND_SWORD;
  }

  @Override
  public void reStock(Player player) {
    Arena arena = ArenaRegistry.getArena(player);
    if (arena == null || arena.getArenaState() != ArenaState.IN_GAME) {
      return;
    }
    player.getInventory().addItem(new ItemStack(XMaterial.matchXMaterial(arena.getBase(player).getColor().toUpperCase() + getPlugin().getConfigPreferences().getColoredBlockMaterial()).get().parseMaterial(), 64));
  }


}
