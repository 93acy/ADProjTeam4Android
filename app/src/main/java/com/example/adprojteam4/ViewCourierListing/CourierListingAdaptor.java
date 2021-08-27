package com.example.adprojteam4.ViewCourierListing;

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

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourierListingAdaptor extends RecyclerView.Adapter<CourierListingAdaptor.ViewHolder> {

    private Context context;
    private List<List<List<String>>> courierListings;
    //private List<List<String>> courierListingDetails;
    private Double foodPrice = 0.0;
    private Double serviceFee;
    private Double totalPrice;
    private String foodItem ="";
    private String pricePerUnit ="";
    private String totalQuantity ="";
    //private Integer size;

    public CourierListingAdaptor(Context context, List<List<List<String>>> courierListings) {
        this.context = context;
        this.courierListings = courierListings;
        //this.size = size;
        //this.courierListingDetails = courierListingDetails;
    }

    @NonNull
    @Override
    public CourierListingAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.view_courier_listing, parent, false);
        return new CourierListingAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourierListingAdaptor.ViewHolder holder, int position) {

        //holder.txtHawkerName.setText(courierListings.get(position).get(0));

        //holder.txtHawkerName.setText(courierListings.get(position).get(0).get(0));
        //for(int i=0; i<size; i++){
        for(List<String> s : courierListings.get(position)){

            holder.txtHawkerName.setText(s.get(0));
            foodItem += s.get(1) + System.getProperty ("line.separator");
            pricePerUnit += s.get(2) + System.getProperty ("line.separator");
            totalQuantity += s.get(3) + System.getProperty ("line.separator");
            holder.txtCourierListingDetailsFoodItem.setText(foodItem);
            holder.txtCourierListingDetailsPricePerUnit.setText(pricePerUnit);
            holder.txtCourierListingDetailsTotalQuantity.setText(totalQuantity);

        }
        //holder.txtCourierListingDetailsFoodItem.setText(foodItem);
        //holder.txtCourierListingDetailsPricePerUnit.setText(pricePerUnit);
        //holder.txtCourierListingDetailsTotalQuantity.setText(totalQuantity);
        //holder.txtCourierListingDetailsFoodItem.setText(courierListings.get(position).get(1));
        //holder.txtCourierListingDetailsPricePerUnit.setText(courierListings.get(position).get(2));
        //holder.txtCourierListingDetailsTotalQuantity.setText(courierListings.get(position).get(3));
        for(List<String> s : courierListings.get(position)){
            foodPrice = foodPrice + Double.parseDouble(s.get(2)) * Double.parseDouble(s.get(3));
            holder.txtTotalFoodPrice.setText("Total Food Price:  " + foodPrice.toString());

        }
        //holder.txtTotalFoodPrice.setText("Total Food Price:  " + foodPrice.toString());
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
                Intent intent = new Intent();

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
                .getInstance()
                .getCourierListingAPI()
                .cancelCourierListing(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                /*if (!response.isSuccessful()) {
                    Toast.makeText(context, "fail to get response", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.body().equals("successful")) {
                    //notifyItemRemoved(position);
                    //notifyDataSetChanged();
                    Toast.makeText(context, "delete successfully", Toast.LENGTH_LONG).show();
                }*/

                String s = "";
                try {
                    s += response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("successful")) {
                    Toast.makeText(context, "delete successfully", Toast.LENGTH_LONG).show();
                    //notifyItemRemoved(position);
                    //notifyDataSetChanged();
                    Intent intent = new Intent(context, ViewCourierListing.class);
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
