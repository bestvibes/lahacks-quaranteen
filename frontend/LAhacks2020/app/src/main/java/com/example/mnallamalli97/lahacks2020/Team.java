package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("id")
    private int id;
    @SerializedName("members")
    private User[] teamMembers;
    @SerializedName("name")
    private String teamName;
    @SerializedName("joinCode")
    private String joinCode;
    @SerializedName("leader")
    private User leader;

    public Team(int id, User[] teamMembers, String teamName, String joinCode, User leader){
        this.id = id;
        this.teamMembers = teamMembers;
        this.teamName = teamName;
        this.joinCode = joinCode;
        this.leader = leader;
    }

    public int getId() {
        return id;
    }

    public User[] getTeamMembers(){
        return teamMembers;
    }

    public String getTeamName(){
        return teamName;
    }

    public String getJoinCode(){
        return joinCode;
    }

    public User getLeader(){
        return leader;
    }
}
