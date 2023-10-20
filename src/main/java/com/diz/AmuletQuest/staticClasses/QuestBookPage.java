package com.diz.AmuletQuest.staticClasses;

import com.diz.AmuletQuest.Main;
import com.diz.AmuletQuest.singeltons.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

// static class holding templates for each page of the quest book
public class QuestBookPage {

    private QuestBookPage() {}

    // title page
    public static String getTitlePage() {
        return ChatColor.RED + "This is a " + ChatColor.BLUE + "magical quest book.";
    }

    /* QUEST PAGES
    * get entity type from config for the associated quest
    * get quest requirement from config for the quest
    * create the text for the page with the config values
    * add "DONE" text to the page if the requirement is met
    * */

    public static String getQuestOnePage(Integer progress) {
        EntityType ent = ConfigManager.getEntityTypeFromConfig("quest-one-mob", Main.getQuestDefaultEntity());
        Integer req = ConfigManager.getIntFromConfig("quest-one-requirement", Main.getQuestReqDefault());
        String pageText = ChatColor.DARK_PURPLE +
                "Quest One\n\n" +
                "Slay " + req +  " " + entityTypeToPluralString(ent) + ".\n\n" +
                "Progress: " + progress.toString() + "/" + req;
        if (progress >= req) {
            pageText = pageText + ChatColor.GREEN + "\nDONE";
        }
        return pageText;
    }

    public static String getQuestTwoPage(Integer progress) {
        EntityType ent = ConfigManager.getEntityTypeFromConfig("quest-two-mob", Main.getQuestDefaultEntity());
        Integer req = ConfigManager.getIntFromConfig("quest-two-requirement", Main.getQuestReqDefault());
        String pageText = ChatColor.BLUE +
                "Quest Two\n\n" +
                "Slay " + req +  " " + entityTypeToPluralString(ent) + ".\n\n" +
                "Progress: " + progress.toString() + "/" + req;
        if (progress >= req) {
            pageText = pageText + ChatColor.GREEN + "\nDONE";
        }
        return pageText;
    }

    public static String getQuestThreePage(Integer progress) {
        EntityType ent = ConfigManager.getEntityTypeFromConfig("quest-three-mob", Main.getQuestDefaultEntity());
        Integer req = ConfigManager.getIntFromConfig("quest-three-requirement", Main.getQuestReqDefault());
        String pageText = ChatColor.DARK_GREEN +
                "Quest Three\n\n" +
                "Slay " + req + " " + entityTypeToPluralString(ent) +".\n\n" +
                "Progress: " + progress.toString() + "/" + req;
        if (progress >= req) {
            pageText = pageText + ChatColor.GREEN + "\nDONE";
        }
        return pageText;
    }

    // takes an entity type and returns a string form of the plural name for the pages
    private static String entityTypeToPluralString(EntityType entity) {
        String name = entity.name().toLowerCase();
        return name.substring(0,1).toUpperCase() + name.substring(1) + "s";
    }

}
