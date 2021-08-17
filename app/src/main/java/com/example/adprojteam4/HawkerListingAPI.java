package com.example.adprojteam4;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface HawkerListingAPI {

    @GET("/hawkerlisting/All")
    Call<List<HawkerListing>> viewAllHawkerListings();

    @POST("/hawkerlisting/add")
    Call<ResponseBody> addNewHawkerListing (
            @Body HawkerListing hawkerListing
    );

    @Multipart
    @POST("/hawkerlisting/upload/")
    Call<ResponseBody> uploadPhoto(
            @Part("hawkerListingId") RequestBody userId,
            @Part MultipartBody.Part photo
            );




    @PUT("/hawkerlisting/update")
    Call<ResponseBody> updateHawkerListing (
        @Body HawkerListing hawkerListing
    );



//    @DELETE("/hawkerlistng/delete/{id}")
//    Call <ResponseBody>

}
