package com.example.adprojteam4.ViewCourierListingDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CourierListingDetailsAPI {

    @GET("/courier/viewCourierListingDetails/{id}")
    Call<List<List<String>>> viewCourierListingDetails(@Path("id") String id);

    @GET("/courier/viewCourierListingDetailInfo/{id}")
    Call<List<List<String>>> viewCourierListingDetailInfo(@Path("id") String id);

}
