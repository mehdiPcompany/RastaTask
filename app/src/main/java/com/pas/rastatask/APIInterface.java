package com.pas.rastatask;

import com.pas.rastatask.retrofitdata.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/comments")
    Call<List<Response>> getHeroList(@Query("postId") Integer id);
}