package com.diz.AmuletQuest.questtracking;

/**
 * This class holds quest information and progress for a specific player.
 *
 * @author Bowen Revill
 */
public class PlayerQuestData {

    private String name;
    private String uuid;
    private Boolean hasEarnedQuestBook = false;
    private Integer questOneProgress = 0;
    private Boolean questOneDone = false;
    private Boolean questOneActive = false;
    private Integer questTwoProgress = 0;
    private Boolean questTwoDone = false;
    private Boolean questTwoActive = false;
    private Integer questThreeProgress = 0;
    private Boolean questThreeDone = false;
    private Boolean questThreeActive = false;


    public PlayerQuestData(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public Integer getQuestOneProgress() {
        return questOneProgress;
    }

    public Boolean getQuestOneDone() {
        return questOneDone;
    }

    public Boolean getQuestOneActive() {
        return questOneActive;
    }

    public Integer getQuestTwoProgress() {
        return questTwoProgress;
    }

    public Boolean getQuestTwoDone() {
        return questTwoDone;
    }

    public Boolean getQuestTwoActive() {
        return questTwoActive;
    }

    public Integer getQuestThreeProgress() {
        return questThreeProgress;
    }

    public Boolean getQuestThreeDone() {
        return questThreeDone;
    }

    public Boolean getQuestThreeActive() {
        return questThreeActive;
    }

    public void setQuestOneProgress(Integer questOneProgress) {
        this.questOneProgress = questOneProgress;
    }

    public void setQuestOneDone(Boolean questOneDone) {
        this.questOneDone = questOneDone;
    }

    public void setQuestOneActive(Boolean questOneActive) {
        this.questOneActive = questOneActive;
    }

    public void setQuestTwoProgress(Integer questTwoProgress) {
        this.questTwoProgress = questTwoProgress;
    }

    public void setQuestTwoDone(Boolean questTwoDone) {
        this.questTwoDone = questTwoDone;
    }

    public void setQuestTwoActive(Boolean questTwoActive) {
        this.questTwoActive = questTwoActive;
    }

    public void setQuestThreeProgress(Integer questThreeProgress) {
        this.questThreeProgress = questThreeProgress;
    }

    public void setQuestThreeDone(Boolean questThreeDone) {
        this.questThreeDone = questThreeDone;
    }

    public void setQuestThreeActive(Boolean questThreeActive) {
        this.questThreeActive = questThreeActive;
    }

    public String getPlayerName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public Boolean getHasEarnedQuestBook() {
        return hasEarnedQuestBook;
    }

    public void setHasEarnedQuestBook(Boolean hasEarnedQuestBook) {
        this.hasEarnedQuestBook = hasEarnedQuestBook;
    }
}
