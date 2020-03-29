package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.ChallengeInstance;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ChallengeService {
    @POST("/challenge/join")
    Call<ChallengeInstance> joinChallenge(@Field("challenge_id") int challenge_id, @Field("team_id") int team_id, @Field("user_id") int user_id);
}
