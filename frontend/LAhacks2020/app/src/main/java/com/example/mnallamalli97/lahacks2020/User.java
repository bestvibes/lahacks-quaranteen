package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    private String loginId;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("taskHistory")
    private String[] tasks;

    public User(String loginId, String avatarUrl, String name, String email, String password, String[] taskHistory){
        this.loginId = loginId;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tasks = taskHistory;
    }

    public String getLoginId(){
        return loginId;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String[] getTasks(){
        return tasks;
    }
}
