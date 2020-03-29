package com.example.mnallamalli97.lahacks2020;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mnallamalli97.lahacks2020.pojo.ChallengeIdTeamIdUserId;
import com.example.mnallamalli97.lahacks2020.utilities.ChallengeService;
import com.example.mnallamalli97.lahacks2020.utilities.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeViewModel extends ViewModel {
    private MutableLiveData<ChallengeInstance> challenge;

//    public ChallengeViewModel(){
//        challenge = new MutableLiveData<ChallengeInstance>();
//    }

    public void joinChallenge(int challenge_id, int team_id, int user_id){
        ChallengeService service = NetworkUtils.getRetrofitInstance().create(ChallengeService.class);
        ChallengeIdTeamIdUserId body = new ChallengeIdTeamIdUserId(challenge_id, team_id, user_id);
        Call<ChallengeInstance> call = service.joinChallenge(body);
        call.enqueue(new Callback<ChallengeInstance>() {
            @Override
            public void onResponse(Call<ChallengeInstance> call, Response<ChallengeInstance> response) {
                challenge.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ChallengeInstance> call, Throwable t) {
                challenge.setValue(null);
            }
        });
    }

    public LiveData<ChallengeInstance> getChallenge(){
        return challenge;
    }
}
