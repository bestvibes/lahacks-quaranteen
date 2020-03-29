package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.Verification;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface VerificationService {
    @Multipart
    @POST("/verify/apple/")
    Call<Verification> verifyApple(@Part("user_id") int user_id, @Part("img") RequestBody image);

    @Multipart
    @POST("/verify/video/")
    Call<Verification> verifyVideo(@Part("user_id") int user_id, @Part("img") RequestBody image);

    @Multipart
    @POST("/verify/book/")
    Call<Verification> verifyBook(@Part("user_id") int user_id, @Part("img") RequestBody image);

    @Multipart
    @POST("/verify/sunrise/")
    Call<Verification> verifySunrise(@Part("user_id") int user_id, @Part("img") RequestBody image);
}
