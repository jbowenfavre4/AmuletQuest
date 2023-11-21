package com.diz.AmuletQuest.data.quests;

import com.diz.AmuletQuest.Main;
import com.diz.AmuletQuest.data.Quest;
import com.diz.AmuletQuest.staticClasses.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import com.diz.AmuletQuest.singeltons.FileManager;

import java.util.Random;
import java.util.UUID;

public class KillQuest implements Quest {

    private String eventType = "EntityDeathEvent";
    private Integer requirement;
    private String rarity;
    private EntityType entity;
    private ChatColor color;
    private UUID uuid = UUID.randomUUID();
    private Boolean complete = false;
    private Integer progress = 0;

    public KillQuest(String rarity, Integer requirement) {
        if (rarity != null) {
            this.rarity = rarity;
        } else {
            this.rarity = "Common";
        }
        this.color = Quest.colorMap.get(rarity);
        if (requirement != null) {
            this.requirement = requirement;
        } else {
            switch (this.rarity) {
                case "Rare":
                    this.requirement = 25;
                    this.entity = Utilities.getRandomEntityType(rareKillMobs);
                    break;
                case "Legendary":
                    this.entity = Utilities.getRandomEntityType(legendaryKillMobs);
                    this.requirement = 25;
                    break;
                case "Mythic":
                    this.entity = Utilities.getRandomEntityType(mythicKillMobs);
                    this.requirement = 1;
                    break;
                default:
                    this.entity = Utilities.getRandomEntityType(commonKillMobs);
                    this.requirement = 50;
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

    @Override
    public Integer getRequirement() {
        return requirement;
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }

    @Override
    public String getPage() {
        String page = "QUEST\n"
                + "Slay " + this.requirement + " " + Utilities.entityTypeToPluralString(this.entity)
                + "\n\nProgress: " + this.progress + "/" + this.requirement;
        if (this.complete) {
            page = page + "\n\n" + ChatColor.DARK_GREEN + "DONE";
        }
        return page;
    }
}
