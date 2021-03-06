package com.example.mnallamalli97.lahacks2020;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int user_id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("team")
    @Nullable private Integer team;
    @SerializedName("team_leader")
    private Boolean team_leader;

    public User(String name, String email, Integer team, Boolean team_leader){
        this.name = name;
        this.email = email;
        this.team = team;
        this.team_leader = team_leader;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public Integer getTeam(){
        return team;
    }

    public Boolean isTeamLeader() {
        return team_leader;
    }
}
