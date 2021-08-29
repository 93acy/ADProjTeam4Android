package com.example.adprojteam4.OrderFunction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.CourierListing.ViewFoodItem;
import com.example.adprojteam4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UserOrderDetailAdaptor extends RecyclerView.Adapter<UserOrderDetailAdaptor.ViewHolder> {
    private Context context;
    private List<List<String>> orderData;
    public UserOrderDetailAdaptor(Context context, List<List<String>> orderFoodData) {
        this.context = context;
        this.orderData = orderData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.userorderlisting, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hawkerName.setText(orderData.get(position).get(3));
        holder.collectionLocation.setText(orderData.get(position).get(1));
        holder.collectionTime.setText(orderData.get(position).get(2));
        holder.userOrderStatus.setText(orderData.get(position).get(4));
        holder.collectionDate.setText(orderData.get(position).get(0));
        holder.userOrderDetailButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ViewFoodItem.class);
            String userOrderId = orderData.get(position).get(5);
            intent.putExtra("userOrderId",userOrderId);
            context.startActivity(intent);
        }
    });

    }
    @Override
    public int getItemCount() {
        return orderData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hawkerName,collectionTime,collectionLocation,userOrderStatus,collectionDate;
        FloatingActionButton userOrderDetailButton;
        CardView cardView;
        public ViewHolder(@NonNull View listingView) {
            super(listingView);
            hawkerName = listingView.findViewById(R.id.hawkerListingName);
            collectionLocation = listingView.findViewById(R.id.collectionLocation);
            collectionTime = listingView.findViewById(R.id.collectionTime);
            collectionDate = listingView.findViewById(R.id.collectionDate);
            userOrderStatus = listingView.findViewById(R.id.orderStatus);
            userOrderDetailButton = listingView.findViewById(R.id.checkUserOrderDetails);
            cardView = listingView.findViewById(R.id.cardView);

        }
    }

}
