package com.example.petopia.networking;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class RetrofitClient {
//
//    private static final String BASE_URL = "https://petopia-pet.000webhostapp.com/";
//
//    private static final OkHttpClient client = new OkHttpClient.Builder().build();
//
//    private static final Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
//            .client(client)
//            .build();
//
//    private static final ApiService apiService = retrofit.create(ApiService.class);
//
//    // Private constructor to prevent instantiation
//    private RetrofitClient() {}
//
//    public static ApiService getApiService() {
//        return apiService;
//    }
//}


public class RetrofitClient {
    private static final String BASE_URL = "https://petopia-pet.000webhostapp.com/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}