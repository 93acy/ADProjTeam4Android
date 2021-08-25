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

import com.example.adprojteam4.HawkerListing;
import com.example.adprojteam4.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CourierListingAdaptor extends RecyclerView.Adapter<CourierListingAdaptor.ViewHolder> {
    private Context context;
    private ArrayList<ArrayList<String>> courierListingData;

    public CourierListingAdaptor(Context context, ArrayList<ArrayList<String>> courierListingData) {
        this.context = context;
        this.courierListingData = courierListingData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.courierlisting_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourierListingAdaptor.ViewHolder holder, int position) {
        ArrayList<String> data = courierListingData.get(position);

        holder.pickupTime.setText(data.get(1));
        holder.hawkerName.setText(data.get(2));
        holder.locationArea.setText(data.get(3));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SelectFood.class);
                intent.putStringArrayListExtra("courierListingData",data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courierListingData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hawkerName,locationArea,pickupTime,text;
        CardView cardView;

        public ViewHolder(@NonNull View listingView) {
            super(listingView);
            hawkerName = listingView.findViewById(R.id.hawkerName);
            locationArea = listingView.findViewById(R.id.location);
            pickupTime = listingView.findViewById(R.id.pickupTime);
            text = listingView.findViewById(R.id.text);
            cardView = listingView.findViewById(R.id.cl_cv);
        }
    }

    public void filterList(ArrayList<ArrayList<String>> filteredList){
        courierListingData = filteredList;
        notifyDataSetChanged();
    }

}
