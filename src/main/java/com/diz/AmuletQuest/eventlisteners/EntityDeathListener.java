package com.diz.AmuletQuest.eventlisteners;

import com.diz.AmuletQuest.singeltons.ConfigManager;
import com.diz.AmuletQuest.enums.CustomItems;
import com.diz.AmuletQuest.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import java.util.Random;

// listens for mob deaths and handles dropping the quest book
public class EntityDeathListener implements Listener {

    private Integer defaultDropChance = 10;

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {

        // check if dead entity matches mob that drops quest book from config
        if (e.getEntityType() == ConfigManager.getEntityTypeFromConfig("quest-drop-mob", Main.getQuestDefaultEntity())) {

            // get drop chance from config or use default
            Integer dropChance = ConfigManager.getIntFromConfig("drop-percentage", defaultDropChance);
            if (dropChance > 0 || dropChance < 101) {
                dropChance = defaultDropChance;
            }

            // roll for the item and drop if successful
            Random r = new Random();
            if (r.nextInt(101) > dropChance) {

                // create a quest book and add it to the drops
                e.getDrops().add(CustomItems.QUEST_BOOK.getItem());
            }
        }
    }
}
