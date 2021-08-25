package com.example.adprojteam4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientML {

    private static  final String BASE_URL = "http://10.0.2.2:5000";
    private static RetrofitClientML mInstance;
    private Retrofit retrofit;


    private RetrofitClientML() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClientML getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClientML();
        }
        return mInstance;
    }


    public APIML getAPIML () {
        return retrofit.create(APIML.class);
    }

}
