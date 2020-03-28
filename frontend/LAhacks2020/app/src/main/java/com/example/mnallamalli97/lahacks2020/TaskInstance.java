package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class TaskInstance {
    @SerializedName("user")
    private User user;
    @SerializedName("day")
    private String day;
    @SerializedName("masterTask")
    private MasterTask masterTask;
    @SerializedName("verified")
    private boolean verified;

    public TaskInstance(User user, String day, MasterTask masterTask, boolean verified){
        this.user = user;
        this.day = day;
        this.masterTask = masterTask;
        this.verified = verified;
    }

    public User getUser() {
        return user;
    }

    public String getDay() {
        return day;
    }

    public MasterTask getMasterTask() {
        return masterTask;
    }

    public boolean isVerified() {
        return verified;
    }
}
