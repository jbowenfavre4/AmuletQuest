package com.diz.AmuletQuest.staticClasses;

import com.diz.AmuletQuest.questtracking.PlayerQuestData;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoreClass {

    public static List<String> getTier1() {
        List<String> list = new ArrayList<>();
        list.add("With sword held high and heart aflame");
        return list;
    }

    public static List<String> getTier2() {
        List<String> list = new ArrayList<>();
        list.add("Long before the days of the Ardent crown");
        return list;
    }

    public static List<String> getTier3() {
        List<String> list = new ArrayList<>();
        list.add("He slew the cold and conquered the night");
        return list;
    }

    public static List<String> getTier4() {
        List<String> list = new ArrayList<>();
        list.add("He ignited the dawn and kindled the flame");
        return list;
    }

    public static List<String> getTier5() {
        List<String> list = new ArrayList<>();
        list.add("Blazing passion, unyielding conviction");
        return list;
    }


}
