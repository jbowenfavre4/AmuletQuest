package com.diz.AmuletQuest;
import com.diz.AmuletQuest.eventlisteners.*;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Integer questDropChance = 10;
    private static Integer amuletDropChance = 10;
    private static Boolean enablePlugin = false;

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        if (this.getConfig().get("enable-plugin").toString().equals("true")) {
            System.out.println("Amulet Quest plugin enabled");

            Integer userAmuletDropChance;
            Integer userQuestDropChance;

            try {
                userAmuletDropChance = Integer.valueOf(this.getConfig().get("amulet-drop-chance").toString());
            } catch (NumberFormatException e) {
                System.out.println("Error while reading config value for amulet-drop-chance");
                userAmuletDropChance = null;
            }

            try {
                userQuestDropChance = Integer.valueOf(this.getConfig().get("quest-drop-chance").toString());
            } catch (NumberFormatException e) {
                System.out.println("Error while reading config value for quest-drop-chance");
                userQuestDropChance = null;
            }

            if (userQuestDropChance != null && userQuestDropChance > 0 && userQuestDropChance instanceof Integer) {
                questDropChance = userQuestDropChance;
                System.out.println("Quest drop chance set to " + userQuestDropChance + "%");
            } else {
                System.out.println("Invalid quest-drop-chance value, setting to default (10%)");
            }

            if (userAmuletDropChance != null && userAmuletDropChance > 0 && userAmuletDropChance instanceof Integer) {
                amuletDropChance = userAmuletDropChance;
                System.out.println("Amulet drop chance set to " + userAmuletDropChance + "%");
            } else {
                System.out.println("Invalid amulet-drop-chance value, setting to default (10%)");
            }

            Bukkit.getPluginManager().registerEvents(new EntityDeathListener(this), this);
            Bukkit.getPluginManager().registerEvents(new PlayerFishListener(), this);
            Bukkit.getPluginManager().registerEvents(new PlayerFindEntityListener(), this);
            Bukkit.getPluginManager().registerEvents(new CraftingInteractListener(), this);

            FileManager.getInstance(this);

        } else {
            System.out.println("Amulet Quest plugin disabled in the config");
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
