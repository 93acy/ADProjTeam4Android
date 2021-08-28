package com.example.adprojteam4.OrderFunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.R;

import java.util.List;

public class UserOrderAdaptor extends RecyclerView.Adapter<UserOrderAdaptor.ViewHolder> {
    private Context context;
    private List<List<String>> orderFoodData;
    public UserOrderAdaptor(Context context, List<List<String>> orderFoodData) {
        this.context = context;
        this.orderFoodData = orderFoodData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.user_orders_status_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foodName.setText(orderFoodData.get(position).get(0));
        holder.quantity.setText(orderFoodData.get(position).get(1));
    }
    @Override
    public int getItemCount() {
        return orderFoodData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName,quantity,text;
        public ViewHolder(@NonNull View listingView) {
            super(listingView);
            foodName = listingView.findViewById(R.id.foodName);
            quantity = listingView.findViewById(R.id.quantity);
            text = listingView.findViewById(R.id.quantityText);
        }
    }

}
