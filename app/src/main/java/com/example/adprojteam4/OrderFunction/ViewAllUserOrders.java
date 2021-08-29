package com.example.adprojteam4.OrderFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adprojteam4.DashboardActivity;
import com.example.adprojteam4.ListingAdaptor;
import com.example.adprojteam4.MainActivity;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;
import com.example.adprojteam4.ViewHawkerListingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllUserOrders extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    UserOrderDetailAdaptor adaptor;
    List<List<String>> orderData = new ArrayList<>();
    BottomNavigationView bottomNav;
    Long userOrderId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_orders);
        userOrderId = getIntent().getLongExtra("userOrderId",0);

        bottomNav = findViewById(R.id.bottomNavbar);
        adaptor  = new UserOrderDetailAdaptor(this,orderData);
        recyclerView.setAdapter(adaptor);
        layoutManager = new LinearLayoutManager(this);


        findViewById((R.id.nav_myAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllUserOrders.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_courierSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllUserOrders.this, ViewCourierListing.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_hawkerSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllUserOrders.this, ViewHawkerListingActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllUserOrders.this, DashboardActivity.class);
                startActivity(intent);
            }});

        fetchListings();
    }

    private void fetchListings(){
        Call<ArrayList<ArrayList<String>>> call = RetrofitClient
                .getInstance(this)
                .getCourierListingAPI()
                .viewPickupDetails(userOrderId);

        call.enqueue(new Callback<ArrayList<ArrayList<String>>>() {

            @Override
            public void onResponse(Call<ArrayList<ArrayList<String>>> call, Response<ArrayList<ArrayList<String>>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(ViewAllUserOrders.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.body() != null) {
                    orderData.addAll(response.body());
                    adaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<String>>> call, Throwable t) {
                Toast.makeText(ViewAllUserOrders.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}