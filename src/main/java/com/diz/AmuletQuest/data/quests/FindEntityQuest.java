package com.diz.AmuletQuest.data.quests;

import com.diz.AmuletQuest.data.Quest;
import com.diz.AmuletQuest.singeltons.FileManager;
import com.diz.AmuletQuest.staticClasses.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import java.util.Random;
import java.util.UUID;

public class FindEntityQuest implements Quest {

    private Integer requirement;
    private String eventType = "PlayerInteractEntityEvent";
    private EntityType entity;
    private String rarity;
    private Integer progress = 0;
    private UUID uuid = UUID.randomUUID();
    private Boolean complete = false;
    private ChatColor color;

    public FindEntityQuest(String rarity, Integer requirement) {
        if (rarity != null) {
            this.rarity = rarity;
        } else {
            this.rarity = "Common";
        }
        this.color = Quest.colorMap.get(rarity);
        if (requirement != null) {
            this.requirement = requirement;
        } else {
            this.requirement = 1;
        }
        switch (this.rarity) {
            case "Rare":
                this.entity = Utilities.getRandomEntityType(rareFindMobs);
                break;
            case "Legendary":
                this.entity = Utilities.getRandomEntityType(legendaryFindMobs);
                break;
            case "Mythic":
                this.entity = Utilities.getRandomEntityType(mythicFindMobs);
                break;
            default:
                this.entity = Utilities.getRandomEntityType(commonFindMobs);
        }

        FileManager.addQuest(this);
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public Integer getRequirement() {
        return this.requirement;
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }

    @Override
    public String getPage() {
        String page = ChatColor.DARK_PURPLE + "FIND QUEST"
                + "\nFind " + this.requirement + " " + this.entity
                + "\n (right click on the mob when you find it to complete)";
        if (this.complete) {
            page = page + "\n\n" + ChatColor.DARK_GREEN + "DONE";
        }
        return page;

    }

    @Override
    public EntityType getEntityType() {
        return this.entity;
    }

    @Override
    public Boolean complete() {
        return this.complete;
    }

    @Override
    public void completeQuest() {
        this.complete = true;
    }

    @Override
    public Boolean increaseProgress() {
        this.progress++;
        if (this.progress == this.requirement) {
            this.completeQuest();
            return true;
        }
        return false;
    }

    @Override
    public String getRarity() {
        return this.rarity;
    }

    @Override
    public ChatColor getColor() {
        return this.color;
    }

    @Override
    public String getBookDisplayName() {
        return this.color + this.rarity + " Quest Book";
    }

}

