package com.example.adprojteam4.OrderFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adprojteam4.CourierListing.courier_ViewCourierListing;
import com.example.adprojteam4.CreateHawkerListingActivity;
import com.example.adprojteam4.DashboardActivity;
import com.example.adprojteam4.HawkerListing;
import com.example.adprojteam4.ListingAdaptor;
import com.example.adprojteam4.MainActivity;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;
import com.example.adprojteam4.ViewHawkerListingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCourierListing extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    CourierListingAdaptor adaptor;
    ArrayList<ArrayList<String>> courierListingData = new ArrayList<>();
    EditText keywordSearch;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courier_listing);

        bottomNav = findViewById(R.id.bottomNavbar);
        bottomNav.setSelectedItemId(R.id.nav_courierSearch);

        keywordSearch = findViewById(R.id.Clist_search_bar);
        progressBar = findViewById(R.id.C_progressBar);
        recyclerView = findViewById(R.id.C_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new CourierListingAdaptor(ViewCourierListing.this, courierListingData);
        recyclerView.setAdapter(adaptor);

        getCourierListing();


        findViewById((R.id.nav_myAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCourierListing.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_courierSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCourierListing.this, ViewCourierListing.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_hawkerSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCourierListing.this, ViewHawkerListingActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCourierListing.this, DashboardActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.viewMyListing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCourierListing.this, courier_ViewCourierListing.class);
                startActivity(intent);
            }
        });

        keywordSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void getCourierListing() {
        Call<ArrayList<ArrayList<String>>> call = RetrofitClient
                .getInstance(this)
                .getCourierListingAPI()
                .viewAllCourierListings();

        call.enqueue(new Callback<ArrayList<ArrayList<String>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<String>>> call, Response<ArrayList<ArrayList<String>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    courierListingData.addAll(response.body());
                    adaptor.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<String>>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ViewCourierListing.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void filter(String text) {
        ArrayList<ArrayList<String>> filteredList = new ArrayList<>();

        if (courierListingData.isEmpty()) {
            Toast.makeText(ViewCourierListing.this, "There is no courier listing now", Toast.LENGTH_SHORT).show();
        } else {
            for (ArrayList<String> item : courierListingData) {
                for (String string : item) {
                    if (string != null && (string.toLowerCase().contains(text.toLowerCase()))) {
                        if (!filteredList.contains(item)) {
                            filteredList.add(item);
                        }
                    }
                }
                adaptor.filterList(filteredList);

            }

        }
    }
}