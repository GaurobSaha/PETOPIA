package com.example.petopia.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.petopia.Observer.DataObserver;
import com.example.petopia.R;
import com.example.petopia.adapter.PetAdapter;
import com.example.petopia.controller.FragmentHomeController;
import com.example.petopia.controller.IFragmentHomeController;
import com.example.petopia.model.pojo.Event;
import com.example.petopia.model.pojo.YourPet;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements IFragmentHomeView {

    private ImageSlider imageSlider;
    TextView addYourPet;
    IFragmentHomeController fragmentHomeController;
    RecyclerView rvYourPet;
    PetAdapter petAdapter;
    List<YourPet> petList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.image_sliderEvent);
        addYourPet = view.findViewById(R.id.TvAddYourPet);
        rvYourPet = view.findViewById(R.id.RvYourPets);

        fragmentHomeController = new FragmentHomeController(this, getContext());


        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
            String userID =  sharedPreferences.getString("user_id", "");
            fragmentHomeController.onGetEvents();
            fragmentHomeController.onGetYourPet(userID);
        }catch (Exception e){
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        addYourPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddYourPet.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void slideImage(ArrayList<SlideModel> imageList){
        imageSlider.setImageList(imageList);
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

    @Override
    public void onGetYourPetError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetYourPetSuccess(List<YourPet> yourPetList) {
        rvYourPet.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        petAdapter = new PetAdapter(getContext());
        rvYourPet.setAdapter(petAdapter);
        fragmentHomeController.addObserver(petAdapter);
    }



    @Override
    public void onDestroy() {
        fragmentHomeController.removeObserver(petAdapter);
        super.onDestroy();
    }
}