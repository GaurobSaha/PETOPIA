package com.example.petopia.controller;

import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.IFragmentHomeView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHomeController implements IFragmentHomeController {


    IFragmentHomeView fragmentHomeView;
    Repository repository;
    private List<Event> eventList = new ArrayList<>();

    public FragmentHomeController(IFragmentHomeView FragmentHomeView) {
        this.fragmentHomeView = FragmentHomeView;
        this.repository = new Repository();
    }

    @Override
    public void onGetEvents() {

        try {
            repository.getEvents(new Callback<List<Event>>(){


                @Override
                public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Event> eventList = response.body();
                        fragmentHomeView.onGetEvents(eventList);
                    } else {
                        fragmentHomeView.onErrorEvents("Error: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Event>> call, Throwable t) {
                    fragmentHomeView.onErrorEvents("Exception: " + t.getMessage());
                }
            });
        }catch (Exception e){
            fragmentHomeView.onErrorEvents("Exception: " + e.getMessage());
        }



    }
}




//    @Override
//    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
//        if (response.isSuccessful() && response.body() != null) {
//            // Clear existing data
//            eventList.clear();
//
//            // Add new data
//            eventList.addAll(response.body());
//            // Extract imageUrls from Event objects
//            List<String> imageUrlList = new ArrayList<>();
//            for (Event event : eventList) {
//                imageUrlList.add(event.getImageUrl());
//            }
//
//            fragmentHomeView.onGetEvents(imageUrlList);
//
//            // Notify the adapter that the data has changed
//            // adapter.notifyDataSetChanged();
//        } else {
//            // Handle HTTP error
//            // Toast.makeText(getContext(), "HTTP Error: " + response.code(), Toast.LENGTH_SHORT).show();
//            fragmentHomeView.onErrorEvents("HTTP Error: " + response.code());
//        }
//    }
//
//    @Override
//    public void onFailure(Call<List<Event>> call, Throwable t) {
//        // Handle network failure
//        // Toast.makeText(getContext(), "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//        fragmentHomeView.onErrorEvents("Exception: " + t.getMessage());
//    }