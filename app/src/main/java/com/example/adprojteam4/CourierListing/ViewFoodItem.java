package com.example.adprojteam4.CourierListing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewFoodItem extends AppCompatActivity {

    RecyclerView recyclerView;
    Button confirm;
    Button createFood;
    List<ArrayList<String>> foodItems = new ArrayList<>();
    FoodAdaptor fAdaptor;
    RecyclerView.LayoutManager layoutManager;
    Long hawkerId;
    List<Double> prices = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_item);

        recyclerView = findViewById(R.id.rv);
        confirm= findViewById(R.id.confirm);
        createFood= findViewById(R.id.createFood);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fAdaptor = new FoodAdaptor(this, foodItems, prices);
        recyclerView.setAdapter(fAdaptor);

        getFoodItem();

    }

    private void getFoodItem() {
        hawkerId = Long.parseLong(getIntent().getStringExtra("hawkerId"));

        Call<List<ArrayList<String>>> call = RetrofitClient
                .getInstance()
                .getFoodAPI()
                .viewFoodItems(hawkerId);

        call.enqueue(new Callback<List<ArrayList<String>>>() {
            @Override
            public void onResponse(Call<List<ArrayList<String>>> call, Response<List<ArrayList<String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewFoodItem.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.body() != null) {
                    foodItems.addAll(response.body());
                    for(int i=0; i< foodItems.size(); i++){
                        prices.add(0.0);
                    }
                    fAdaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ArrayList<String>>> call, Throwable t) {
                Toast.makeText(ViewFoodItem.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}






