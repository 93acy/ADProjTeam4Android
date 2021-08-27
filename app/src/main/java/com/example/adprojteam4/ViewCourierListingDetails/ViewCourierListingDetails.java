package com.example.adprojteam4.ViewCourierListingDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;
import com.example.adprojteam4.ViewCourierListing.CourierListingAdaptor;
import com.example.adprojteam4.ViewCourierListing.ViewCourierListing;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCourierListingDetails extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courier_listing_details);

        recyclerView = findViewById(R.id.rvCourierListing);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cAdaptor = new CourierListingAdaptor(this, courierListings);
        recyclerView.setAdapter(cAdaptor);

        getCourierListing();
    }

    private void getCourierListing() {

        Call<List<List<List<String>>>> call = RetrofitClient
                .getInstance()
                .getCourierListingAPI()
                .viewCourierListings();

        call.enqueue(new Callback<List<List<List<String>>>>() {
            @Override
            public void onResponse(Call<List<List<List<String>>>> call, Response<List<List<List<String>>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewCourierListing.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.body() != null) {
                    courierListings.addAll(response.body());
                    //size = courierListings.size();
                    cAdaptor.notifyDataSetChanged();
                    //cAdaptor.notifyItemRangeInserted(0,2);

                }
            }

            @Override
            public void onFailure(Call<List<List<List<String>>>> call, Throwable t) {
                Toast.makeText(ViewCourierListing.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/
}