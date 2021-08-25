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

import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCourierPickupDetails extends AppCompatActivity {

    EditText pickupDate,pickupLocation,orderBeforeTime,pickupTime;
    Button confirmBtn;
    String puTime,puDate,puLocation,ordBeforeTime;
    List<Long> courierListingIds = new ArrayList<>();
    List<CourierListing> courierListing = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courier_pickup_details);

        confirmBtn =findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCourierPickupDetails();
            }
        });

    }

    private void createCourierPickupDetails(){
        pickupDate = (EditText)findViewById(R.id.pickupDate);
        pickupLocation = (EditText)findViewById(R.id.pickupLocation);
        pickupTime = (EditText)findViewById(R.id.pickupTime);
        orderBeforeTime = (EditText)findViewById(R.id.orderBeforeTime);

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

        CourierListing courierListing = new CourierListing(puLocation,puDate,puTime,ordBeforeTime);
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getFoodAPI()
                .createCourierPickupDetails(courierListing,courierListingIds);

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
                    Toast.makeText(AddCourierPickupDetails.this, "Successfully created!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddCourierPickupDetails.this, FollowupActivity.class);
                    AddCourierPickupDetails.this.startActivity(intent);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddCourierPickupDetails.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}