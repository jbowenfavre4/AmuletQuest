package com.diz.AmuletQuest.eventlisteners;
import com.diz.AmuletQuest.staticClasses.QuestGiver;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class CraftingInteractListener implements Listener {

    @EventHandler
    public void onPlayerCraft(PrepareAnvilEvent e) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("AmuletQuest");
        AnvilInventory inv = e.getInventory();
        ItemStack amulet = inv.getItem(0);
        ItemStack essence = inv.getItem(1);
        if (amulet != null && amulet.getType() == Material.NETHER_STAR) {
            ItemMeta amuletMeta = amulet.getItemMeta();
            if (amuletMeta.getDisplayName().contains("Amulet")) {
                if (essence != null && essence.getType() == Material.GLOWSTONE_DUST) {
                    ItemMeta essenceMeta = essence.getItemMeta();
                    if (essenceMeta.getDisplayName().contains("essence")) {

                        String rarity = essenceMeta.getPersistentDataContainer().get(
                                new NamespacedKey(plugin, "rarity"),
                                PersistentDataType.STRING
                        );

                        ItemStack newAmulet = getNewAmulet(amulet, essence);

                        Integer repaired = newAmulet.getItemMeta().getPersistentDataContainer().get(
                                new NamespacedKey(plugin, "repaired"),
                                PersistentDataType.INTEGER
                        );

                        Integer baseCost;

                        switch (rarity) {
                            case "Rare":
                                baseCost = 2;
                                break;
                            case "Legendary":
                                baseCost = 3;
                                break;
                            case "Mythic":
                                baseCost = 4;
                                break;
                            default:
                                baseCost = 1;
                        }

                        e.setResult(newAmulet);

                        plugin.getServer().getScheduler().runTask(plugin, () -> e.getInventory().setRepairCost(baseCost * repaired + repaired));
                    }
                }
            }
        }
    }

    private ItemStack getNewAmulet(ItemStack baseAmulet, ItemStack essence) {
        Attribute newAttribute;
        Gson gson = new Gson();
        double increase = 0;
        ItemStack newAmulet = QuestGiver.getAmulet();
        ItemMeta newMeta = newAmulet.getItemMeta();
        ItemMeta baseMeta = baseAmulet.getItemMeta();
        ItemMeta essenceMeta = essence.getItemMeta();

        String essenceAttribute = essenceMeta.getPersistentDataContainer().get(
                new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "attribute"),
                PersistentDataType.STRING
        );

        String rarity = essenceMeta.getPersistentDataContainer().get(
                new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "rarity"),
                PersistentDataType.STRING
        );

        switch (essenceAttribute) {
            case "health":
                newAttribute = Attribute.GENERIC_MAX_HEALTH;
                break;
            case "speed":
                newAttribute = Attribute.GENERIC_MOVEMENT_SPEED;
                break;
            case "strength":
                newAttribute = Attribute.GENERIC_ATTACK_DAMAGE;
                break;
            case "knockback":
                newAttribute = Attribute.GENERIC_ATTACK_KNOCKBACK;
                break;
            default:
                newAttribute = Attribute.GENERIC_ARMOR;
                break;
        }

        ClassLoader classLoader = CraftingInteractListener.class.getClassLoader();
        String jsonFilePath = "UpgradeLogic.json";

        try (InputStream inputStream = classLoader.getResourceAsStream(jsonFilePath);
             Scanner scanner = new Scanner(inputStream)) {

            // Read the contents of the file
            StringBuilder jsonContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            JsonObject jsonData = gson.fromJson(jsonContent.toString(), JsonObject.class);
            JsonObject rarityInfo = jsonData.getAsJsonObject(rarity);
            increase = rarityInfo.get(essenceAttribute).getAsFloat();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (increase == 0) {
            increase = 1;
        }

        Multimap<Attribute, AttributeModifier> existingAttributes = baseMeta.getAttributeModifiers();
        if (existingAttributes != null) {
            for (Map.Entry<Attribute, AttributeModifier> entry : existingAttributes.entries()) {
                if (entry.getKey() == newAttribute) {
                    increase = increase + entry.getValue().getAmount();

                } else {
                    newMeta.addAttributeModifier(entry.getKey(), entry.getValue());
                }
            }
        }

        newMeta.addAttributeModifier(newAttribute, new AttributeModifier(UUID.randomUUID(),
                        essenceAttribute,
                        increase,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlot.OFF_HAND
                ));

        // increase repaired value
        Integer repaired = baseMeta.getPersistentDataContainer().get(
                new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "repaired"),
                PersistentDataType.INTEGER
        );

        newMeta.getPersistentDataContainer().set(
                new NamespacedKey(Bukkit.getPluginManager().getPlugin("AmuletQuest"), "repaired"),
                PersistentDataType.INTEGER,
                repaired + 1
        );

        newAmulet.setItemMeta(newMeta);

        return newAmulet;

    }

}
