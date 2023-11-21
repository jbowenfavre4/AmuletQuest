package com.diz.AmuletQuest.data.quests;

import com.diz.AmuletQuest.Main;
import com.diz.AmuletQuest.data.Quest;
import com.diz.AmuletQuest.singeltons.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class FishingQuest implements Quest {

    private Integer requirement;
    private String eventType = "PlayerFishEvent";
    private Boolean complete = false;
    private UUID uuid = UUID.randomUUID();
    private Integer progress = 0;
    private String rarity;
    private ChatColor color;

    public FishingQuest(String rarity, Integer requirement) {
        if (requirement != null) {
            this.rarity = rarity;
        } else {
            this.rarity = "Common";
        }
        this.color = Quest.colorMap.get(this.rarity);
        if (requirement != null) {
            this.requirement = requirement;
        } else {
            switch(this.rarity) {
                case "Mythic":
                    this.requirement = 100;
                    break;
                case "Legendary":
                    this.requirement = 60;
                    break;
                case "Rare":
                    this.requirement = 30;
                    break;
                default:
                    this.requirement = 15;
                    break;
            }
        }

        FileManager.addQuest(this);
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public EntityType getEntityType() {
        return null;
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
        String page = ChatColor.DARK_PURPLE + "FISHING QUEST"
                + "\nCatch " + this.requirement + " " + "items while fishing"
                + "\n\nProgress: " + this.progress + "/" + this.requirement;
        if (this.complete) {
            page = page + "\n\n" + ChatColor.DARK_GREEN + "DONE";
        }
        return page;
    }
}
