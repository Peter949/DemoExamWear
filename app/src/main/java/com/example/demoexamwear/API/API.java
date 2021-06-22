package com.example.demoexamwear.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API
{
    public static Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su").build();

    @POST("/auth/login")
    Call<SignInParam> doAuth(@Body SignInParam signInParam);

    @GET("/movies?filter=new")
    Call<List<MovieParam>> getMovie();
}
