package com.example.adprojteam4.CourierListing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.CourierListing.FoodAdaptor;
import com.example.adprojteam4.CourierListing.ViewFoodItem;
import com.example.adprojteam4.OrderFunction.CourierListingAdaptor;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.media.CamcorderProfile.get;

public class courier_ViewCourierListing extends AppCompatActivity {

    RecyclerView recyclerView;
    courier_CourierListingAdapter cAdaptor;
    RecyclerView.LayoutManager layoutManager;
    List<List<List<String>>> courierListings = new ArrayList<>();
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier_view_courier_listing);

        bottomNav = findViewById(R.id.bottomNavbar);
        bottomNav.setSelectedItemId(R.id.nav_courierSearch);

        recyclerView = findViewById(R.id.rvCourierListing);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cAdaptor = new courier_CourierListingAdapter(this, courierListings);
        recyclerView.setAdapter(cAdaptor);

        getCourierListing();
    }

    private void getCourierListing() {

        Call<List<List<List<String>>>> call = RetrofitClient
                .getInstance(this)
                .getCourierListingAPI()
                .viewCourierListings();

        call.enqueue(new Callback<List<List<List<String>>>>() {
            @Override
            public void onResponse(Call<List<List<List<String>>>> call, Response<List<List<List<String>>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(courier_ViewCourierListing.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.body() != null) {
                    courierListings.addAll(response.body());
                    //Log.i("courierListings",courierListings.get(0).get(0).get(0));
                    cAdaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<List<List<String>>>> call, Throwable t) {
                Toast.makeText(courier_ViewCourierListing.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}