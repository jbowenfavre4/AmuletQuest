package com.diz.AmuletQuest.eventlisteners;
import com.diz.AmuletQuest.Main;
import com.diz.AmuletQuest.enums.Lore;
import com.diz.AmuletQuest.staticClasses.QuestManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import com.diz.AmuletQuest.singeltons.ConfigManager;

// listens for player to kill a mob, checks quest status and updates JSON if necessary
public class PlayerKillListener implements Listener {

    @EventHandler
    public void onPlayerKillEvent(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            EntityType killedType = e.getEntity().getType();
            Player killer = e.getEntity().getKiller();

            if (killedType == ConfigManager.getEntityTypeFromConfig("quest-one-mob", Main.getQuestDefaultEntity())) {
               QuestManager.questOneCheck(killer);

            } else if (killedType == ConfigManager.getEntityTypeFromConfig("quest-two-mob", Main.getQuestDefaultEntity())) {
                QuestManager.questTwoCheck(killer);

            } else if (killedType == ConfigManager.getEntityTypeFromConfig("quest-three-mob", Main.getQuestDefaultEntity())) {
                QuestManager.questThreeCheck(killer);
            }
        }

    }

}
