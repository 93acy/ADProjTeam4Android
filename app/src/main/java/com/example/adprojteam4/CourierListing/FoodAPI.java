package com.example.adprojteam4.CourierListing;


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
    Call<ResponseBody> createCourierFoodItemDetails
            (@Body List<CourierFoodItemDetails> courierFoodItemDetails, @Query("foodIds") List<Long> foodIds);

    @POST("/courier/courierPickupDetail")
    Call<ResponseBody> createCourierPickupDetails
            (@Body CourierListing courierPickupDetails, @Query("courierListingIds") Long courierListingIds);


}
