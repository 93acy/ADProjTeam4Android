package com.example.adprojteam4.OrderFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ViewOrderStatus extends AppCompatActivity {
    List<List<String>>orderFoodData = new ArrayList<>();
    ArrayList<ArrayList<String>> pickupDetailsData = new ArrayList<>();
    TextView hawkerName,pickupTime,pickupLocation;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    UserOrderAdaptor adaptor;
    Long userOrderId;
    Button cancel,received;
    ConstraintLayout btmCl,topCl;
    RelativeLayout rl;
    String orderStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_status);

        userOrderId = getIntent().getLongExtra("userOrderId",0);

        btmCl = findViewById(R.id.btm_cl);
        topCl=findViewById(R.id.top_cl);
        rl = findViewById(R.id.relativate);
        hawkerName = findViewById(R.id.hawker_name);
        pickupTime = findViewById(R.id.pickup_time);
        pickupLocation = findViewById(R.id.pickup_location);
        received = findViewById(R.id.received_btn);
        cancel = findViewById(R.id.cancel_btn);


        recyclerView = findViewById(R.id.foodList_rv);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new UserOrderAdaptor(ViewOrderStatus.this,orderFoodData);
        recyclerView.setAdapter(adaptor);

        getPickupDetailsInUserOrder();
        getOrderFoodItem();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderStatus = "Cancled";
                updateOrderStatus();
            }
        });

        received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderStatus = "Received";
                updateOrderStatus();
            }
        });

    }


    private void getPickupDetailsInUserOrder() {

        Call<ArrayList<ArrayList<String>>> call = RetrofitClient
                .getInstance(this)
                .getCourierListingAPI()
                .viewPickupDetails(userOrderId);

        call.enqueue(new Callback<ArrayList<ArrayList<String>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<String>>> call, Response<ArrayList<ArrayList<String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewOrderStatus.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.body() != null) {
                    pickupDetailsData.addAll(response.body());
                    pickupLocation.setText("Pickup Location:" + pickupDetailsData.get(0).get(1));
                    pickupTime.setText("Pickup Time: "+ pickupDetailsData.get(0).get(2));
                    hawkerName.setText(pickupDetailsData.get(0).get(3));

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<String>>> call, Throwable t) {
                Toast.makeText(ViewOrderStatus.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getOrderFoodItem() {
        Call<ArrayList<ArrayList<String>>> call = RetrofitClient
                .getInstance(this)
                .getCourierListingAPI()
                .viewOrderFoodItems(userOrderId);

        call.enqueue(new Callback<ArrayList<ArrayList<String>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<String>>> call, Response<ArrayList<ArrayList<String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewOrderStatus.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.body() != null) {
                    orderFoodData.addAll(response.body());
                    adaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<String>>> call, Throwable t) {
                Toast.makeText(ViewOrderStatus.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateOrderStatus() {
        Call<ResponseBody> call = RetrofitClient
                .getInstance(this)
                .getCourierListingAPI()
                .updateCourierOrderStatusById(userOrderId,orderStatus);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                try {
                    s += response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("SUCCESS")) {
                    Toast.makeText(ViewOrderStatus.this, "Your order has been "+orderStatus.toLowerCase(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ViewOrderStatus.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }



}