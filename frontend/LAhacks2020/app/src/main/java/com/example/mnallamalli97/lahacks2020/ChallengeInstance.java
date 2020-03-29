package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class ChallengeInstance {
    @SerializedName("id")
    private int id;
    @SerializedName("tasks")
    private TaskInstance[] tasks;
    @SerializedName("challenge")
    private int challenge;
    @SerializedName("teams")
    private int[] teams;
    @SerializedName("scores")
    private int[] scores;

    public ChallengeInstance(int id, TaskInstance[] tasks, int challenge, int[] teams, int[] scores){
        this.id = id;
        this.tasks = tasks;
        this.challenge = challenge;
        this.teams = teams;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public TaskInstance[] getTasks() {
        return tasks;
    }

    public int getChallenge() {
        return challenge;
    }

    public int[] getTeams() {
        return teams;
    }

    public int[] getScores() {
        return scores;
    }
}
