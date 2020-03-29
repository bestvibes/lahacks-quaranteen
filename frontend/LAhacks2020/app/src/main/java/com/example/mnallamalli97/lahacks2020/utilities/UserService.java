package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("/models/users/?format=json")
    Call<List<User>> listUsers();

    @PUT("/models/users/{id}/")
    Call<User> updateUserName(@Path("id") int userId, @Field("name") String name);
}
