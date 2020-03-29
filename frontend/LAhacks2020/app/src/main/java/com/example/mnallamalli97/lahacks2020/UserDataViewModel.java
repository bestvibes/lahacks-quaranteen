package com.example.mnallamalli97.lahacks2020;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mnallamalli97.lahacks2020.utilities.NetworkUtils;
import com.example.mnallamalli97.lahacks2020.utilities.UserService;

import java.util.List;

public class UserDataViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    private MutableLiveData<User> updatedUser;

    public UserDataViewModel() {
        users = new MutableLiveData<List<User>>();
        updatedUser = new MutableLiveData<User>();
    }

    public void loadListUsers() {
        if (users.getValue() == null){
            UserService service = NetworkUtils.getRetrofitInstance().create(UserService.class);
            Call<List<User>> call = service.listUsers();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    users.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    users.setValue(null);
                }
            });
        }
    }

    public void updateUser(int user_id, String name) {
        UserService service = NetworkUtils.getRetrofitInstance().create(UserService.class);
        Call<User> call = service.updateUserName(user_id, name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                updatedUser.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                users.setValue(null);
            }
        });
    }

    public LiveData<List<User>> getUsersLiveData(){
        return users;
    }

    public LiveData<User> getUpdatedUserLiveData() {
        return updatedUser;
    }
}
