package com.example.adprojteam4.ViewCourierListingDetails;

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
import java.util.List;


public class CourierListingDetailsAdaptor extends RecyclerView.Adapter<CourierListingDetailsAdaptor.ViewHolder> {

    private Context context;
    private List<List<String>> courierListingDetails;
    private List<List<String>> courierListingDetailInfo;
    String foodItem="";
    String foodQuantity="";


    public CourierListingDetailsAdaptor(Context context, List<List<String>> courierListingDetails, List<List<String>> courierListingDetailInfo) {
        this.context = context;
        this.courierListingDetails = courierListingDetails;
        this.courierListingDetailInfo = courierListingDetailInfo;
    }

    @NonNull
    @Override
    public CourierListingDetailsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.view_courier_listing_details, parent, false);
        return new CourierListingDetailsAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourierListingDetailsAdaptor.ViewHolder holder, int position) {


        holder.txtUserName.setText(courierListingDetails.get(position).get(2));
        holder.txtStatus.setText(courierListingDetails.get(position).get(3));

        for(List<String> s : courierListingDetailInfo){
            if(s.get(0).equals(courierListingDetails.get(position).get(0))){
                foodItem += s.get(1) + System.getProperty ("line.separator");
                holder.txtFoodItem.setText(foodItem);
                foodQuantity += s.get(2) + System.getProperty ("line.separator");
                holder.txtFoodQuantity.setText(foodQuantity);

            }
        }
        foodItem="";
        foodQuantity="";

    }

    @Override
    public int getItemCount() {
        return courierListingDetails.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtHawkersName, txtUserName, txtStatus, txtFoodItem, txtFoodQuantity;


        public ViewHolder(@NonNull View listingView){
            super(listingView);

            txtHawkersName = listingView.findViewById(R.id.txtHawkersName);
            txtUserName = listingView.findViewById(R.id.txtUserName);
            txtStatus = listingView.findViewById(R.id.txtStatus);
            txtFoodItem = listingView.findViewById(R.id.txtFoodItem);
            txtFoodQuantity = listingView.findViewById(R.id.txtFoodQuantity);
        }
    }
}
