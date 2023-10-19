package com.diz.AmuletQuest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static ConfigManager instance;
    private JavaPlugin plugin;
    private static Map<String, String> configValues = new HashMap<>();

    private ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        FileConfiguration config = this.plugin.getConfig();
        for (String thing : config.getKeys(true)) {
            configValues.put(thing, config.getString(thing));
        }
    }

    public static ConfigManager getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        return instance;
    }

    public static String getStringFromConfig(String key, String defaultValue) {
        String value = configValues.get(key);
        if (value instanceof String) {
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }
        return defaultValue;
    }

    public static Integer getIntFromConfig(String key, Integer defaultValue) {
        String value = configValues.get(key);
        if (value != null && !value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println(key + "is not a valid integer. Using a default value.");
            }
        }
        return defaultValue;
    }

    public static EntityType getEntityTypeFromConfig(String key, EntityType defaultValue) {
        String value = configValues.get(key);
        if (value != null && !value.isEmpty()) {
            EntityType retEntity = stringToEntityType(value);
            if (retEntity != null) {
                return retEntity;
            }
        }
        System.out.println(key + "value missing or is invalid entity type.");
        return defaultValue;
    }

    private static EntityType stringToEntityType(String s) {
        s = s.toUpperCase();
        for (EntityType entityType : EntityType.values()) {
            if (entityType.name().equals(s)) {
                return entityType;
            }
        }
        return null;
    }

}
