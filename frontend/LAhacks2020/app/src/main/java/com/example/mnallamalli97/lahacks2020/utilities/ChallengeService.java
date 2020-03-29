package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.ChallengeInstance;
import com.example.mnallamalli97.lahacks2020.pojo.ChallengeIdTeamIdUserId;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChallengeService {
    @POST("/challenge/join")
    Call<ChallengeInstance> joinChallenge(@Body ChallengeIdTeamIdUserId body);
}
