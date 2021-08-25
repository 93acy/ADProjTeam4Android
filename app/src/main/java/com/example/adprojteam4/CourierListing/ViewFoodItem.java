package com.example.adprojteam4.CourierListing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ViewFoodItem extends AppCompatActivity {

    RecyclerView recyclerView;
    ConstraintLayout foodForm;
    EditText foodName,category,description;
    String name,cate,des;
    Button create,cancle;
    Button confirm,createFood;
    List<ArrayList<String>> foodItems = new ArrayList<>();
    FoodAdaptor fAdaptor;
    RecyclerView.LayoutManager layoutManager;
    Long hawkerId;
    List<Double> prices = new ArrayList<>();
    List<Long> foodIds = new ArrayList<>();
    List<CourierFoodItemDetails> courierFoodItemDetails = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_item);



        recyclerView = findViewById(R.id.rv);
        confirm= findViewById(R.id.confirm);
        createFood= findViewById(R.id.createFood);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fAdaptor = new FoodAdaptor(this, foodItems,prices,foodIds);
        recyclerView.setAdapter(fAdaptor);

        getFoodItem();

        createFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFoodItem();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCourierFoodItemDetail();
            }
        });

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

    private void createFoodItem() {
        foodForm = findViewById(R.id.foodForm);
        foodForm.setVisibility(View.VISIBLE);
        foodName = findViewById(R.id.newFoodName);

        category = (EditText)findViewById(R.id.categoryET);
        description = findViewById(R.id.descriptionET);
        create = findViewById(R.id.create);
        cancle = findViewById(R.id.cancle);
        foodName.setText(null);
        category.setText(null);
        description.setText(null);

        foodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                name = foodName.getText().toString().trim();
            }
        });

        category.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                cate = category.getText().toString().trim();
            }
        });

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                des = description.getText().toString().trim();
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItem newFood = new FoodItem(name,cate,des);
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getFoodAPI()
                        .createFoodItem(newFood,hawkerId);

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
                            Toast.makeText(ViewFoodItem.this, "Successfully created! ", Toast.LENGTH_LONG).show();
                            foodForm.setVisibility(View.INVISIBLE);
                            foodItems.clear();
                            prices.clear();
                            foodIds.clear();
                            getFoodItem();
                            Toast.makeText(ViewFoodItem.this, "Please add price again.", Toast.LENGTH_LONG).show();

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

    private void createCourierFoodItemDetail(){

        for(int i=0;i<prices.size();i++) {

            if (prices.get(i) == 0) {
                Toast.makeText(ViewFoodItem.this, "Price cannot be 0!", Toast.LENGTH_LONG).show();
                return;
            }
            else{
                courierFoodItemDetails.add(new CourierFoodItemDetails(prices.get(i)));
            }

            if(i==prices.size()-1){
                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getFoodAPI()
                        .createCourierFoodItemDetails(courierFoodItemDetails, foodIds);

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
                            Toast.makeText(ViewFoodItem.this, "Successfully created!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ViewFoodItem.this, AddCourierPickupDetails.class);
                            ViewFoodItem.this.startActivity(intent);

                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ViewFoodItem.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

        }
    }

}






