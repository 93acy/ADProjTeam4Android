package com.example.adprojteam4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecommendationsAdaptor extends RecyclerView.Adapter<RecommendationsAdaptor.ViewHolder> {

    private ArrayList<TempStall>  recommendedHawkerListingList;

    public RecommendationsAdaptor(ArrayList<TempStall> recommendedHawkerListingList) {
        this.recommendedHawkerListingList = recommendedHawkerListingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommendations_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationsAdaptor.ViewHolder holder, int position) {

        holder.hawkerName.setText(recommendedHawkerListingList.get(position).getName());
        holder.hawkerAddress.setText(recommendedHawkerListingList.get(position).getAddress());
        holder.hawkerPostalCode.setText(recommendedHawkerListingList.get(position).getPostalCode());
    }

    @Override
    public int getItemCount() {
        return recommendedHawkerListingList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hawkerImage;
        TextView hawkerName;
        TextView hawkerAddress;
        TextView hawkerPostalCode;

        public ViewHolder(@NonNull View listingView){
            super(listingView);

            hawkerName = listingView.findViewById(R.id.recommendationslistingName);
            hawkerAddress = listingView.findViewById(R.id.recommendationslistingAddress);
            hawkerPostalCode = listingView.findViewById(R.id.recommendationslistingPostalCode);
            hawkerImage= listingView.findViewById(R.id.recommendationslistingImage);

        }
    }
}
