package com.example.adprojteam4.CourierListing;


import com.example.adprojteam4.OrderFunction.CourierListing;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodAPI {
    @GET("/courier/hawker/{id}")
    Call<List<ArrayList<String>>> viewFoodItems(@Path("id") Long HawkerId);

    @POST("/courier/food")
    Call<ResponseBody> createFoodItem(@Body FoodItem foodItem, @Query("hawkerId") Long hawkerId);

    @POST("/courier/foodItemDetail")
    Call<ArrayList<String>> createCourierFoodItemDetails
            (@Body List<CourierFoodItemDetails> courierFoodItemDetails,
             @Query("foodIds") List<Long> foodIds);

    @POST("/courier/courierListing")
    Call<ResponseBody> createCourierListing
            (@Body CourierListing courierListing,
             @Query("courierFoodItemDetailIds") ArrayList<String> courierFoodItemDetailIds,
             @Query("FoodID") ArrayList<String> FoodID);

    @GET("/courier/food")
    Call<List<ArrayList<String>>> FoodItems();


}