package com.example.mnallamalli97.lahacks2020;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mnallamalli97.lahacks2020.pojo.UserIdJoinCode;
import com.example.mnallamalli97.lahacks2020.utilities.NetworkUtils;
import com.example.mnallamalli97.lahacks2020.utilities.TeamService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDataViewModel extends ViewModel {
    private static MutableLiveData<Team> teamLiveData = new MutableLiveData<Team>();

    /*public TeamDataViewModel(){
        team = new MutableLiveData<Team>();
    }*/

    public static void createTeam(String name, int user_id){
        TeamService service = NetworkUtils.getRetrofitInstance().create(TeamService.class);
        Call<Team> call = service.createTeam(name, user_id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                teamLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                teamLiveData.setValue(null);
            }
        });
    }

    public static void leaveTeam(int user_id){
        TeamService service = NetworkUtils.getRetrofitInstance().create(TeamService.class);
        Call<Team> call = service.leaveTeam(user_id);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                teamLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                teamLiveData.setValue(null);
            }
        });
    }

    public static void joinTeam(int user_id, String join_code){
        TeamService service = NetworkUtils.getRetrofitInstance().create(TeamService.class);
        UserIdJoinCode body = new UserIdJoinCode(join_code, user_id);
        Call<Team> call = service.joinTeam(body);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                teamLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                teamLiveData.setValue(null);
            }
        });
    }

    public static LiveData<Team> getTeamData(){
        return teamLiveData;
    }
}
