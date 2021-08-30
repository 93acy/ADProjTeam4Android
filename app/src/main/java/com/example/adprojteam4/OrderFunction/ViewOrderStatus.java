package com.example.adprojteam4.OrderFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.DashboardActivity;
import com.example.adprojteam4.MainActivity;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;
import com.example.adprojteam4.ViewHawkerListingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    BottomNavigationView bottomNav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_status);

        bottomNav = findViewById(R.id.bottomNavbar);
        bottomNav.setSelectedItemId(R.id.nav_hawkerSearch);

        userOrderId = Long.parseLong(getIntent().getStringExtra("userOrderId"));

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

        findViewById((R.id.nav_myAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewOrderStatus.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_courierSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewOrderStatus.this, ViewCourierListing.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_hawkerSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewOrderStatus.this, ViewHawkerListingActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewOrderStatus.this, DashboardActivity.class);
                startActivity(intent);
            }});

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderStatus = "Canceled";
                updateOrderStatus();
                Intent intent = new Intent(ViewOrderStatus.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderStatus = "Received";
                updateOrderStatus();
                Intent intent = new Intent(ViewOrderStatus.this, DashboardActivity.class);
                startActivity(intent);
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
                else{
                    Toast.makeText(ViewOrderStatus.this, "You may have received or cancled the order.".toLowerCase(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ViewOrderStatus.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }



}