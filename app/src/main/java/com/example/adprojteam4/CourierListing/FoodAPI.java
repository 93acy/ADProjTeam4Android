package com.example.adprojteam4.CourierListing;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodAPI {
    @GET("/courier/hawker/{id}")
    Call<List<ArrayList<String>>> viewFoodItems(@Path("id") Long HawkerId);

    @GET("/courier/food")
    Call<List<ArrayList<String>>> FoodItems();
}
