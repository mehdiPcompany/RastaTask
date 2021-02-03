package com.pas.rastatask;

import com.pas.rastatask.retrologin.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers({"Content-Type: application/json"})
    @GET("/api/v1/task")
    Call<List<Login>> getHeroList(@Header("Authorization") String token,
                            @Query("password") String password);


    @Headers({"Content-Type: application/json"})
    @GET("/api/v1/login")
    Call<Login> getLogin(@Query("username") String username, @Query("password") String password, @Query("pwdsalt") String pwdsalt);
}