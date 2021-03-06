package com.example.adprojteam4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.CourierListing.ViewFoodItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListingAdaptor extends RecyclerView.Adapter<ListingAdaptor.ViewHolder> {

    private Context context;
    private List<List<String>> hawkerData;

    public ListingAdaptor(Context context, List<List<String>> hawkerData) {
        this.context = context;
        this.hawkerData = hawkerData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hawkerlisting_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListingAdaptor.ViewHolder holder, int position) {


        holder.hawkerName.setText(hawkerData.get(position).get(1));
        holder.hawkerAddress.setText(hawkerData.get(position).get(3));
        holder.hawkerLocation.setText(hawkerData.get(position).get(4));
        holder.hawkerPostalCode.setText(hawkerData.get(position).get(5));
        holder.hawkerStallNo.setText(hawkerData.get(position).get(2));
        holder.courierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewFoodItem.class);
                String hawkerId = hawkerData.get(position).get(0);
                intent.putExtra("hawkerId",hawkerId);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hawkerData.size();
    }

    public void filterList(List<List<String>> filteredList) {
        hawkerData = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hawkerImage;
        TextView hawkerName;
        TextView hawkerAddress;
        TextView hawkerLocation;
        TextView hawkerPostalCode;
        TextView hawkerStallNo;
        CardView cardView;
        FloatingActionButton courierButton;

        public ViewHolder(@NonNull View listingView){
            super(listingView);


            hawkerName = listingView.findViewById(R.id.listingName);
            hawkerAddress = listingView.findViewById(R.id.listingAddress);
            hawkerLocation = listingView.findViewById(R.id.locationArea);
            hawkerPostalCode = listingView.findViewById(R.id.listingPostalCode);
            hawkerImage= listingView.findViewById(R.id.listingImage);
            hawkerStallNo = listingView.findViewById(R.id.listingStallNo);
            cardView = listingView.findViewById(R.id.cardView);
            courierButton = listingView.findViewById(R.id.postCourierListing);

        }
    }
}
