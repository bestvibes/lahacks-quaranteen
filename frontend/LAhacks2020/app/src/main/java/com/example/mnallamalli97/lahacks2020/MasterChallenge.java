package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class MasterChallenge {
    @SerializedName("id")
    private int id;
    @SerializedName("master_tasks")
    private MasterTask[] masterTasks;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("prize")
    private String prize;
    @SerializedName("daily_point_limit")
    private int dailyPointCap;
    @SerializedName("team_size_limit")
    private int teamSizeLimit;
    @SerializedName("number_of_days")
    private int numberOfDays;

    public MasterChallenge (int id, MasterTask[] masterTasks, String name, String description, String prize, int dailyPointCap, int teamSizeLimit, int numberOfDays){
        this.id = id;
        this.name = name;
        this.description = description;
        this.prize = prize;
        this.masterTasks = masterTasks;
        this.dailyPointCap = dailyPointCap;
        this.teamSizeLimit = teamSizeLimit;
        this.numberOfDays = numberOfDays;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getPrize() {
        return prize;
    }

    public MasterTask[] getMasterTask() {
        return masterTasks;
    }

    public int getDailyPointCap() {
        return dailyPointCap;
    }

    public int getTeamSizeLimit() {
        return teamSizeLimit;
    }

    public int getNumberOfDays(){
        return numberOfDays;
    }
}
