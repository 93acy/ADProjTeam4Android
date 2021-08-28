package com.example.adprojteam4;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIML {
    @POST("/recommend")
    Call<List<TempStall>> recommend (
            @Body UserPreferences userPreferences
    );
}
