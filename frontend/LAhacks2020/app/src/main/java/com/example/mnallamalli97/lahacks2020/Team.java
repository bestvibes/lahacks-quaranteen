package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("members")
    private User[] teamMembers;
    @SerializedName("name")
    private String teamName;
    @SerializedName("joinCode")
    private String joinCode;
    @SerializedName("leader")
    private User leader;

    public Team(User[] teamMembers, String teamName, String joinCode, User leader){
        this.teamMembers = teamMembers;
        this.teamName = teamName;
        this.joinCode = joinCode;
        this.leader = leader;
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
