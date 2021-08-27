package com.example.adprojteam4;

import com.example.adprojteam4.CourierListing.FoodAPI;
import com.example.adprojteam4.ViewCourierListing.CourierListingAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static  final String BASE_URL = "http://10.0.2.2:8080";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }


    public API getAPI () {
        return retrofit.create(API.class);
    }

    public HawkerListingAPI getHawkerListingAPI() {return retrofit.create(HawkerListingAPI.class);}

    public FoodAPI getFoodAPI() {
        return retrofit.create(FoodAPI.class);
    }

    public CourierListingAPI getCourierListingAPI() { return retrofit.create(CourierListingAPI.class); }
}
