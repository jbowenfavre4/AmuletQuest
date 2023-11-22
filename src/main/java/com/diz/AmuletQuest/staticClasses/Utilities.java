package com.diz.AmuletQuest.staticClasses;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {

    private Utilities() {}

    public static EntityType getRandomEntityType(ArrayList<EntityType> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static String entityTypeToPluralString(EntityType entity) {
        String name = entity.name().toLowerCase();
        return name.substring(0,1).toUpperCase() + name.substring(1) + "s";
    }

    public static String randomStringFromList(ArrayList<String> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static Integer randomNumber(Integer bound) {
        Random random = new Random();
        return random.nextInt(bound) + 1;
    }

}
