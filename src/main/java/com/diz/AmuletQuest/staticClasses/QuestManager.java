package com.diz.AmuletQuest.staticClasses;

import com.diz.AmuletQuest.Main;
import com.diz.AmuletQuest.enums.CustomItems;
import com.diz.AmuletQuest.questtracking.PlayerQuestData;
import com.diz.AmuletQuest.singeltons.ConfigManager;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.entity.Player;

// static class that manages updating json file when quest progress is made, and activating and finishing quests
public class QuestManager {

    private QuestManager() {}

    public static void questOneCheck(Player p) {
        Integer req = ConfigManager.getIntFromConfig("quest-one-requirement", Main.getQuestReqDefault());
        PlayerQuestData player = FileManager.getUser(p.getUniqueId());

        if (player.getQuestOneActive() == true) {
            player.setQuestOneProgress(player.getQuestOneProgress() + 1);

            if (player.getQuestOneProgress() == req) {
                player.setQuestOneActive(false);
                player.setQuestOneDone(true);
                player.setQuestTwoActive(true);
                p.getInventory().addItem(CustomItems.TIER1.getItem());
                p.sendMessage("You have completed the first quest! The reward has been added to your inventory. The second quest is now available in your quest book.");
            }
            FileManager.editUser(player);
        }
    }

    public static void questTwoCheck(Player p) {
        Integer req = ConfigManager.getIntFromConfig("quest-two-requirement", Main.getQuestReqDefault());
        PlayerQuestData player = FileManager.getUser(p.getUniqueId());

        if (player.getQuestTwoActive() == true) {
            player.setQuestTwoProgress(player.getQuestTwoProgress() + 1);

            if (player.getQuestTwoProgress() == req) {
                player.setQuestTwoDone(true);
                player.setQuestTwoActive(false);
                player.setQuestThreeActive(true);
                p.getInventory().addItem(CustomItems.TIER2.getItem());
                p.sendMessage("You have completed the second quest! The reward has been added to your inventory. The third quest is now available in your quest book.");
            }
            FileManager.editUser(player);
        }
    }

    public static void questThreeCheck(Player p) {
        Integer req = ConfigManager.getIntFromConfig("quest-three-requirement", Main.getQuestReqDefault());
        PlayerQuestData player = FileManager.getUser(p.getUniqueId());

        if (player.getQuestThreeActive() == true) {
            player.setQuestThreeProgress(player.getQuestThreeProgress() + 1);

            if (player.getQuestThreeProgress() == req) {
                player.setQuestThreeDone(true);
                player.setQuestThreeActive(false);
                //player.setQuestThreeActive(true); <-- Change this line when adding more quests
                p.getInventory().addItem(CustomItems.TIER3.getItem());
                p.sendMessage("You have completed all quests! Enjoy the rewards.");
            }
            FileManager.editUser(player);
        }
    }
}