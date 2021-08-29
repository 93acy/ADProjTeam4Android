package com.example.adprojteam4.CourierListing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.R;
import com.example.adprojteam4.RetrofitClient;
import com.example.adprojteam4.ViewCourierListingDetails.ViewCourierListingDetails;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class courier_CourierListingAdapter extends RecyclerView.Adapter<courier_CourierListingAdapter.ViewHolder> {
    private Context context;
    private List<List<List<String>>> courierListings;
    private Double foodPrice = 0.0;
    private Double serviceFee;
    private Double totalPrice;
    private String foodItem ="";
    private String pricePerUnit ="";
    private String totalQuantity ="";


    public courier_CourierListingAdapter(Context context, List<List<List<String>>> courierListings) {
        this.context = context;
        this.courierListings = courierListings;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.courier_view_courier_listing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        for(List<String> s : courierListings.get(position)){
            if (s.get(0)!=null){
            holder.txtHawkerName.setText(s.get(0));}
            if (s.get(1)!=null){
            foodItem += s.get(1) + System.getProperty ("line.separator");}
            if (s.get(2)!=null){
            pricePerUnit += s.get(2) + System.getProperty ("line.separator");}
            if (s.get(3)!=null){
            totalQuantity += s.get(3) + System.getProperty ("line.separator");}

            holder.txtCourierListingDetailsFoodItem.setText(foodItem);
            holder.txtCourierListingDetailsPricePerUnit.setText(pricePerUnit);
            holder.txtCourierListingDetailsTotalQuantity.setText(totalQuantity);
        }

        for(List<String> s : courierListings.get(position)){

            if (s.get(2)!=null && s.get(3)!=null){
            foodPrice = foodPrice + Double.parseDouble(s.get(2)) * Double.parseDouble(s.get(3));
            holder.txtTotalFoodPrice.setText("Total Food Price:  " + foodPrice.toString());
            }

        }

        serviceFee = foodPrice*0.1;
        holder.txtServiceFee.setText("Service fee: " + serviceFee.toString());
        totalPrice = foodPrice + serviceFee;
        holder.txtTotalPrice.setText("Total Price: " + totalPrice.toString());

        foodItem ="";
        pricePerUnit ="";
        totalQuantity ="";
        foodPrice = 0.0;

        holder.btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelCourierListing(v,position);
            }
        });

        holder.btnCloseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCourierListing(v,position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return courierListings.size();
    }

    private void cancelCourierListing(View v, int position){
        String id = courierListings.get(position).get(0).get(0);
        Call<ResponseBody> call = RetrofitClient
                .getInstance(context)
                .getCourierListingAPI()
                .cancelCourierListing(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String s = "";
                try {
                    s += response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("successful")) {
                    Toast.makeText(context, "delete successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, courier_ViewCourierListing.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateCourierListing(View v, int position){
        String id = courierListings.get(position).get(0).get(0);
        Call<ResponseBody> call = RetrofitClient
                .getInstance(context)
                .getCourierListingAPI()
                .updateCourierListing(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {
                    Toast.makeText(context, "close successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, ViewCourierListingDetails.class);
                    intent.putExtra("id", courierListings.get(position).get(0).get(0));
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtHawkerName, txtCourierListingDetailsFoodItem, txtCourierListingDetailsPricePerUnit, txtCourierListingDetailsTotalQuantity, txtTotalFoodPrice, txtServiceFee, txtTotalPrice;
        Button btnCloseOrder, btnCancelOrder;


        public ViewHolder(@NonNull View listingView){
            super(listingView);

            txtHawkerName = listingView.findViewById(R.id.txtHawkerName);
            txtCourierListingDetailsFoodItem = listingView.findViewById(R.id.txtCourierListingDetailsFoodItem);
            txtCourierListingDetailsPricePerUnit = listingView.findViewById(R.id.txtCourierListingDetailsPricePerUnit);
            txtCourierListingDetailsTotalQuantity = listingView.findViewById(R.id.txtCourierListingDetailsTotalQuantity);
            txtTotalFoodPrice = listingView.findViewById(R.id.txtTotalFoodPrice);
            txtServiceFee = listingView.findViewById(R.id.txtServiceFee);
            txtTotalPrice = listingView.findViewById(R.id.txtTotalPrice);
            btnCloseOrder = listingView.findViewById(R.id.closeOrder);
            btnCancelOrder = listingView.findViewById(R.id.cancelOrder);
        }
    }
}
