package com.diz.AmuletQuest.eventlisteners;
import com.diz.AmuletQuest.FileManager;
import com.diz.AmuletQuest.questtracking.PlayerQuestData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

// listens for player to join and adds them to the json file if necessary
public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        Gson gson = new Gson();
        UUID uuid = e.getPlayer().getUniqueId();
        String stringUuid = e.getPlayer().getUniqueId().toString();
        JsonArray array = FileManager.readFile();

        // loop through json objects and return if player is found
        for (JsonElement obj : array) {
            PlayerQuestData questData = gson.fromJson(obj, PlayerQuestData.class);
            if (questData.getUuid().equals(stringUuid)) {
                return;
            }
        }

        // add user to the json file if they are not there
        FileManager.addUser(e.getPlayer().getName(), uuid);
    }

}
