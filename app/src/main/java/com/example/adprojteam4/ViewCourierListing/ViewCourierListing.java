package com.example.adprojteam4.ViewCourierListing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.CourierListing.FoodAdaptor;
import com.example.adprojteam4.CourierListing.ViewFoodItem;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCourierListing extends AppCompatActivity {

    RecyclerView recyclerView;
    CourierListingAdaptor cAdaptor;
    RecyclerView.LayoutManager layoutManager;
    List<List<List<String>>> courierListings = new ArrayList<>();
    //Integer size;
    //List<List<String>> courierListingDetails = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courier_listing);

        recyclerView = findViewById(R.id.rvCourierListing);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cAdaptor = new CourierListingAdaptor(this, courierListings);
        recyclerView.setAdapter(cAdaptor);


        getCourierListing();
        /*for(String s: courierListings){
            getCourierListingDetails(Long.parseLong(s));
        }*/

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
    }

    /*private void getCourierListingDetails(Long id) {

        Call<List<List<String>>> call = RetrofitClient
                .getInstance()
                .getCourierListingAPI()
                .viewCourierListingDetails(id);

        call.enqueue(new Callback<List<List<String>>>() {
            @Override
            public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewCourierListing.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }



                if (response.body() != null) {

                    courierListingDetails.addAll(response.body());
                    //detail.setText(courierListingDetails.get(0).get(1));
                    cAdaptor.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<List<String>>> call, Throwable t) {
                Toast.makeText(ViewCourierListing.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/
}