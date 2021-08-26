package com.example.adprojteam4;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface HawkerListingAPI {

    @GET("/hawkerlisting/All")
    Call<List<List<String>>> viewAllHawkerListings();

    @POST("/hawkerlisting/add")
    Call<ResponseBody> addNewHawkerListing (
            @Body HawkerListing hawkerListing
    );


    @PUT("/hawkerlisting/update")
    Call<ResponseBody> updateHawkerListing (
        @Body HawkerListing hawkerListing
    );

//    @DELETE("/hawkerlistng/delete/{id}")
//    Call <ResponseBody>

}
