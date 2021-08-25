package com.example.adprojteam4.OrderFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.CourierListing.ViewFoodItem;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectFood extends AppCompatActivity {
    ArrayList<String> courierListingData = new ArrayList<>();
    ArrayList<ArrayList<String>> foodData= new ArrayList<>();
    TextView hawkerName,orderBefore,pickupTime,pickupLocation,separator;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SelectFoodAdaptor adaptor;
    Long CourierListingId,hawkerId;
    Button cancle,confirm;
    ConstraintLayout btn,text;
    RelativeLayout rl;
    List<Integer> Quantity = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);

        courierListingData.addAll(getIntent().getStringArrayListExtra("courierListingData"));

        btn = findViewById(R.id.btn_cl);
        text = findViewById(R.id.text_cl);
        rl = findViewById(R.id.relativate);
        hawkerName = findViewById(R.id.hawker_name);
        orderBefore = findViewById(R.id.order_before);
        pickupTime = findViewById(R.id.pickup_time);
        pickupLocation = findViewById(R.id.pickup_location);
        separator = findViewById(R.id.separator);
        cancle = findViewById(R.id.cancle_btn);
        confirm = findViewById(R.id.confirm_btn);


        pickupTime.setText("Pickup Time:"+courierListingData.get(1));
        hawkerName.setText(courierListingData.get(2));
        pickupLocation.setText("Pickup Location:"+courierListingData.get(4));
        orderBefore.setText("Place order before:"+courierListingData.get(5));

        CourierListingId = Long.parseLong(courierListingData.get(0));
        hawkerId = Long.parseLong(courierListingData.get(6));

        recyclerView = findViewById(R.id.foodSelect_rv);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new SelectFoodAdaptor(SelectFood.this,foodData, Quantity);
        recyclerView.setAdapter(adaptor);

        getFoodItemInCourierListing();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectFood.this, ViewOrderStatus.class);
                //intent.putStringArrayListExtra("courierListingData", data);
                SelectFood.this.startActivity(intent);
            }

        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectFood.this, ViewCourierListing.class);
                SelectFood.this.startActivity(intent);
            }
        });



    }

    private void getFoodItemInCourierListing() {
        Call<ArrayList<ArrayList<String>>> call = RetrofitClient
                .getInstance()
                .getCourierListingAPI()
                .selectFoodItems(CourierListingId,hawkerId);

        call.enqueue(new Callback<ArrayList<ArrayList<String>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<String>>> call, Response<ArrayList<ArrayList<String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SelectFood.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.body() != null) {
                    foodData.addAll(response.body());
                    for(int i=0; i< foodData.size(); i++){
                        Quantity.add(0);
                    }
                    adaptor.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<String>>> call, Throwable t) {
                Toast.makeText(SelectFood.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}