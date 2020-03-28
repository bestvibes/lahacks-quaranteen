package com.example.mnallamalli97.lahacks2020.network;

import com.example.mnallamalli97.lahacks2020.User;


import retrofit2.Call;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface GamifyClient {

    @POST("/login")
    @FormUrlEncoded
    Call<User> login(
            @Field("name") String name,
            @Field("email") String email

    );
}

