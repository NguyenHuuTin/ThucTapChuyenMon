package com.nguyenhuutin.adapter;

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

import com.nguyenhuutin.appdatdoan_ttcm.CakeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.CartActivity;
import com.nguyenhuutin.appdatdoan_ttcm.HomeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.MainActivity;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.model.Cart;
import com.nguyenhuutin.model.Food;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ItemHolder> {
    Context context;
    ArrayList<Food> arrayFood;

    public FoodAdapter() {
    }

    public FoodAdapter(Context context, ArrayList<Food> arrayFood) {
        this.context = context;
        this.arrayFood = arrayFood;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,null);
        FoodAdapter.ItemHolder itemHolder = new FoodAdapter.ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Food food = arrayFood.get(position);
        holder.txtfoodName.setText(food.getFooodName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtprice.setText(decimalFormat.format(food.getPrice()) + " đ");
        Picasso.with(context).load(food.getImgFood())
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.imgfood);
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(HomeActivity.arrayListCart.size()>0){
                     boolean exists =false;
                     for (int i=0; i< HomeActivity.arrayListCart.size();i++){
                         if (HomeActivity.arrayListCart.get(i).getID() == food.getId()){
                             HomeActivity.arrayListCart.get(i).setSL(HomeActivity.arrayListCart.get(i).getSL() + 1);
                             if(HomeActivity.arrayListCart.get(i).getSL() >10){
                                 HomeActivity.arrayListCart.get(i).setSL(10);
                             }
                             HomeActivity.arrayListCart.get(i).setPrice(food.getPrice() * HomeActivity.arrayListCart.get(i).getSL());
                             exists =true;
                         }
                     }
                     if (exists ==false) {
                         HomeActivity.arrayListCart.add(new Cart(food.getId(), food.getFooodName(), food.getPrice(), food.getImgFood(), 1));
                     }
                 }else {
                     HomeActivity.arrayListCart.add(new Cart(food.getId(),food.getFooodName(),food.getPrice(),food.getImgFood(),1));

                 }
                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayFood.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgfood;
        public TextView txtfoodName, txtprice;
        public Button btnAddToCart;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgfood = (ImageView)itemView.findViewById(R.id.imgFood);
            txtfoodName = (TextView)itemView.findViewById(R.id.txtFoodName);
            txtprice = (TextView)itemView.findViewById(R.id.txtPrice);
            btnAddToCart = (Button)itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
