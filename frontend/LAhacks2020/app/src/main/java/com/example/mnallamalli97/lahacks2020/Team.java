package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("id")
    private int id;
    @SerializedName("members")
    private User[] teamMembers;
    @SerializedName("name")
    private String teamName;
    @SerializedName("join_code")
    private String joinCode;

    public Team(int id, User[] teamMembers, String teamName, String joinCode){
        this.id = id;
        this.teamMembers = teamMembers;
        this.teamName = teamName;
        this.joinCode = joinCode;
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
}
