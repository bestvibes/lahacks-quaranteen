package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.APIResponse;
import com.example.mnallamalli97.lahacks2020.Team;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface TeamService {

    @POST("/team/create/")
    Call<APIResponse<Team>> createTeam(@Field("name") String name, @Field("user_id") int user_id);

    @POST("/team/leave/")
    Call<APIResponse<Team>> leaveTeam(@Field("user_id") int user_id);

    @POST("/team/join/")
    Call<APIResponse<Team>> joinTeam(@Field("name") String name, @Field("join_code") String join_code);

}
