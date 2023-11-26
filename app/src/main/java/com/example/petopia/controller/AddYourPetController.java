package com.example.petopia.controller;

import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.pojo.ServerResponse;
import com.example.petopia.model.pojo.YourPet;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.AddYourPet;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddYourPetController {
    AddYourPet addYourPet;
    Repository repository;
    // private List<Event> eventList = new ArrayList<>();

    public AddYourPetController(AddYourPet addYourPet) {
        this.addYourPet = addYourPet;
        this.repository = new Repository();
    }

    public void onAddYourPet(String userId, String petName, String petType, String petAge, String petWeight, String gender, String encodeImage) {

        YourPet yourPet = new YourPet(userId, petName, petType, petAge, petWeight, gender, encodeImage);


        repository.addYourPet(yourPet, new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null && "Successful".equals(serverResponse.getMessage())) {
                        addYourPet.onSuccess(serverResponse.getMessage());
                    } else {
                        addYourPet.onError("Error on Adding you pet" + serverResponse.getMessage());
                    }
                } else {
                    addYourPet.onError("HTTP Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                addYourPet.onError("Exception: " + t.getMessage());
            }
        });

    }


    public void onDataChanged() {

    }

}
