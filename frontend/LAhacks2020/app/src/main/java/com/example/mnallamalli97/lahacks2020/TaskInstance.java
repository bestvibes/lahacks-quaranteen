package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class TaskInstance {
    @SerializedName("id")
    private int id;
    @SerializedName("user")
    private Integer user;
    @SerializedName("date")
    private String date;
    @SerializedName("masterTask")
    private MasterTask masterTask;
    @SerializedName("challenge")
    private Integer challenge;
    @SerializedName("completed")
    private boolean verified;

    public TaskInstance(int id, Integer user, String date, MasterTask masterTask, boolean verified, Integer challenge){
        this.user = user;
        this.date = date;
        this.masterTask = masterTask;
        this.verified = verified;
        this.id = id;
        this.challenge = challenge;
    }

    public Integer getUser() {
        return user;
    }

    public String getDay() {
        return date;
    }

    public MasterTask getMasterTask() {
        return masterTask;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getId() {
        return id;
    }

    public Integer getChallenge() {
        return challenge;
    }
}
