package com.diz.AmuletQuest;

import com.diz.AmuletQuest.commands.SpeedCommand;
import com.diz.AmuletQuest.commands.TestCommand;
import com.diz.AmuletQuest.eventlisteners.EntityDeathListener;
import com.diz.AmuletQuest.eventlisteners.PlayerJoinListener;
import com.diz.AmuletQuest.eventlisteners.PlayerKillListener;
import com.diz.AmuletQuest.eventlisteners.PlayerHoldQuestBookListener;
import com.diz.AmuletQuest.singeltons.ConfigManager;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    private static final Integer defaultQuestReq = 25;
    private static final EntityType defaultQuestEntityType = EntityType.ZOMBIE;

    @Override
    public void onEnable() {
        // Plugin startup logic

        FileManager.getInstance(this);
        ConfigManager.getInstance(this);

        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerKillListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerHoldQuestBookListener(), this);

        getCommand("test").setExecutor(new TestCommand());
        getCommand("speed").setExecutor(new SpeedCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Integer getQuestReqDefault() {
        return defaultQuestReq;
    }

    public static EntityType getQuestDefaultEntity() {
        return defaultQuestEntityType;
    }
}
