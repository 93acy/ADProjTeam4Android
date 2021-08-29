package com.example.adprojteam4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.CourierListing.courier_CourierListingAdapter;
import com.example.adprojteam4.OrderFunction.ViewOrderStatus;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DashboardAdaptor extends RecyclerView.Adapter<DashboardAdaptor.ViewHolder>{
    Context context;
    ArrayList<ArrayList<ArrayList<String>>> Data = new ArrayList<>();

    public DashboardAdaptor(Context context, ArrayList<ArrayList<ArrayList<String>>> data) {
        this.context = context;
        Data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_order_item, parent, false);
        return new DashboardAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdaptor.ViewHolder holder, int position) {
        holder.hawkerName.setText(Data.get(position).get(position).get(1));
        holder.totalCost.setText(Data.get(position).get(position).get(2));
        holder.orderStatus.setText(Data.get(position).get(position).get(3));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewOrderStatus.class);
                intent.putExtra("userOrderId",Data.get(position).get(position).get(0));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView hawkerName, orderStatus, totalCost;
        CardView cardView;

        public ViewHolder(@NonNull View listingView) {
            super(listingView);
            hawkerName = listingView.findViewById(R.id.HakerName);
            orderStatus = listingView.findViewById(R.id.OrderStatus);
            totalCost = listingView.findViewById(R.id.TotalCost);
            cardView = listingView.findViewById(R.id.CardView);
        }
    }
}
