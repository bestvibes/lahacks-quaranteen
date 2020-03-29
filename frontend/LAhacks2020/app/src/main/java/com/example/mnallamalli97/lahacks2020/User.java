package com.example.mnallamalli97.lahacks2020;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int user_id;
    @SerializedName("name")
    private String name;
    @SerializedName("team")
    @Nullable private Integer team;
    @SerializedName("team_leader")
    private Boolean team_leader;

    public User(int user_id, String name, @Nullable Integer team, Boolean team_leader){
        this.user_id = user_id;
        this.name = name;
        this.team = team;
        this.team_leader = team_leader;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName(){
        return name;
    }

    public Integer getTeam(){
        return team;
    }

    public Boolean getTeamLeader(){
        return team_leader;
    }
}
