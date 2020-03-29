package com.example.mnallamalli97.lahacks2020;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mnallamalli97.lahacks2020.utilities.NetworkUtils;
import com.example.mnallamalli97.lahacks2020.utilities.TeamService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDataViewModel extends ViewModel {
    private MutableLiveData<Team> team;

    public TeamDataViewModel(){
        team = new MutableLiveData<Team>();
    }

    public void createTeam(String name, int user_id){
        TeamService service = NetworkUtils.getRetrofitInstance().create(TeamService.class);
        Call<Team> call = service.createTeam(name, user_id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                team.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                team.setValue(null);
            }
        });
    }

    public void leaveTeam(int user_id){
        TeamService service = NetworkUtils.getRetrofitInstance().create(TeamService.class);
        Call<Team> call = service.leaveTeam(user_id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                team.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                team.setValue(null);
            }
        });
    }

    public void joinTeam(String name, String join_code){
        TeamService service = NetworkUtils.getRetrofitInstance().create(TeamService.class);
        Call<Team> call = service.joinTeam(name, join_code);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                team.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                team.setValue(null);
            }
        });
    }

    public LiveData<Team> getTeamData(){
        return team;
    }
}
