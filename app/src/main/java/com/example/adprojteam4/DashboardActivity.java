package com.example.adprojteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adprojteam4.OrderFunction.ViewCourierListing;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    Button editUserPrefs;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNav = findViewById(R.id.bottomNavbar);
        bottomNav.setSelectedItemId(R.id.nav_home);

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
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);

        editUserPrefs = findViewById(R.id.editUserPrefs);
        editUserPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, UserPreferencesActivity.class);
                startActivity(intent);
            }
        });

    }
}