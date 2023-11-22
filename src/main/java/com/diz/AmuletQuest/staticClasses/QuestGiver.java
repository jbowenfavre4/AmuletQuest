package com.diz.AmuletQuest.staticClasses;
import com.diz.AmuletQuest.data.Quest;
import com.diz.AmuletQuest.data.quests.FindEntityQuest;
import com.diz.AmuletQuest.data.quests.FishingQuest;
import com.diz.AmuletQuest.data.quests.KillQuest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestGiver {

    public static ItemStack getBook(Quest quest) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = book.getItemMeta();
        if (meta != null && meta instanceof BookMeta) {
            ((BookMeta) meta).setTitle("QuestBook");
            ((BookMeta) meta).setAuthor("an unknown wizard");
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "quest-uuid"),
                    PersistentDataType.STRING,
                    quest.getUuid().toString()
            );

            ((BookMeta) meta).setPages(quest.getPage());
            meta.setDisplayName(quest.getBookDisplayName());
        }
        book.setItemMeta(meta);
        return book;
    }

    public static Quest newQuest() {
        String rarity;
        int rarityRoll = Utilities.randomNumber(100);
        if (rarityRoll == 95) {
            rarity = "Mythic";
        } else if (rarityRoll > 85) {
            rarity = "Legendary";
        } else if (rarityRoll > 50) {
            rarity = "Rare";
        } else {
            rarity = "Common";
        }
        int questTypeRoll = Utilities.randomNumber(3);
        if (questTypeRoll == 1) {
            return new FindEntityQuest(rarity, null);
        } else if (questTypeRoll == 2) {
            return new FishingQuest(rarity,null);
        } else {
            return new KillQuest(rarity, null);
        }
    }

    public static ItemStack getAmulet() {
        ItemStack amulet = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = amulet.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Amulet");
        meta.setLore(new ArrayList<String>(Arrays.asList(
                "This amulet can be infused with essence",
                "at an anvil to provide passive",
                "benefits while in your off hand."
        )));

        meta.getPersistentDataContainer().set(
                new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "repaired"),
                PersistentDataType.INTEGER,
                0
        );


        amulet.setItemMeta(meta);
        return amulet;
    }
}
