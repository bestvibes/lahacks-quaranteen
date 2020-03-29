package com.example.mnallamalli97.lahacks2020;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mnallamalli97.lahacks2020.pojo.UserId;
import com.example.mnallamalli97.lahacks2020.utilities.NetworkUtils;
import com.example.mnallamalli97.lahacks2020.utilities.TaskService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskViewModel extends ViewModel {
    private static MutableLiveData<List<TaskInstance>> tasks;

//    public TaskViewModel(){
//        tasks = new MutableLiveData<List<TaskInstance>>();
//    }

    public static void getTasksByUser(int user_id){
        TaskService service = NetworkUtils.getRetrofitInstance().create(TaskService.class);
        UserId body = new UserId(user_id);
        Call<List<TaskInstance>> call = service.getUserTasks(body);
        call.enqueue(new Callback<List<TaskInstance>>() {
            @Override
            public void onResponse(Call<List<TaskInstance>> call, Response<List<TaskInstance>> response) {
                tasks.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TaskInstance>> call, Throwable t) {
                tasks.setValue(null);
            }
        });
    }

    public static LiveData<List<TaskInstance>> getUserTasks(){
        return tasks;
    }
}
