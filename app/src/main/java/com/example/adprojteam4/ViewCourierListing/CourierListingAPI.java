package com.example.adprojteam4.ViewCourierListing;

import com.example.adprojteam4.HawkerListing;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CourierListingAPI {

        @GET("/courier/viewCourierListings")
        Call<List<List<List<String>>>> viewCourierListings();

        /*@GET("/courier/viewCourierListingDetails/{id}")
        Call<List<List<String>>> viewCourierListingDetails(@Path("id") Long id);*/

        @DELETE("/courier/cancelCourierListing/{id}")
        Call<ResponseBody> cancelCourierListing(@Path("id") String id);

}
