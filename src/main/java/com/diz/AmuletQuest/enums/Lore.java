package com.diz.AmuletQuest.enums;

import com.diz.AmuletQuest.Main;
import com.diz.AmuletQuest.enums.CustomItems;
import com.diz.AmuletQuest.questtracking.PlayerQuestData;
import com.diz.AmuletQuest.singeltons.ConfigManager;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum Lore {

    HEALTH(createHealthList()),
    ARMOR(createArmorList()),
    SPEED(createSpeedList());


    private List<String> text;

    public List<String> getText() {
        return text;
    }

    Lore(List<String> text) {
        this.text = text;
    }

    private static List<String> createHealthList() {
        List<String> thing = new ArrayList<>();
        thing.add("The gentle heat radiating from");
        thing.add("this amulet warms your heart.");
        return thing;
    }

    private static List<String> createSpeedList() {
        List<String> thing = new ArrayList<>();
        thing.add("This boy is fast as hell");
        return thing;
    }

    private static List<String> createArmorList() {
        List<String> thing = new ArrayList<>();
        thing.add("This should be the first line");
        thing.add("Armor yo");
        return thing;
    }
}
