package com.example.adprojteam4;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListingAdaptor extends RecyclerView.Adapter<ListingAdaptor.ViewHolder> {

    private List<HawkerListing>  hawkerListingList;

    public ListingAdaptor(List<HawkerListing> hawkerListingList) {
        this.hawkerListingList = hawkerListingList;
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

        holder.hawkerName.setText(hawkerListingList.get(position).getName());
        holder.hawkerAddress.setText(hawkerListingList.get(position).getAddress());
        holder.hawkerPostalCode.setText(hawkerListingList.get(position).getPostalCode());
        holder.hawkerStallNo.setText(hawkerListingList.get(position).getStallNo());
    }

    @Override
    public int getItemCount() {
        return hawkerListingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView hawkerImage;
        TextView hawkerName;
        TextView hawkerAddress;
        TextView hawkerPostalCode;
        TextView hawkerStallNo;

        public ViewHolder(@NonNull View listingView){
            super(listingView);

            hawkerName = listingView.findViewById(R.id.listingName);
            hawkerAddress = listingView.findViewById(R.id.listingAddress);
            hawkerPostalCode = listingView.findViewById(R.id.listingPostalCode);
            hawkerImage= listingView.findViewById(R.id.listingImage);
            hawkerStallNo = listingView.findViewById(R.id.listingStallNo);

        }
    }
}
