package com.example.adprojteam4.OrderFunction;

import com.example.adprojteam4.HawkerListing;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CourierListingAPI {
    @GET("/users/courierListing")
    Call<ArrayList<ArrayList<String>>> viewAllCourierListings();

    @GET("/users/courierListing/{id}")
    Call<ArrayList<ArrayList<String>>> selectFoodItems
            (@Path("id") Long courierListingId,@Query("hawkerId") Long hawkerId);

    @GET("/users/courierListingPickup/{id}")
    Call<ArrayList<ArrayList<String>>> viewPickupDetails
            (@Path("id") Long userOrderId);

    @GET("/users/orders/foodItems/{id}")
    Call<ArrayList<ArrayList<String>>> viewOrderFoodItems
            (@Path("id") Long userOrderId);

    @PUT("users/orders/orderStatus/update")
    Call<ResponseBody> updateCourierOrderStatusById (
            @Path("id") Long userOrderId,
            @Query("status") String status);

}
