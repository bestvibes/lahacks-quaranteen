package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("team")
    private String team;
    @SerializedName("team_leader")
    private Boolean team_leader;

    public User(String name, String email, String team, Boolean team_leader){
        this.name = name;
        this.email = email;
        this.team = team;
        this.team_leader = team_leader;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getTeam(){
        return team;
    }

    public Boolean getTeamLeader(){
        return team_leader;
    }
}
