package com.diz.AmuletQuest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum CustomItems {

    QUEST_BOOK(createQuestBook()),
    TIER1(createTier1()),
    TIER2(createTier2()),
    TIER3(createTier3());
//    TIER4(),
//    TIER5();

    private ItemStack item;

    CustomItems(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    private static ItemStack createTier1() {
        ItemStack reward = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = reward.getItemMeta();

        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(
                UUID.randomUUID(),
                "health",
                5.0,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlot.OFF_HAND));
        meta.setDisplayName(ChatColor.RED + "Blessing of Health");

        meta.setLore(Lore.HEALTH.getText());
        reward.setItemMeta(meta);

        return reward;
    }

    private static ItemStack createTier2() {
        ItemStack reward = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = reward.getItemMeta();

        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(
                UUID.randomUUID(),
                "armor",
                5.0,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlot.OFF_HAND));
        meta.setDisplayName(ChatColor.BLUE + "Blessing of Armor");
        meta.setLore(Lore.ARMOR.getText());
        reward.setItemMeta(meta);

        return reward;
    }

    private static ItemStack createTier3() {
        ItemStack reward = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = reward.getItemMeta();

        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(
                UUID.randomUUID(),
                "speed",
                0.2,
                AttributeModifier.Operation.ADD_SCALAR,
                EquipmentSlot.OFF_HAND));
        meta.setDisplayName(ChatColor.GREEN + "Blessing of Swiftness");

        meta.setLore(Lore.SPEED.getText());
        reward.setItemMeta(meta);

        return reward;
    }

    private static ItemStack createQuestBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        meta.setTitle("dizQuestBook");
        meta.setAuthor("The Wizard of Oz");
        meta.setDisplayName("Magical Bitchin' Quest Book");
        List<String> pages = new ArrayList<String>();
        pages.add("");
        meta.setPages(pages);
        book.setItemMeta(meta);
        book.setItemMeta(meta);
        return book;
    }
}
