package com.example.petopia.model.repository;

import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.pojo.User;
import com.example.petopia.networking.ApiService;
import com.example.petopia.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Repository {
    private ApiService apiService;

    public Repository() {
        this.apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public void loginUser(User user, Callback<ResponseUser> callback) {
        Call<ResponseUser> call = apiService.loginUser(user);
        call.enqueue(callback);
    }

    public void registerUser(User user, Callback<ResponseUser> callback) {
        Call<ResponseUser> call = apiService.registerUser(user);
        call.enqueue(callback);
    }

    public void getEvents(Callback<List<Event>> callback) {
        Call<List<Event>> call = apiService.getEvents();
        call.enqueue(callback);
    }
}
