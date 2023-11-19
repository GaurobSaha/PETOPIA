package com.example.petopia.networking;


import com.example.petopia.model.pojo.User;
import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.pojo.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("signup.php")
    Call<ResponseUser> registerUser(@Body User user);

    @POST("login.php")
    Call<ResponseUser> loginUser(@Body User user);

    @GET("events.php")
    Call<List<Event>> getEvents();

}