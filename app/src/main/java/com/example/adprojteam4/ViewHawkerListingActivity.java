package com.example.adprojteam4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    ArrayList<HawkerListing> hawkerListingList= new ArrayList<>();
    EditText keywordSearch;
    BottomNavigationView bottomNav;
    LinearLayoutManager recommendationsLayoutManager;
    RecyclerView recommendationsRecyclerView;
    Button editUserPrefs;
    RecommendationsAdaptor recommendationsAdaptor;
    ArrayList<TempStall> recommendationsListingList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hawker_listing);

        Call<List<HawkerListing>> call = RetrofitClient
                .getInstance()
                .getHawkerListingAPI()
                .viewAllHawkerListings();

        keywordSearch = findViewById(R.id.hlist_search_bar);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new ListingAdaptor(hawkerListingList);
        recyclerView.setAdapter(adaptor);
        bottomNav = findViewById(R.id.bottomNavbar);

        recommendationsRecyclerView = findViewById(R.id.recommendationsRecyclerView);
        recommendationsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendationsRecyclerView.setLayoutManager(recommendationsLayoutManager);
        recommendationsAdaptor = new RecommendationsAdaptor(recommendationsListingList);
        recommendationsRecyclerView.setAdapter(recommendationsAdaptor);

        findViewById(R.id.addNewListing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ViewHawkerListingActivity.this, CreateHawkerListingActivity.class);
                startActivity(intent);

            }
        });
        editUserPrefs = findViewById(R.id.editUserPrefs);
        editUserPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewHawkerListingActivity.this, UserPreferencesActivity.class);
                startActivity(intent);
            }
        });

        fetchListings();

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

        SharedPreferences userPrefs = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String foodType = userPrefs.getString("foodType", "");
        String json1 = userPrefs.getString("carbType", "");
        String json2 = userPrefs.getString("proteinType", "");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> carbType = gson.fromJson(json1, type);
        ArrayList<String> proteinType = gson.fromJson(json2, type);

        Call<List<TempStall>> recommendations = RetrofitClientML
                .getInstance()
                .getAPIML()
                .recommend(new UserPreferences(foodType, carbType, proteinType));
        recommendations.enqueue(new Callback<List<TempStall>>() {
            @Override
            public void onResponse(Call<List<TempStall>> call, Response<List<TempStall>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    recommendationsListingList.addAll(response.body());
                    recommendationsAdaptor.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<TempStall>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ViewHawkerListingActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        }

        private void filter(String text) {
            ArrayList<HawkerListing> filteredList = new ArrayList<>();

            for (HawkerListing item: hawkerListingList){
                if(item.getStallNo().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
                }
            }
            adaptor.filterList(filteredList);

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
