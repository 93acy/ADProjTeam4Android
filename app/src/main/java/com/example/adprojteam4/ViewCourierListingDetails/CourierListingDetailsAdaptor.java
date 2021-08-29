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
import com.example.adprojteam4.RetrofitClient;
import com.example.adprojteam4.ViewCourierListing.CourierListingAdaptor;
import com.example.adprojteam4.ViewCourierListing.ViewCourierListing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourierListingDetailsAdaptor extends RecyclerView.Adapter<CourierListingDetailsAdaptor.ViewHolder> {

    private Context context;
    private List<List<String>> courierListingDetails;
    private List<List<String>> courierListingDetailInfo;
    //private List<List<List<String>>> courierListingDetailInfos;
    String foodItem="";
    String foodQuantity="";
    //int size = 0;


    public CourierListingDetailsAdaptor(Context context, List<List<String>> courierListingDetails, List<List<String>> courierListingDetailInfo) {
        this.context = context;
        this.courierListingDetails = courierListingDetails;
        this.courierListingDetailInfo = courierListingDetailInfo;
        //this.size = size;
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
        //holder.txtUserName.setText(courierListingDetails.get(position).get(4));
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




//        for(List<String> a : courierListingDetails){
//
//            for(List<String> s : courierListingDetailInfo){
//                foodItem += s.get(0) + System.getProperty ("line.separator");
//                holder.txtFoodItem.setText(foodItem);
//                foodQuantity += s.get(1) + System.getProperty ("line.separator");
//                holder.txtFoodQuantity.setText(foodQuantity);
//            }
//        }



//        for(List<String> s : courierListingDetailInfo){
//            foodItem += s.get(0) + System.getProperty ("line.separator");
//            holder.txtFoodItem.setText(foodItem);
//            foodQuantity += s.get(1) + System.getProperty ("line.separator");
//            holder.txtFoodQuantity.setText(foodQuantity);
//        }

        //for(List<String> s : courierListingDetailInfos.get(position)){
        //foodItem += s.get(0) + System.getProperty ("line.separator");
        //holder.txtFoodItem.setText(foodItem);
        //foodQuantity += s.get(1) + System.getProperty ("line.separator");
        //holder.txtFoodQuantity.setText(foodQuantity);
        //}


        /*for(int i=0; i< courierListingDetailInfos.get(i).size(); i++){
            foodItem += courierListingDetailInfo.get(i).get(0) + System.getProperty ("line.separator");
            holder.txtFoodItem.setText(foodItem);
            foodQuantity += courierListingDetailInfo.get(i).get(1) + System.getProperty ("line.separator");
            holder.txtFoodQuantity.setText(foodQuantity);
        }*/

        /*for(int i=0; i<size; i++){
            //for(int j=0; j< courierListingDetailInfo.get(position).size(); j++){
                foodItem += courierListingDetailInfo.get(count).get(0) + System.getProperty ("line.separator");
                //holder.txtFoodItem.setText(foodItem);
                foodQuantity += courierListingDetailInfo.get(count).get(1) + System.getProperty ("line.separator");
                //holder.txtFoodQuantity.setText(foodQuantity);
                count = count + 1;
            //}
            //holder.txtFoodItem.setText(courierListingDetailInfo.get(position).get(0) + System.getProperty ("line.separator"));
            //holder.txtFoodQuantity.setText(courierListingDetailInfo.get(position).get(1) + System.getProperty ("line.separator"));
        }
        holder.txtFoodItem.setText(foodItem);
        holder.txtFoodQuantity.setText(foodQuantity);*/

//        for(int i=0; i<size; i++){
//            //for(int j=0; j< courierListingDetailInfo.get(position).size(); j++){
//            foodItem += courierListingDetailInfo.get(i).get(0) + System.getProperty ("line.separator");
//            holder.txtFoodItem.setText(foodItem);
//            foodQuantity += courierListingDetailInfo.get(i).get(1) + System.getProperty ("line.separator");
//            holder.txtFoodQuantity.setText(foodQuantity);
//            //}
//            //holder.txtFoodItem.setText(courierListingDetailInfo.get(position).get(0) + System.getProperty ("line.separator"));
//            //holder.txtFoodQuantity.setText(courierListingDetailInfo.get(position).get(1) + System.getProperty ("line.separator"));
//        }


        //List<List<String>> s2 = courierListingDetailInfos.get(position);


//        for(List<String> s : courierListingDetailInfos.get(position)){
//            foodItem += s.get(0) + System.getProperty ("line.separator");
//            holder.txtFoodItem.setText(foodItem);
//            foodQuantity += s.get(1) + System.getProperty ("line.separator");
//            holder.txtFoodQuantity.setText(foodQuantity);
//
//        }
//        foodItem="";
//        foodQuantity="";




        /*for(List<List<String>> s : courierListingDetailInfos){
            foodItem += s.get(0).get(0) + System.getProperty ("line.separator");
            holder.txtFoodItem.setText(foodItem);
            foodQuantity += s.get(0).get(1) + System.getProperty ("line.separator");
            holder.txtFoodQuantity.setText(foodQuantity);

        }
        foodItem="";
        foodQuantity="";

         */





        //holder.txtUserName.setText(courierListingDetails.get(position).get(2));
        //holder.txtStatus.setText(courierListingDetails.get(position).get(3));


        /*for(List<List<String>> s : courierListingDetailInfos){
            holder.txtFoodItem.setText(s.get(0)+ System.getProperty ("line.separator"));
            holder.txtFoodQuantity.setText(s.get(1)+ System.getProperty ("line.separator"));
        }*/

        //holder.txtFoodItem.setText(courierListingDetailInfo);
        //holder.txtFoodItem.setText(courierListingDetailInfo.get(0).get(0)+" * "+courierListingDetailInfo.get(0).get(1));


        //holder.txtCourierListingDetailsPricePerUnit.setText(courierListingDetailInfo);

        /*for(List<String> s : courierListings.get(position)){

            holder.txtHawkerName.setText(s.get(0));
            foodItem += s.get(1) + System.getProperty ("line.separator");
            pricePerUnit += s.get(2) + System.getProperty ("line.separator");
            totalQuantity += s.get(3) + System.getProperty ("line.separator");
            holder.txtCourierListingDetailsFoodItem.setText(foodItem);
            holder.txtCourierListingDetailsPricePerUnit.setText(pricePerUnit);
            holder.txtCourierListingDetailsTotalQuantity.setText(totalQuantity);

        }*/
        //holder.txtCourierListingDetailsFoodItem.setText(foodItem);
        //holder.txtCourierListingDetailsPricePerUnit.setText(pricePerUnit);
        //holder.txtCourierListingDetailsTotalQuantity.setText(totalQuantity);
        //holder.txtCourierListingDetailsFoodItem.setText(courierListings.get(position).get(1));
        //holder.txtCourierListingDetailsPricePerUnit.setText(courierListings.get(position).get(2));
        //holder.txtCourierListingDetailsTotalQuantity.setText(courierListings.get(position).get(3));
        /*for(List<String> s : courierListings.get(position)){
            foodPrice = foodPrice + Double.parseDouble(s.get(2)) * Double.parseDouble(s.get(3));
            holder.txtTotalFoodPrice.setText("Total Food Price:  " + foodPrice.toString());

        }*/
        //holder.txtTotalFoodPrice.setText("Total Food Price:  " + foodPrice.toString());
        /*serviceFee = foodPrice*0.1;
        holder.txtServiceFee.setText("Service fee: " + serviceFee.toString());
        totalPrice = foodPrice + serviceFee;
        holder.txtTotalPrice.setText("Total Price: " + totalPrice.toString());

        foodItem ="";
        pricePerUnit ="";
        totalQuantity ="";
        foodPrice = 0.0;*/

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
