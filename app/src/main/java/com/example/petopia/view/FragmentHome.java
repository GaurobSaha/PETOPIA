package com.example.petopia.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.petopia.R;
import com.example.petopia.controller.FragmentHomeController;
import com.example.petopia.controller.IFragmentHomeController;
import com.example.petopia.model.pojo.Event;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements IFragmentHomeView {

    private ImageSlider imageSlider;
    IFragmentHomeController fragmentHomeController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fragmentHomeController = new FragmentHomeController(this);
        imageSlider = view.findViewById(R.id.image_sliderEvent);

        try {
            fragmentHomeController.onGetEvents();
        }catch (Exception e){
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }





        return view;
    }

    @Override
    public void onGetEvents(List<Event> eventList) {
        ArrayList<SlideModel> imageList = new ArrayList<>();
        for (Event event : eventList) {
            String imageUrl = "https://petopia-pet.000webhostapp.com/images/"+event.getImage();
            imageList.add(new SlideModel(imageUrl, ScaleTypes.CENTER_CROP));
        }
        slideImage(imageList);
    }

    @Override
    public void onErrorEvents(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


    private void slideImage(ArrayList<SlideModel> imageList){
        imageSlider.setImageList(imageList);
    }



}