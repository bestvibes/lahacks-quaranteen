package com.example.mnallamalli97.lahacks2020;

import com.google.gson.annotations.SerializedName;

public class APIResponse<T> {
    @SerializedName("model")
    private String model;

    @SerializedName("pk")
    private String pk;

    @SerializedName("fields")
    private T fields;

    public APIResponse(String model, String pk, T fields){
        this.model = model;
        this.pk = pk;
        this.fields = fields;
    }

    public String getModel() {
        return model;
    }

    public String getPk() {
        return pk;
    }

    public T getFields() {
        return fields;
    }
}
