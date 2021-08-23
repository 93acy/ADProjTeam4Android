package com.example.adprojteam4;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.CourierListing.ViewFoodItem;

import java.util.List;

public class ListingAdaptor extends RecyclerView.Adapter<ListingAdaptor.ViewHolder> {

    private Context context;
    private List<List<String>>  hawkerData;

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
        holder.hawkerLocation.setText(hawkerData.get(position).get(2));
        holder.hawkerPostalCode.setText(hawkerData.get(position).get(3));
        holder.hawkerStallNo.setText(hawkerData.get(position).get(4));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hawkerImage;
        TextView hawkerName;
        TextView hawkerLocation;
        TextView hawkerPostalCode;
        TextView hawkerStallNo;
        CardView cardView;

        public ViewHolder(@NonNull View listingView){
            super(listingView);

            hawkerName = listingView.findViewById(R.id.listingName);
            hawkerLocation = listingView.findViewById(R.id.locationArea);
            hawkerPostalCode = listingView.findViewById(R.id.listingPostalCode);
            hawkerImage= listingView.findViewById(R.id.listingImage);
            hawkerStallNo = listingView.findViewById(R.id.listingStallNo);
            cardView = listingView.findViewById(R.id.cardView);

        }
    }
}
