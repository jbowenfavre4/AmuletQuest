package com.diz.AmuletQuest.singeltons;

import com.diz.AmuletQuest.data.Quest;
import com.diz.AmuletQuest.data.quests.FindEntityQuest;
import com.diz.AmuletQuest.data.quests.FishingQuest;
import com.diz.AmuletQuest.data.quests.KillQuest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;
import java.util.UUID;

/**
 * This class handles creating, reading and updating the json file that stores PlayerQuestData objects.
 *
 * @author Bowen Revill
 */
public class FileManager {
    private static final String QUESTS_FILE = "quests.json";
    private static FileManager instance;
    private static File questsFile;
    private FileManager(JavaPlugin plugin) {
        questsFile = new File(plugin.getDataFolder(), QUESTS_FILE);
        createQuestsFile(plugin);
    }

    public static FileManager getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new FileManager(plugin);
        }
        return instance;
    }

    public static void addQuest(Quest quest) {
        Gson gson = new Gson();
        JsonArray existing = readFile(questsFile);
        JsonArray newJsonArray;
        existing.add(gson.toJsonTree(quest));
        newJsonArray = existing;

        try {
            Writer writer = new FileWriter(questsFile, false);
            gson.toJson(newJsonArray, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createQuestsFile(JavaPlugin plugin) {
        try {
            plugin.getDataFolder().mkdir();
            if (!questsFile.exists()) {
                questsFile.createNewFile();
                Writer writer = new FileWriter(questsFile);
                writer.write("[]");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonArray readFile(File readFile) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(readFile);
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Quest getQuest(UUID uuid) {
        String stringUuid = uuid.toString();
        Gson gson = new Gson();
        JsonArray list = readFile(questsFile);
        for (JsonElement obj : list) {
            JsonObject jsonObj = obj.getAsJsonObject();
            if (jsonObj.has("uuid")) {
                if (jsonObj.get("uuid").toString().equals("\"" + stringUuid + "\"")) {
                    Quest quest;
                    switch(jsonObj.get("eventType").toString().replace("\"", "")) {
                        case "PlayerInteractEntityEvent":
                            quest = gson.fromJson(obj, FindEntityQuest.class);
                            break;
                        case "PlayerFishEvent":
                            quest = gson.fromJson(obj, FishingQuest.class);
                            break;
                        default:
                            quest = gson.fromJson(obj, KillQuest.class);
                            break;
                    }
                    return quest;
                }
            }

        }
        return null;
    }

    private static JsonArray getQuests() {
        return readFile(questsFile);
    }

    public static void editQuest(Quest quest) {
        Gson gson = new Gson();
        JsonArray oldList = getQuests();
        for (int i = 0; i < oldList.size(); i++) {
            if (oldList.get(i).getAsJsonObject().get("uuid").toString().equals("\"" + quest.getUuid().toString() + "\"")) {
                oldList.set(i, gson.toJsonTree(quest));
            }
        }
        try {
            Writer writer = new FileWriter(questsFile, false);
            gson.toJson(gson.toJsonTree(oldList), writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
