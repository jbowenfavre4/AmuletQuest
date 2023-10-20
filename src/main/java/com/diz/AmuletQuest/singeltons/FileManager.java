package com.diz.AmuletQuest.singeltons;

import com.diz.AmuletQuest.questtracking.PlayerQuestData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

/**
 * This class handles creating, reading and updating the json file that stores PlayerQuestData objects.
 *
 * @author Bowen Revill
 */
public class FileManager {

    private static final String FILE_NAME = "questdata.json";
    private static FileManager instance;
    private static File file;
    private FileManager(JavaPlugin plugin) {
        file = new File(plugin.getDataFolder(), FILE_NAME);
        createJsonFile(plugin);
    }

    public static FileManager getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new FileManager(plugin);
        }
        return instance;
    }

    public void createJsonFile(JavaPlugin plugin) {
        try {
            plugin.getDataFolder().mkdir();
            if (!file.exists()) {
                file.createNewFile();
                Writer writer = new FileWriter(file);
                writer.write("[]");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonArray readFile() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(file);
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addUser(String name, UUID uuid) {
        Gson gson = new Gson();
        JsonArray existing = readFile();
        PlayerQuestData entry = new PlayerQuestData(name, uuid.toString());
        JsonArray newJsonArray;
        existing.add(gson.toJsonTree(entry));
        newJsonArray = existing;

        try {
            Writer writer = new FileWriter(file, false);
            gson.toJson(newJsonArray, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static PlayerQuestData getUser(UUID uuid) {
        String stringUuid = uuid.toString();
        Gson gson = new Gson();
        JsonArray list = readFile();
        for (JsonElement obj : list) {
            PlayerQuestData questData = gson.fromJson(obj, PlayerQuestData.class);
            if (questData.getUuid().equals(stringUuid)) {
                return questData;
            }
        }
        return null;
    }

    private static List<PlayerQuestData> getObjectList() {
        Gson gson = new Gson();
        JsonArray array = readFile();
        Type listType = new TypeToken<List<PlayerQuestData>>() {}.getType();
        List<PlayerQuestData> objList = gson.fromJson(array, listType);
        return objList;
    }

    public static void editUser(PlayerQuestData updatedItem) {
        Gson gson = new Gson();
        List<PlayerQuestData> oldList = getObjectList();
        for (int i = 0; i < oldList.size(); i++) {
            if (oldList.get(i).getUuid().equals(updatedItem.getUuid())) {
                oldList.set(i, updatedItem);
            }
        }
        try {
            Writer writer = new FileWriter(file, false);
            gson.toJson(gson.toJsonTree(oldList), writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
