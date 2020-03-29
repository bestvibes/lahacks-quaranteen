package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.Team;
import com.example.mnallamalli97.lahacks2020.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TeamService {
    @GET("/team")
    Call<Team> getTeam();

    @POST("/joinTeam")
    Call<Team> joinTeam(@Body User user, @Field("joinCode") int joinCode);



}
