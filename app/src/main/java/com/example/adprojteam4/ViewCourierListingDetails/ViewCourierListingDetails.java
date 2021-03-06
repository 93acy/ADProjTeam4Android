package com.example.adprojteam4.ViewCourierListingDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.DashboardActivity;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCourierListingDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    CourierListingDetailsAdaptor cdAdaptor;
    RecyclerView.LayoutManager layoutManager;
    List<List<String>> courierListingDetails = new ArrayList<>();
    List<List<String>> courierListingDetailInfo = new ArrayList<>();
    TextView txtHawkersName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courier_listing_details);

        recyclerView = findViewById(R.id.rvCourierListingDetails);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cdAdaptor = new CourierListingDetailsAdaptor(this, courierListingDetails, courierListingDetailInfo);
        recyclerView.setAdapter(cdAdaptor);
        txtHawkersName = findViewById(R.id.txtHawkersName);
        Button home = findViewById(R.id.btnbackToHome);

        home.setOnClickListener(v->{
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        });


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        getCourierListingDetails(id);
        txtHawkersName.setText(id);
    }

    private void getCourierListingDetails(String id) {

        Call<List<List<String>>> call = RetrofitClient
                .getInstance(this)
                .getCourierListingDetailsAPI()
                .viewCourierListingDetails(id);

        call.enqueue(new Callback<List<List<String>>>() {
            @Override
            public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewCourierListingDetails.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.body() != null) {
                    courierListingDetails.addAll(response.body());
                    //cdAdaptor.notifyDataSetChanged();
                    for(int i=0; i<courierListingDetails.size(); i++){
                        getCourierListingDetailInfo(courierListingDetails.get(i).get(0));
                        //cdAdaptor.notifyDataSetChanged();
                    }
                    cdAdaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<List<String>>> call, Throwable t) {
                Toast.makeText(ViewCourierListingDetails.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getCourierListingDetailInfo(String id) {

        Call<List<List<String>>> call2 = RetrofitClient
                .getInstance(this)
                .getCourierListingDetailsAPI()
                .viewCourierListingDetailInfo(id);

        call2.enqueue(new Callback<List<List<String>>>() {
            @Override
            public void onResponse(Call<List<List<String>>> call1, Response<List<List<String>>> response1) {
                if (!response1.isSuccessful()) {
                    Toast.makeText(ViewCourierListingDetails.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response1.code() == 200) {
                    courierListingDetailInfo.addAll(response1.body());
                    cdAdaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<List<String>>> call1, Throwable t) {
                Toast.makeText(ViewCourierListingDetails.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}