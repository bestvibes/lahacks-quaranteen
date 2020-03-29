package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class MasterTask {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("challenge")
    private Integer masterChallenge;
    @SerializedName("description")
    private String description;
    @SerializedName("verification_url")
    private String verificationMLModelURL;
    @SerializedName("point_value")
    private int pointValue;

    public MasterTask(int id, String name, Integer masterChallenge, String description, String verificationMLModelURL, int pointValue){
        this.id = id;
        this.name = name;
        this.masterChallenge = masterChallenge;
        this.description = description;
        this.verificationMLModelURL = verificationMLModelURL;
        this.pointValue = pointValue;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMasterChallenge() {
        return masterChallenge;
    }

    public String getDescription() {
        return description;
    }

    public String getVerificationMLModelURL() {
        return verificationMLModelURL;
    }

    public int getPointValue() {
        return pointValue;
    }
}
