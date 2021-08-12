package com.example.adprojteam4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ViewHawkerListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hawker_listing);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getHawkerListingAPI()
                .viewAllHawkerListings();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavbar);

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
