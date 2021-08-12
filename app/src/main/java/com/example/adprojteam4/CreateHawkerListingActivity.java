package com.example.adprojteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateHawkerListingActivity extends AppCompatActivity {

    private EditText hawkerName, hawkerAddress, hawkerPostalCode, hawkerStallNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hawker_listing);

        hawkerName = findViewById(R.id.hawkerName);
        hawkerAddress = findViewById(R.id.hawkerAddress);
        hawkerPostalCode = findViewById(R.id.hawkerPostalCode);
        hawkerStallNumber = findViewById(R.id.hawkerStallNumber);


        findViewById(R.id.btnRegister3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerhawker();
            }
        });
    }
    private void registerhawker() {
        String name = hawkerName.getText().toString().trim();
        String address = hawkerAddress.getText().toString().trim();
        String postalCode= hawkerPostalCode.getText().toString().trim();
        String stallNo = hawkerStallNumber.getText().toString().trim();

        if (name.isEmpty()) {
            hawkerName.setError("Stall Name is required");
            hawkerName.requestFocus();
            return;
        } else if (address.isEmpty()) {
            hawkerAddress.setError("Address is required");
            hawkerAddress.requestFocus();
            return;
        }
        // the rest can just fuck it

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getHawkerListingAPI()
                .addNewHawkerListing(new HawkerListing(name, address, postalCode, stallNo));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("SUCCESS")) {
                    Toast.makeText(CreateHawkerListingActivity.this, "Successfully Listed!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateHawkerListingActivity.this, ViewHawkerListingActivity.class));
                } else {
                    Toast.makeText(CreateHawkerListingActivity.this, "Listing already exists!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CreateHawkerListingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}