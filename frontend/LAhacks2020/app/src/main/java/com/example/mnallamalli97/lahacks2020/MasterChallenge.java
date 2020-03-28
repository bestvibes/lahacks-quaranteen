package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class MasterChallenge {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("masterTask")
    private MasterTask masterTask;
    @SerializedName("pointCap")
    private int dailyPointCap;
    @SerializedName("teamSizeLimit")
    private int teamSizeLimit;

    public MasterChallenge (String name, String description, MasterTask masterTask, int dailyPointCap, int teamSizeLimit){
        this.name = name;
        this.description = description;
        this.masterTask = masterTask;
        this.dailyPointCap = dailyPointCap;
        this.teamSizeLimit = teamSizeLimit;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public MasterTask getMasterTask() {
        return masterTask;
    }

    public int getDailyPointCap() {
        return dailyPointCap;
    }

    public int getTeamSizeLimit() {
        return teamSizeLimit;
    }
}
