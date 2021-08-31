package com.example.adprojteam4.OrderFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adprojteam4.CourierListing.ViewFoodItem;
import com.example.adprojteam4.DashboardActivity;
import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Query;

public class ViewBill extends AppCompatActivity {
    TextView title,foodPrice,serviceFee,totalCost;
    Button confirm,cancle;
    Double foodFee =0.0;
    Long CourierListingId;
    ArrayList<Long> CourierFoodDetailId = new ArrayList<>();
    ArrayList<Integer> Quantity = new ArrayList<>();
    Long userOrderId ;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);



        foodFee = getIntent().getDoubleExtra("foodFee",0);
        Quantity = getIntent().getIntegerArrayListExtra("Quantity");
        CourierListingId =getIntent().getLongExtra("CourierListingId",0);
        ArrayList<String> temp = getIntent().getStringArrayListExtra("CourierFoodDetailId");

        if(temp !=null){
            for(String id:temp){
                CourierFoodDetailId.add(Long.parseLong(id));
            }
        }
        else
            Toast.makeText(ViewBill.this, "CourierFoodDetailId is null", Toast.LENGTH_LONG).show();

        setUpView();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUserOrder();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ViewBill.this, ViewCourierListing.class);
                ViewBill.this.startActivity(intent);
            }
        });
    }

    public void setUpView(){
        title = findViewById(R.id.title);
        foodPrice = findViewById(R.id.foodPrice);
        serviceFee = findViewById(R.id.serviceFee);
        totalCost = findViewById(R.id.totalCost);
        confirm = findViewById(R.id.bill_confirm);
        cancle = findViewById(R.id.bill_cancle);


        DecimalFormat df = new DecimalFormat("#,###.##");

        String s = df.format(foodFee*0.1);
        Double ServiceCost =Double.parseDouble(s);
        foodPrice.setText("Total food price: "+String.valueOf(foodFee));
        serviceFee.setText("Service fee(10%): "+String.valueOf(ServiceCost));
        totalCost.setText("Total cost: "+String.valueOf(foodFee+ServiceCost));
    }


    public void createUserOrder(){
        UserOrder userOrder = new UserOrder(foodFee);

        Call<String> call = RetrofitClient
                .getInstance(this)
                .getUserAPI()
                .createUserOrder(userOrder,CourierListingId);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewBill.this, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.body() != null) {
                    userOrderId=Long.parseLong(response.body());
                    createUseOrderDetail();
                    intent = new Intent(ViewBill.this, DashboardActivity.class);
                    intent.putExtra("CourierListingId",CourierListingId);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                intent = new Intent(ViewBill.this,ViewCourierListing.class);
                //intent.putExtra("userOrderId",userOrderId);
                Toast.makeText(ViewBill.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void createUseOrderDetail(){
        List<UserOrderDetail> userOrderDetails = new ArrayList<>();
        for(Integer q: Quantity){
            userOrderDetails.add(new UserOrderDetail(q));
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance(this)
                .getUserAPI()
                .createUserOrderDetail(userOrderDetails,userOrderId,CourierFoodDetailId);



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
                    Toast.makeText(ViewBill.this, "Your order has been placed", Toast.LENGTH_LONG).show();
                    ViewBill.this.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ViewBill.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }
}