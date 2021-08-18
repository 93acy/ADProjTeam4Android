package com.example.adprojteam4;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST("/register")
    Call<ResponseBody> register (
            @Body User user
    );

    @POST("/authenticate")
    Call<JwtResponse> authenticate(
            @Body JwtRequest jwtRequest
    );
}
