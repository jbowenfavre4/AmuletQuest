package com.diz.AmuletQuest.data.essences;
import com.diz.AmuletQuest.data.Essence;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LegendaryEssence implements Essence {

    private ChatColor rarityColor = ChatColor.LIGHT_PURPLE;
    private Attribute attribute;
    private List<Attribute> possibleAttributes = new ArrayList<>(Arrays.asList(
            Attribute.GENERIC_ARMOR,
            Attribute.GENERIC_MAX_HEALTH,
            Attribute.GENERIC_MOVEMENT_SPEED,
            Attribute.GENERIC_ATTACK_DAMAGE
    ));

    private ItemStack essence;

    public LegendaryEssence() {
        this.attribute = getRandomAttribute();
        this.produceItem();
    }

    @Override
    public ItemStack getItem() {
        return this.essence;
    }

    @Override
    public void produceItem() {
        String attribute;
        ItemStack reward = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta meta = reward.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("Can be infused into an amulet");
        switch (this.attribute) {
            case GENERIC_ARMOR:
                meta.setDisplayName(rarityColor + "Legendary essence of armor");
                lore.add("to provide a significant boost to armor");
                attribute = "armor";
                break;
            case GENERIC_MAX_HEALTH:
                meta.setDisplayName(rarityColor + "Legendary essence of health");
                lore.add("to provide a small health increase");
                attribute = "health";
                break;
            case GENERIC_MOVEMENT_SPEED:
                meta.setDisplayName(rarityColor + "Legendary essence of speed");
                lore.add("to provide a small boost to movement speed");
                attribute = "speed";
                break;
            case GENERIC_ATTACK_DAMAGE:
                meta.setDisplayName(rarityColor + "Legendary essence of strength");
                lore.add("to increase attack damage");
                attribute = "strength";
                break;
            default:
                meta.setDisplayName(rarityColor + "Legendary essence of knockback");
                lore.add("to increase knockback");
                attribute = "knockback";
                break;
        }

        meta.getPersistentDataContainer().set(
                new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "rarity"),
                PersistentDataType.STRING,
                "Legendary"
        );

        meta.getPersistentDataContainer().set(
                new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "attribute"),
                PersistentDataType.STRING,
                attribute
        );

        meta.setLore(lore);
        reward.setItemMeta(meta);
        this.essence = reward;
    }

    private Attribute getRandomAttribute() {
        Random random = new Random();
        int randomIndex = random.nextInt(possibleAttributes.size());
        return possibleAttributes.get(randomIndex);
    }
}