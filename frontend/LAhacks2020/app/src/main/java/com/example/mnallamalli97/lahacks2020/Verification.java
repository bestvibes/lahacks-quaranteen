package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class Verification {
    @SerializedName("verified")
    private boolean verified;
    @SerializedName("code")
    private String code;

    public Verification(boolean verified, String code){
        this.verified = verified;
        this.code = code;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getCode() {
        return code;
    }
}
