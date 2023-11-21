package com.diz.AmuletQuest.eventlisteners;

import com.diz.AmuletQuest.data.Quest;
import com.diz.AmuletQuest.data.essences.CommonEssence;
import com.diz.AmuletQuest.data.essences.LegendaryEssence;
import com.diz.AmuletQuest.data.essences.MythicEssence;
import com.diz.AmuletQuest.data.essences.RareEssence;
import com.diz.AmuletQuest.singeltons.FileManager;
import com.diz.AmuletQuest.staticClasses.QuestGiver;
import com.diz.AmuletQuest.staticClasses.Utilities;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class PlayerFishListener implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        if (e.getCaught() != null) {
            if (Utilities.randomNumber(10) == 5) {
                Quest quest = QuestGiver.newQuest();
                e.getPlayer().getInventory().addItem(QuestGiver.getBook(quest));
            }

            Inventory inventory = e.getPlayer().getInventory();
            for (ItemStack item : inventory.getContents()) {
                if (item != null && item.getType() == Material.WRITTEN_BOOK) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta instanceof BookMeta) {
                        if (((BookMeta) meta).getTitle().contains("QuestBook")) {
                            String customData = meta.getPersistentDataContainer().get(
                                    new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "quest-uuid"),
                                    PersistentDataType.STRING
                            );
                            if (customData != null) {
                                Quest quest = FileManager.getQuest(UUID.fromString(customData));
                                if (quest != null) {
                                    if (quest.getEventType().equals("PlayerFishEvent") && !quest.complete()) {
                                        quest.increaseProgress();
                                        ((BookMeta) meta).setPages(quest.getPage());
                                        FileManager.editQuest(quest);
                                        if (quest.complete()) {
                                            meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.DARK_GREEN + "DONE")));
                                            meta.setDisplayName(meta.getDisplayName() + "\n" + ChatColor.DARK_GREEN + "DONE");
                                            HashMap<Integer, ItemStack> map;
                                            switch(quest.getRarity()) {
                                                case "Mythic":
                                                    map = e.getPlayer().getInventory().addItem(new MythicEssence().getItem());
                                                    break;
                                                case "Legendary":
                                                    map = e.getPlayer().getInventory().addItem(new LegendaryEssence().getItem());
                                                    break;
                                                case "Rare":
                                                    map = e.getPlayer().getInventory().addItem(new RareEssence().getItem());
                                                    break;
                                                default:
                                                    map = e.getPlayer().getInventory().addItem(new CommonEssence().getItem());
                                            }
                                            if (map != null && !map.isEmpty()) {
                                                Location playerLocation = e.getPlayer().getLocation();
                                                World world = e.getPlayer().getWorld();
                                                for (Map.Entry<Integer, ItemStack> entry : map.entrySet()) {
                                                    world.dropItem(playerLocation, entry.getValue());
                                                }
                                                e.getPlayer().sendTitle(quest.getColor() + " Quest Complete",
                                                        "Item dropped (inventory full)",
                                                        20, 50, 20);
                                            } else {
                                                e.getPlayer().sendTitle(quest.getColor() + " Quest Complete",
                                                        "Reward added to inventory",
                                                        20, 50, 20);
                                            }
                                        }
                                        item.setItemMeta(meta);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
