package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class MasterTask {
    @SerializedName("masterChallenger")
    private MasterChallenge masterChallenge;
    @SerializedName("description")
    private String description;
    @SerializedName("verificationMLModelUrl")
    private String verificationMLModelURL;
    @SerializedName("pointValue")
    private int pointValue;

    public MasterTask(MasterChallenge masterChallenge, String description, String verificationMLModelURL, int pointValue){
        this.masterChallenge = masterChallenge;
        this.description = description;
        this.verificationMLModelURL = verificationMLModelURL;
        this.pointValue = pointValue;
    }

    public MasterChallenge getMasterChallenge() {
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
