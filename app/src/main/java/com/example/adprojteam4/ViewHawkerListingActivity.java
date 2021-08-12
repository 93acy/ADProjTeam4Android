package com.example.adprojteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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




    }
}