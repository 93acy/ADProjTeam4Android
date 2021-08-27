package com.example.adprojteam4.CourierListing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adprojteam4.OrderFunction.CourierListing;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourierListingSummary extends AppCompatActivity {

    EditText pickupDate,pickupLocation,orderBeforeTime,pickupTime;
    Button confirmBtn;
    String puTime,puDate,puLocation,ordBeforeTime;
    long courierListingId;
    List<CourierListing> courierListing = new ArrayList<>();
    ArrayList<String> FoodID = new ArrayList<>();
    ArrayList<String> courierFoodItemDetailIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier_listing_summary);

        pickupDate = (EditText)findViewById(R.id.pickupDate);
        pickupLocation = (EditText)findViewById(R.id.pickupLocation);
        pickupTime = (EditText)findViewById(R.id.pickupTime);
        orderBeforeTime = (EditText)findViewById(R.id.orderBeforeTime);
        confirmBtn =findViewById(R.id.confirmBtn);

        FoodID=  (ArrayList<String>) getIntent().getSerializableExtra("FoodID");
        courierFoodItemDetailIds =  (ArrayList<String>) getIntent().getSerializableExtra("courierFoodItemDetailIds");

        getText();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCourierListingDetails();
            }
        });

        }

        private void getText(){
            pickupDate.setText(null);
            pickupLocation.setText(null);
            pickupTime.setText(null);
            orderBeforeTime.setText(null);

            pickupDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    puDate = pickupDate.getText().toString().trim();
                }
            });

            pickupTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    puTime = pickupTime.getText().toString().trim();
                }
            });

            pickupLocation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    puLocation = pickupLocation.getText().toString().trim();
                }
            });

            orderBeforeTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
                @Override
                public void afterTextChanged(Editable s) {
                    ordBeforeTime = orderBeforeTime.getText().toString().trim();
                }
            });
        }

        private void createCourierListingDetails(){

            CourierListing courierListing = new CourierListing(puLocation,puDate,puTime,ordBeforeTime,"Open");
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getFoodAPI()
                    .createCourierListing(courierListing,courierFoodItemDetailIds,FoodID);

            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String s = "";
                    try {
                        s = s + response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (s.equals("SUCCESS")) {
                        Toast.makeText(CourierListingSummary.this, "A new courier listing is created!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CourierListingSummary.this,CourierListingStatus.class);
                        CourierListingSummary.this.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(CourierListingSummary.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

    }
}