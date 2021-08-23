package com.example.adprojteam4.CourierListing;


import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FoodAPI {
    @GET("/courier/hawker/{id}")
    Call<List<ArrayList<String>>> viewFoodItems(@Path("id") Long HawkerId);

    @POST("/courier/food")
    Call<ResponseBody> createFoodItem(@Body FoodItem foodItem);


}
