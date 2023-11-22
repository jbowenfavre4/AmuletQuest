package com.diz.AmuletQuest.eventlisteners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingCancelListener implements Listener {

    @EventHandler
    public void onCraftItem(PrepareItemCraftEvent e) {
        if (e.getRecipe() != null) {
            if (checkForItem(e.getInventory().getMatrix(), "Amulet", Material.NETHER_STAR)) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }

            if (checkForItem(e.getInventory().getMatrix(), "essence", Material.GLOWSTONE_DUST)) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }

        }
    }

    public static Boolean checkForItem(ItemStack[] itemList, String identifier, Material material) {
        for (ItemStack itemStack : itemList) {
            if (itemStack != null && itemStack.getType() == material) {
                if (itemStack.getItemMeta().getDisplayName().contains(identifier)) {
                    return true;
                }
            }
        }
        return false;
    }

}
