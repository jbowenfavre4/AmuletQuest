package com.diz.AmuletQuest.data;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public interface Quest {

    Integer getRequirement();
    String getEventType();
    String getPage();
    UUID getUuid();
    EntityType getEntityType();
    Boolean complete();
    void completeQuest();
    Boolean increaseProgress();
    String getRarity();
    ChatColor getColor();
    String getBookDisplayName();
    static Map<String, ChatColor> colorMap = Map.of(
            "Common", ChatColor.GREEN,
            "Rare", ChatColor.BLUE,
            "Legendary", ChatColor.LIGHT_PURPLE,
            "Mythic", ChatColor.GOLD
    );


    ArrayList<EntityType> commonFindMobs = new ArrayList<>(Arrays.asList(
            EntityType.VILLAGER,
            EntityType.PIGLIN,
            EntityType.SQUID,
            EntityType.DROWNED,
            EntityType.BEE,
            EntityType.WOLF
    ));

    ArrayList<EntityType> rareFindMobs = new ArrayList<>(Arrays.asList(
            EntityType.MAGMA_CUBE,
            EntityType.WANDERING_TRADER,
            EntityType.WITHER_SKELETON,
            EntityType.BLAZE,
            EntityType.GHAST,
            EntityType.IRON_GOLEM
    ));

    ArrayList<EntityType> legendaryFindMobs = new ArrayList<>(Arrays.asList(
            EntityType.ENDER_DRAGON,
            EntityType.ELDER_GUARDIAN,
            EntityType.WITHER
    ));

    ArrayList<EntityType> mythicFindMobs = new ArrayList<>(Arrays.asList(
            EntityType.MUSHROOM_COW,
            EntityType.SKELETON_HORSE,
            EntityType.WARDEN
    ));

    ArrayList<EntityType> commonKillMobs = new ArrayList<>(Arrays.asList(
            EntityType.ZOMBIE,
            EntityType.SKELETON,
            EntityType.SPIDER
    ));

    ArrayList<EntityType> rareKillMobs = new ArrayList<>(Arrays.asList(
            EntityType.ENDERMAN,
            EntityType.DROWNED,
            EntityType.SLIME,
            EntityType.BLAZE
    ));

    ArrayList<EntityType> legendaryKillMobs = new ArrayList<>(Arrays.asList(
            EntityType.GHAST,
            EntityType.CAVE_SPIDER,
            EntityType.HOGLIN,
            EntityType.VINDICATOR,
            EntityType.EVOKER
    ));

    ArrayList<EntityType> mythicKillMobs = new ArrayList<>(Arrays.asList(
            EntityType.ELDER_GUARDIAN,
            EntityType.ENDER_DRAGON,
            EntityType.WITHER,
            EntityType.WARDEN
    ));

}
