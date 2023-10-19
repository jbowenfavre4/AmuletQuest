package com.diz.AmuletQuest;

import com.diz.AmuletQuest.questtracking.PlayerQuestData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// reads the json file and updates a player's book based on their quest progress
public class QuestBookWriter {

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
