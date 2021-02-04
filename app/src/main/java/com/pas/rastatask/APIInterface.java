package com.pas.rastatask;

import com.pas.rastatask.retroalltask.Task;
import com.pas.rastatask.retrologin.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/api/v1/task")
    Call<Task> getAllTask(@Header("Authorization") String token,
                           @Query("password") String password);

    @GET("/api/v1/login")
    Call<Login> getLogin(@Query("username") String username, @Query("password") String password, @Query("pwdsalt") String pwdsalt);
}