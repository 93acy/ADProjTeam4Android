package com.example.adprojteam4.CourierListing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.HawkerListing;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewFoodItem extends AppCompatActivity {

    RecyclerView recyclerView;
    ConstraintLayout foodForm;
    EditText enterFoodName;
    Button create;
    Button cancle;
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

        hawkerId = Long.parseLong(getIntent().getStringExtra("hawkerId"));

        recyclerView = findViewById(R.id.rv);
        confirm= findViewById(R.id.confirm);
        createFood= findViewById(R.id.createFood);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fAdaptor = new FoodAdaptor(this, foodItems,prices);
        recyclerView.setAdapter(fAdaptor);

        getFoodItem();

        createFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFoodItem();
            }
        });

    }

    private void getFoodItem() {
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

    private void createFoodItem() {
        foodForm = findViewById(R.id.foodForm);
        foodForm.setVisibility(View.VISIBLE);
        enterFoodName = findViewById(R.id.newFoodName);
        String name = enterFoodName.getText().toString().trim();
        create = findViewById(R.id.create);
        cancle = findViewById(R.id.cancle);


        //HawkerListing hawkerListing = new HawkerListing(hawkerId);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getFoodAPI()
                        .createFoodItem(new FoodItem(name));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = "";
                        try {
                            s += response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (s.equals("SUCCESS")) {
                            Toast.makeText(ViewFoodItem.this, "Successfully created!", Toast.LENGTH_LONG).show();
                            foodForm.setVisibility(View.INVISIBLE);
                            getFoodItem();

                        } else {
                            Toast.makeText(ViewFoodItem.this, "Food already exists!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(ViewFoodItem.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodForm.setVisibility(View.INVISIBLE);
            }
        });

    }
}






