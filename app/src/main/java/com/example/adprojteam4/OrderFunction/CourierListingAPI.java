
package com.example.adprojteam4.OrderFunction;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CourierListingAPI {
    @GET("/users/courierListing")
    Call<ArrayList<ArrayList<String>>> viewAllCourierListings();

    @GET("/users/courierListing/{id}")
    Call<ArrayList<ArrayList<String>>> selectFoodItems(@Path("id") Long courierListingId,@Query("hawkerId") Long hawkerId);

    @GET("/courier/viewCourierListings")
    Call<List<List<List<String>>>> viewCourierListings();

    @DELETE("/courier/cancelCourierListing/{id}")
    Call<ResponseBody> cancelCourierListing(@Path("id") String id);


}