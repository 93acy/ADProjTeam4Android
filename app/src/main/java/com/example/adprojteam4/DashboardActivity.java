package com.example.adprojteam4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.OrderFunction.SelectFood;
import com.example.adprojteam4.OrderFunction.SelectFoodAdaptor;
import com.example.adprojteam4.OrderFunction.ViewCourierListing;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    Button editUserPrefs;
    BottomNavigationView bottomNav;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    LinearLayoutManager layoutManager;
    DashboardAdaptor adaptor;
    ArrayList<ArrayList<ArrayList<String>>> orderData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNav = findViewById(R.id.bottomNavbar);
        bottomNav.setSelectedItemId(R.id.nav_home);
        relativeLayout = findViewById(R.id.dashBroad_rl);
        recyclerView = findViewById(R.id.dashBroad_rv);

        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new DashboardAdaptor(DashboardActivity.this,orderData);
        recyclerView.setAdapter(adaptor);

        findViewById((R.id.nav_myAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_courierSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ViewCourierListing.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_hawkerSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ViewHawkerListingActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                startActivity(intent);
            }});


        String welcomeText = "Welcome " + getIntent().getStringExtra("username") + "!";
        Toast.makeText(DashboardActivity.this, welcomeText, Toast.LENGTH_LONG).show();


        editUserPrefs = findViewById(R.id.editUserPrefs);
        editUserPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, UserPreferencesActivity.class);
                startActivity(intent);
            }
        });

        getOrderData();

    }

    private void getOrderData(){
        Call<ArrayList<ArrayList<ArrayList<String>>>> call = RetrofitClient
                .getInstance(this)
                .getUserAPI()
                .viewOrderDta();

        call.enqueue(new Callback<ArrayList<ArrayList<ArrayList<String>>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<ArrayList<String>>>> call, Response<ArrayList<ArrayList<ArrayList<String>>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.body() != null) {
                    orderData.addAll(response.body());
                    adaptor.notifyDataSetChanged();

                }
            }




            @Override
            public void onFailure(Call<ArrayList<ArrayList<ArrayList<String>>>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }}