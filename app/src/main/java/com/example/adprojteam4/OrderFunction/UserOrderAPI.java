package com.example.adprojteam4.OrderFunction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserOrderAPI {

    @GET("/users/userorder")
    Call<ArrayList<ArrayList<String>>> viewAllUserOrder();
}
