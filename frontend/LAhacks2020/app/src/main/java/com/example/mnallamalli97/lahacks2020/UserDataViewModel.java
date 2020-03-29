package com.example.mnallamalli97.lahacks2020;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mnallamalli97.lahacks2020.utilities.NetworkUtils;
import com.example.mnallamalli97.lahacks2020.utilities.UserService;

public class UserDataViewModel extends ViewModel {
    private MutableLiveData<User> user;

    public UserDataViewModel() {
        user = new MutableLiveData<User>();
    }

    public void loadData() {
        if (user.getValue() == null){
            UserService service = NetworkUtils.getRetrofitInstance().create(UserService.class);
            Call<User> call = service.getUser();
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    user.setValue(response.body());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    user.setValue(null);
                }
            });
        }
    }

    public LiveData<User> getLiveData(){
        return user;
    }
}
