package com.example.adprojteam4;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST("/users/register")
    Call<ResponseBody> createUser (
            @Body User user
    );

    @POST("/users/login")
    Call<ResponseBody> checkUser (
            @Body User user
    );
}
