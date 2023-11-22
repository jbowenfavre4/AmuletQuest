package com.diz.AmuletQuest;
import com.diz.AmuletQuest.eventlisteners.*;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Integer questDropChance = 10;
    private static Integer amuletDropChance = 10;
    private static Boolean enablePlugin = false;

    private static final Logger logger = Logger.getLogger("AmuletQuest");

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        if (this.getConfig().get("enable-plugin").toString().equals("true")) {
            logger.log(Level.INFO, "Amulet Quest plugin enabled");

            Integer userAmuletDropChance;
            Integer userQuestDropChance;

            try {
                userAmuletDropChance = Integer.valueOf(this.getConfig().get("amulet-drop-chance").toString());
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error while reading config value for amulet-drop-chance");
                userAmuletDropChance = null;
            }

            try {
                userQuestDropChance = Integer.valueOf(this.getConfig().get("quest-drop-chance").toString());
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error while reading config value for quest-drop-chance");
                userQuestDropChance = null;
            }

            if (userQuestDropChance != null && userQuestDropChance > 0 && userQuestDropChance instanceof Integer) {
                questDropChance = userQuestDropChance;
                logger.log(Level.INFO, "Quest drop chance set to " + userQuestDropChance + "%");
            } else {
                logger.log(Level.WARNING, "Invalid quest-drop-chance value, setting to default (10%)");
            }

            if (userAmuletDropChance != null && userAmuletDropChance > 0 && userAmuletDropChance instanceof Integer) {
                amuletDropChance = userAmuletDropChance;
                logger.log(Level.INFO, "Amulet drop chance set to " + userAmuletDropChance + "%");
            } else {
                logger.log(Level.WARNING, "Invalid amulet-drop-chance value, setting to default (10%)");
            }

            Bukkit.getPluginManager().registerEvents(new EntityDeathListener(this), this);
            Bukkit.getPluginManager().registerEvents(new PlayerFishListener(), this);
            Bukkit.getPluginManager().registerEvents(new PlayerFindEntityListener(), this);
            Bukkit.getPluginManager().registerEvents(new CraftingInteractListener(), this);
            Bukkit.getPluginManager().registerEvents(new CraftingCancelListener(), this);

            FileManager.getInstance(this);

        } else {
            logger.log(Level.INFO, "Amulet Quest plugin disabled in the config");
        }

    }

    public static Integer questDropChance() {
        return questDropChance;
    }

    public static Integer amuletDropChance() {
        return amuletDropChance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
