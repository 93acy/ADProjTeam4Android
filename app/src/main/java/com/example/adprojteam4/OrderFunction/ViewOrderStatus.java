package com.example.adprojteam4.OrderFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrderStatus extends AppCompatActivity {
    ArrayList<String> courierListingData = new ArrayList<>();
    ArrayList<String>orderFoodData = new ArrayList<>();
    ArrayList<ArrayList<String>> pickupDetailsData = new ArrayList<ArrayList<String>>();
    TextView hawkerName,pickupTime,pickupLocation;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    UserOrderAdaptor adaptor;
    Long courierListingId,userOrderId,userId;
    Button cancel,received;
    ConstraintLayout btmCl,topCl;
    RelativeLayout rl;
    List<Integer> Quantity = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_status);

        orderFoodData.addAll(getIntent().getStringArrayListExtra("orderFoodData"));
        courierListingData.addAll(getIntent().getStringArrayListExtra("courierListingData"));
        btmCl = findViewById(R.id.btm_cl);
        topCl=findViewById(R.id.top_cl);
        rl = findViewById(R.id.relativate);
        hawkerName = findViewById(R.id.hawker_name);
        pickupTime = findViewById(R.id.pickup_time);
        pickupLocation = findViewById(R.id.pickup_location);
        received = findViewById(R.id.received_btn);
        cancel = findViewById(R.id.cancel_btn);


        courierListingId = Long.parseLong("2");
        userOrderId = Long.parseLong("1");
        userId = Long.parseLong("1");

        recyclerView = findViewById(R.id.foodList_rv);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        adaptor = new UserOrderAdaptor(ViewOrderStatus.this,foodData, Quantity);
        recyclerView.setAdapter(adaptor);

        getPickupDetailsInUserOrder();

        pickupTime.setText("Pickup Time: "+ pickupDetailsData.get(2));
//        hawkerName.setText(pickupDetailsData.get(2));
        pickupLocation.setText("Pickup Location:"+pickupDetailsData.get(1));

    }

    private void getPickupDetailsInUserOrder() {
        Call<ArrayList<ArrayList<String>>> call = RetrofitClient
                .getInstance()
                .getCourierListingAPI()
                .viewPickupDetails();

        call.enqueue(new Callback<ArrayList<ArrayList<String>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<String>>> call, Response<ArrayList<ArrayList<String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewOrderStatus.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.body() != null) {
                    pickupDetailsData.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<String>>> call, Throwable t) {
                Toast.makeText(ViewOrderStatus.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


//    private void getFoodItem() {
//
//        hawkerId = Long.parseLong(getIntent().getStringExtra("hawkerId"));
//        Call<List<ArrayList<String>>> call = RetrofitClient
//                .getInstance()
//                .getFoodAPI()
//                .viewFoodItems(hawkerId);
//
//        call.enqueue(new Callback<List<ArrayList<String>>>() {
//            @Override
//            public void onResponse(Call<List<ArrayList<String>>> call, Response<List<ArrayList<String>>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(ViewOrderStatus.this, "fail to get response", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                if (response.body() != null) {
//                    foodItems.addAll(response.body());
//                    for (int i = 0; i < foodItems.size(); i++) {
//                        prices.add(0.0);
//                    }
//                    adaptor.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ArrayList<String>>> call, Throwable t) {
//                Toast.makeText(ViewFoodItem.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });


}