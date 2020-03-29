package com.example.mnallamalli97.lahacks2020.utilities;

import com.example.mnallamalli97.lahacks2020.TaskInstance;
import com.example.mnallamalli97.lahacks2020.pojo.UserId;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface TaskService {
    @POST("/tasks/byuser/")
    Call<List<TaskInstance>> getUserTasks(@Body UserId body);
}
