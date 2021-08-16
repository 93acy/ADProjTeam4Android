package com.example.adprojteam4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewHawkerListingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    ListingAdaptor adaptor;
    List<HawkerListing> hawkerListingList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hawker_listing);

        Call<List<HawkerListing>> call = RetrofitClient
                .getInstance()
                .getHawkerListingAPI()
                .viewAllHawkerListings();


        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new ListingAdaptor(hawkerListingList);
        recyclerView.setAdapter(adaptor);
        fetchListings();


        BottomNavigationView bottomNav = findViewById(R.id.bottomNavbar);

        }

    private void fetchListings() {
        RetrofitClient.getInstance().getHawkerListingAPI().viewAllHawkerListings().enqueue(new Callback<List<HawkerListing>>() {
            @Override
            public void onResponse(Call<List<HawkerListing>> call, Response<List<HawkerListing>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    hawkerListingList.addAll(response.body());
                    adaptor.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<HawkerListing>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ViewHawkerListingActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

//        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        Fragment selectedFragment = null;
//
//                        switch(item.getItemId()){
//                            case R.id.nav_home:
//                                startActivity(new Intent(getApplicationContext(), DashboardActiviy.class));
//                                overridePendingTransition(0,0);
//                                return true;
//                            case R.id.nav_hawkerSearch:
//                                startActivity(new Intent(getApplicationContext(), ViewHawkerListingActivity.class));
//                                overridePendingTransition(0,0);
//                                return true;
//                            case R.id.nav_courierSearch:
//                                startActivity(new Intent(getApplicationContext(), ViewCourierListingActivity.class));
//                                overridePendingTransition(0,0);
//                                return true;
//                            case R.id.nav_myAccount:
//                                startActivity(new Intent(getApplicationContext(), MyAccountActivity.class));
//                                overridePendingTransition(0,0);
//                                return true;
//                    }
//                    return false;
//                }
//
//
//    };
    }
