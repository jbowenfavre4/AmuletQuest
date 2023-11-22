package com.diz.AmuletQuest.eventlisteners;
import com.diz.AmuletQuest.Main;
import com.diz.AmuletQuest.data.Quest;
import com.diz.AmuletQuest.data.essences.CommonEssence;
import com.diz.AmuletQuest.data.essences.LegendaryEssence;
import com.diz.AmuletQuest.data.essences.MythicEssence;
import com.diz.AmuletQuest.data.essences.RareEssence;
import com.diz.AmuletQuest.singeltons.FileManager;
import com.diz.AmuletQuest.staticClasses.QuestGiver;
import com.diz.AmuletQuest.staticClasses.Utilities;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

// listens for mob deaths and handles dropping the quest book
public class EntityDeathListener implements Listener {

    private JavaPlugin plugin;

    public EntityDeathListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        Integer questRoll = Utilities.randomNumber(100);
        Integer amuletRoll = Utilities.randomNumber(100);
        if (questRoll > 100 - Main.questDropChance()) { // CHANGE
            Quest quest = QuestGiver.newQuest();
            e.getDrops().add(QuestGiver.getBook(quest));
        } else if (amuletRoll > 100 - Main.amuletDropChance()) {
            e.getDrops().add(QuestGiver.getAmulet());
        }
        Player p = e.getEntity().getKiller();
        if (p != null) {
            EntityType entity = e.getEntityType();
            Inventory inventory = p.getInventory();
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
                                    if (quest.getEventType().equals("EntityDeathEvent") && entity == quest.getEntityType() && !quest.complete()) {
                                        quest.increaseProgress();
                                        ((BookMeta) meta).setPages(quest.getPage());

                                        FileManager.editQuest(quest);
                                        if (quest.complete()) {
                                            meta.setLore(new ArrayList<>(Arrays.asList(ChatColor.DARK_GREEN + "DONE")));
                                            meta.setDisplayName(meta.getDisplayName() + "\n" + ChatColor.DARK_GREEN + "DONE");
                                            HashMap<Integer, ItemStack> map;
                                            switch(quest.getRarity()) {
                                                case "Mythic":
                                                    map = p.getInventory().addItem(new MythicEssence().getItem());
                                                    break;
                                                case "Legendary":
                                                    map = p.getInventory().addItem(new LegendaryEssence().getItem());
                                                    break;
                                                case "Rare":
                                                    map = p.getInventory().addItem(new RareEssence().getItem());
                                                    break;
                                                default:
                                                    map = p.getInventory().addItem(new CommonEssence().getItem());
                                            }
                                            if (map != null && !map.isEmpty()) {
                                                Location playerLocation = p.getLocation();
                                                World world = p.getWorld();
                                                for (Map.Entry<Integer, ItemStack> entry : map.entrySet()) {
                                                    world.dropItem(playerLocation, entry.getValue());
                                                }
                                                p.sendTitle(quest.getColor() + " Quest Complete",
                                                        "Item dropped (inventory full)",
                                                        20, 50, 20);
                                            } else {
                                                p.sendTitle(quest.getColor() + " Quest Complete",
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
