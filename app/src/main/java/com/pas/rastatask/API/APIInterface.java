package com.pas.rastatask.API;

import com.google.gson.JsonObject;
import com.pas.rastatask.retroaddtask.AddTask;
import com.pas.rastatask.retroallstate.State;
import com.pas.rastatask.retroallstatus.Status;
import com.pas.rastatask.retroalltask.Task;
import com.pas.rastatask.retrologin.Login;
import com.pas.rastatask.retroonetask.OneTask;
import com.pas.rastatask.retrouser.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/api/v1/task")
    Call<Task> getAllTask(@Header("Authorization") String token,
                          @Query("password") String password
    );

    @GET("/api/v1/task")
    Call<OneTask> getTask(@Header("Authorization") String token,
                          @Query("password") String password,
                          @Query("idtask") String id
    );

    @Headers({"Content-Type: application/json"})
    @POST("/api/v1/task")
    Call<AddTask> setTask(@Header("Authorization") String token,
                          @Body JsonObject body
    );

    @GET("/api/v1/state")
    Call<State> getState(@Header("Authorization") String token,
                         @Query("password") String password
    );

    @GET("/api/v1/status")
    Call<Status> getStatus(@Header("Authorization") String token,
                           @Query("password") String password
    );

    @GET("/api/v1/user")
    Call<User> getUser(@Header("Authorization") String token,
                       @Query("password") String password
    );

    @GET("/api/v1/login")
    Call<Login> getLogin(@Query("username") String username,
                         @Query("password") String password,
                         @Query("pwdsalt") String pwdsalt
    );

}

