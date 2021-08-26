package com.example.adprojteam4.OrderFunction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.R;

import java.util.ArrayList;
import java.util.List;

public class SelectFoodAdaptor extends RecyclerView.Adapter<SelectFoodAdaptor.ViewHolder>{

    private Context context;
    private ArrayList<ArrayList<String>> foodData;
    private List<Integer> Quantity = new ArrayList<>();
    private Integer q=0;

    public SelectFoodAdaptor(Context context, ArrayList<ArrayList<String>> foodData,List<Integer> Quantity) {
        this.context = context;
        this.foodData = foodData;
        this.Quantity = Quantity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.food_item_order, parent, false);
        return new SelectFoodAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectFoodAdaptor.ViewHolder holder, int position) {

        holder.foodName.setText(foodData.get(position).get(1));
        holder.category.setText(foodData.get(position).get(2));
        holder.description.setText(foodData.get(position).get(3));
        holder.price.setText(foodData.get(position).get(4));
        holder.quantity.setText(Quantity.get(position).toString());


        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q = Integer.parseInt(holder.quantity.getText().toString());
                if(q==0){
                    Toast.makeText(context, "Quantity cannot less than 0 !", Toast.LENGTH_LONG).show();
                }
                else{
                    q = q-1;
                    updateQuantity(position, q);
                }
            }

        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q = q+1;
                updateQuantity(position, q);
            }
        });

    }

    public void updateQuantity(int position, Integer q) {

        Quantity.set(position, q);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {

        return foodData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView plus, minus;
        TextView foodName, category, description, price, dollar, quantity;

        public ViewHolder(@NonNull View listingView) {
            super(listingView);
            plus = listingView.findViewById(R.id.plus);
            minus = listingView.findViewById(R.id.minus);
            foodName = listingView.findViewById(R.id.food_name);
            category = listingView.findViewById(R.id.food_category);
            description = listingView.findViewById(R.id.food_description);
            price = listingView.findViewById(R.id.food_price);
            dollar = listingView.findViewById(R.id.food_dollar);
            quantity = listingView.findViewById(R.id.quantity);


        }
    }

}