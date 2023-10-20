package com.diz.AmuletQuest.commands;

import com.diz.AmuletQuest.singeltons.ConfigManager;
import com.diz.AmuletQuest.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            System.out.println("quest drop mob: " + ConfigManager.getEntityTypeFromConfig("quest-drop-mob", Main.getQuestDefaultEntity()));
            System.out.println("quest one mob: " + ConfigManager.getEntityTypeFromConfig("quest-one-mob", Main.getQuestDefaultEntity()));
            System.out.println("quest two mob: " + ConfigManager.getEntityTypeFromConfig("quest-two-mob", Main.getQuestDefaultEntity()));
            System.out.println("quest three mob: " + ConfigManager.getEntityTypeFromConfig("quest-three-mob", Main.getQuestDefaultEntity()));
        }

        return false;
    }
}
