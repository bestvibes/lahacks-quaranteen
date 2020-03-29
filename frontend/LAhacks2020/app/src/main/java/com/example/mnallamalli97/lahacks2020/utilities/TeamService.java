package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.Team;
import com.example.mnallamalli97.lahacks2020.pojo.NameUserId;
import com.example.mnallamalli97.lahacks2020.pojo.UserId;
import com.example.mnallamalli97.lahacks2020.pojo.UserIdJoinCode;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TeamService {

    @GET("/models/teams/{id}")
    Call<Team> getTeamById(@Path("id") int id);

    @POST("/team/create/")
    Call<Team> createTeam(@Body NameUserId body);

    @POST("/team/leave/")
    Call<Team> leaveTeam(@Body UserId body);

    @POST("/team/join/")
    Call<Team> joinTeam(@Body UserIdJoinCode body);

}
