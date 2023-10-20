package com.diz.AmuletQuest.staticClasses;

import com.diz.AmuletQuest.questtracking.PlayerQuestData;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoreClass {

    public static List<String> getTier1() {
        List<String> list = new ArrayList<>();
        list.add("With sword held high and heart aflame");
        return list;
    }

    public static List<String> getTier2() {
        List<String> list = new ArrayList<>();
        list.add("Long before the days of the Ardent crown");
        return list;
    }

    public static List<String> getTier3() {
        List<String> list = new ArrayList<>();
        list.add("He slew the cold and conquered the night");
        return list;
    }

    public static List<String> getTier4() {
        List<String> list = new ArrayList<>();
        list.add("He ignited the dawn and kindled the flame");
        return list;
    }

    public static List<String> getTier5() {
        List<String> list = new ArrayList<>();
        list.add("Blazing passion, unyielding conviction");
        return list;
    }

    // reads the json file and updates a player's book based on their quest progress
    public static class QuestBookWriter {

        private QuestBookWriter() {}

        public static void writeToBook(UUID uuid, ItemStack book) {

            // retrieve player quest data from the json
            PlayerQuestData player = FileManager.getUser(uuid);

            // activate first quest if player has not started yet
            if (player.getHasEarnedQuestBook() == false) {
                player.setQuestOneActive(true);
                player.setHasEarnedQuestBook(true);
                FileManager.editUser(player);
            }

            ItemMeta meta = book.getItemMeta();

            // create title page and page one from QuestBookPage class
            if (meta instanceof BookMeta) {
                List<String> newPages = new ArrayList<>();
                newPages.add(QuestBookPage.getTitlePage());
                newPages.add(QuestBookPage.getQuestOnePage(player.getQuestOneProgress()));

                // create second page if player has progressed to it
                if (player.getQuestTwoActive() == true || player.getQuestTwoDone() == true) {
                    newPages.add(QuestBookPage.getQuestTwoPage(player.getQuestTwoProgress()));
                }

                // create third page if player has progressed to it
                if (player.getQuestThreeActive() == true || player.getQuestThreeDone() == true) {
                    newPages.add(QuestBookPage.getQuestThreePage(player.getQuestThreeProgress()));
                }

                // add all pages to the book and set meta
                ((BookMeta) meta).setPages(newPages);
                book.setItemMeta(meta);

            }
        }

    }
}
