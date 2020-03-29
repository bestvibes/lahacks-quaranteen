package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.Verification;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface VerificationService {
    @POST("/verify/apple")
    Call<Verification> verifyApple(@Field("user_id") int user_id, @Field("img") byte[] image);

    @POST("/verify/video")
    Call<Verification> verifyVideo(@Field("user_id") int user_id, @Field("img") byte[] image);

    @POST("/verify/book")
    Call<Verification> verifyBook(@Field("user_id") int user_id, @Field("img") byte[] image);

    @POST("/verify/sunrise")
    Call<Verification> verifySunrise(@Field("user_id") int user_id, @Field("img") byte[] image);
}
