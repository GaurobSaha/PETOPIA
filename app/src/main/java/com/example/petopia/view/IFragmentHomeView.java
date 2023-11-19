package com.example.petopia.view;

import com.example.petopia.model.pojo.Event;

import java.util.List;

public interface IFragmentHomeView {

    void onGetEvents(List<Event> eventList);

    void onErrorEvents(String message);


}
