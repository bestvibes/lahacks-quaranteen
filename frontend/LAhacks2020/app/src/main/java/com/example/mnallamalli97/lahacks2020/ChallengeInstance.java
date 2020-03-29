package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class ChallengeInstance {
    @SerializedName("id")
    private int id;
    @SerializedName("challenge")
    private Integer masterChallenge;
    @SerializedName("teams")
    private int[] teams;
    @SerializedName("tasks")
    private TaskInstance[] tasks;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("scores")
    private int[] scores;

    public ChallengeInstance(int id, Integer challenge, TaskInstance[] tasks, int[] teams, String start_time, int[] scores){
        this.id = id;
        this.tasks = tasks;
        this.masterChallenge = challenge;
        this.teams = teams;
        this.startTime = start_time;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public TaskInstance[] getTasks() {
        return tasks;
    }

    public Integer getMasterChallenge() {
        return masterChallenge;
    }

    public int[] getTeams() {
        return teams;
    }

    public String getStartTime() {
        return startTime;
    }

    public int[] getScores() {
        return scores;
    }
}
