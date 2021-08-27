package com.example.adprojteam4.CourierListing;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adprojteam4.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdaptor extends RecyclerView.Adapter<FoodAdaptor.ViewHolder> {

    private Context context;
    private List<ArrayList<String>> foodItems;
    private List<Double> prices;

    public FoodAdaptor(Context context, List<ArrayList<String>> foodItems, List<Double> prices) {
        this.context = context;
        this.foodItems = foodItems;
        this.prices = prices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.food_item, parent, false);
        return new FoodAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdaptor.ViewHolder holder, int position) {
        //holder.foodId.setText(foodItems.get(position).get(0));
        holder.foodName.setText(foodItems.get(position).get(1));
        holder.foodPrice.setText(prices.get(position).toString());

        holder.addPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFoodPricePopUpWindow(v,position);
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    private void setFoodPricePopUpWindow(View v, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.set_price_popup, null, false);
        Button btnConfirmFoodPrice = (Button) view.findViewById(R.id.confirmFoodPrice);
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);



        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));

        popWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        btnConfirmFoodPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FoodPrice = ((EditText)view.findViewById(R.id.textSetFoodPrice)).getText().toString();
                Double foodPrice = Double.valueOf(FoodPrice);
                addData(position, foodPrice);
                popWindow.dismiss();
            }
        });
    }


    public void addData(int position, Double foodPrice) {

        prices.set(position, foodPrice);
        notifyDataSetChanged();
        //notifyItemInserted(position);
    }


    public void removeData(int position) {

        foodItems.remove(position);
        prices.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName, foodPrice;
        Button addPrice,remove;
        CardView cardView;

        public ViewHolder(@NonNull View listingView){
            super(listingView);

            foodName = listingView.findViewById(R.id.foodName);
            addPrice = listingView.findViewById(R.id.addPrice);
            remove = listingView.findViewById(R.id.remove);
            cardView = listingView.findViewById(R.id.cv);
            foodPrice = listingView.findViewById(R.id.foodPrice);
        }
    }
}

