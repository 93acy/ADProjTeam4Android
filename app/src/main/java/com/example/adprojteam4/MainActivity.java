package com.example.adprojteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.adprojteam4.OrderFunction.ViewCourierListing;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences userPrefs = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
//        Log.d("hello",userPrefs.getString("IsLoggedIn", null));

        if (userPrefs.getString("IsLoggedIn", null)=="yes")
        {
            setContentView(R.layout.activity_main_loggedin);
            Log.d("hello", "onCreate: IsloggedIn");// change to another xml which includes just user profile text and logout and set button to log out only
        }

        else{
            setContentView(R.layout.activity_main);
            Log.d("hello", "onCreate: IsloggedOut");

            findViewById(R.id.btnToRegister).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                }
            });

            findViewById(R.id.btnToLogin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        }


        bottomNav = findViewById(R.id.bottomNavbar);
        bottomNav.setSelectedItemId(R.id.nav_myAccount);


        findViewById((R.id.nav_hawkerSearch)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, ViewHawkerListingActivity.class);
                startActivity(intent);
            }
        });

        findViewById((R.id.nav_myAccount)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        findViewById((R.id.nav_courierSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewCourierListing.class);
                startActivity(intent);
            }
        });


        findViewById((R.id.nav_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });





    }

}