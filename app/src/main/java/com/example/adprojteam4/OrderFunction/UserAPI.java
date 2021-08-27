package com.example.adprojteam4.OrderFunction;

import com.example.adprojteam4.CourierListing.CourierFoodItemDetails;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {
    @POST("/user/createOrder")
    Call<String> createUserOrder (@Body UserOrder userOrder,
                                  @Query("courierListngId") Long courierListngId);
    //need to update userId also

    @POST("/user/createOrderDetail")
    Call<ResponseBody> createUserOrderDetail (@Body List<UserOrderDetail> userOrderDetail,
                                              @Query("userOrderId") Long userOrderId,
                                              @Query("courierFoodItemDetail") List<Long> courierFoodItemDetail);
}
