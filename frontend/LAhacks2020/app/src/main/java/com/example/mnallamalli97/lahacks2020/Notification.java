package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class Notification {
    @SerializedName("sender")
    private User sender;
    @SerializedName("receiver")
    private User receiver;

    public Notification(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
    }

    public User getSender(){
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }
}
