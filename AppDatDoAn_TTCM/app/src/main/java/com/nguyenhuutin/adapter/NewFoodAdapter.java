package com.nguyenhuutin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.model.Food;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NewFoodAdapter extends RecyclerView.Adapter<NewFoodAdapter.ItemHolder> {
    Context context;
    ArrayList<Food> arrayFood;

    public NewFoodAdapter() {
    }

    public NewFoodAdapter(Context context, ArrayList<Food> arrayFood) {
        this.context = context;
        this.arrayFood = arrayFood;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfood,null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Food food = arrayFood.get(position);
        holder.txtfoodName.setText(food.getFooodName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtprice.setText("Giá chỉ từ: " + decimalFormat.format(food.getPrice()));
        Picasso.with(context).load(food.getImgFood())
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.imgfood);


    }

    @Override
    public int getItemCount() {
        return arrayFood.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgfood;
        public TextView txtfoodName, txtprice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgfood = (ImageView)itemView.findViewById(R.id.imgFood);
            txtfoodName = (TextView)itemView.findViewById(R.id.txtfoodName);
            txtprice = (TextView)itemView.findViewById(R.id.txtpriceFood);
        }
    }
}
