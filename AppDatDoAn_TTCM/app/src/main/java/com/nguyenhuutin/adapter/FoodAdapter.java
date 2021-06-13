package com.nguyenhuutin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhuutin.appdatdoan_ttcm.HomeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.fragment.FragmentCart;
import com.nguyenhuutin.model.Cart;
import com.nguyenhuutin.model.Food;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ItemHolder> {
    Context context;
    ArrayList<Food> arrayFood;
    private IClickAddToCartListener iClickAddToCartListener;
    public interface IClickAddToCartListener{
        void onClickAddToCart(ImageView imgAddToCart, Food food);
    }

    public FoodAdapter() {
    }

    public FoodAdapter(Context context, ArrayList<Food> arrayFood, IClickAddToCartListener listener) {
        this.context = context;
        this.arrayFood = arrayFood;
        this.iClickAddToCartListener = listener;
        notifyDataSetChanged();
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
                iClickAddToCartListener.onClickAddToCart(holder.btnAddToCart,food);
                FragmentCart.cartAdapter.notifyDataSetChanged();
                 if(HomeActivity.arrayListCart.size()>0){
                     boolean exists =false;
                     for (int i=0; i< HomeActivity.arrayListCart.size();i++){
                         if (HomeActivity.arrayListCart.get(i).getID() == food.getId()){
                             HomeActivity.arrayListCart.get(i).setSL(HomeActivity.arrayListCart.get(i).getSL() + 1);
                             FragmentCart.cartAdapter.notifyDataSetChanged();
                             if(HomeActivity.arrayListCart.get(i).getSL() >10){
                                 HomeActivity.arrayListCart.get(i).setSL(10);
                                 FragmentCart.cartAdapter.notifyDataSetChanged();
                             }
                             HomeActivity.arrayListCart.get(i).setPrice(food.getPrice() * HomeActivity.arrayListCart.get(i).getSL());
                             exists =true;
                             FragmentCart.cartAdapter.notifyDataSetChanged();
                         }
                     }
                     if (exists ==false) {
                         HomeActivity.arrayListCart.add(new Cart(food.getId(), food.getFooodName(), food.getPrice(), food.getImgFood(), 1));
                         FragmentCart.cartAdapter.notifyDataSetChanged();
                         HomeActivity.mCountFood ++;
                         HomeActivity.checkCountFood();


                     }
                 }else {
                     HomeActivity.arrayListCart.add(new Cart(food.getId(),food.getFooodName(),food.getPrice(),food.getImgFood(),1));
                     FragmentCart.cartAdapter.notifyDataSetChanged();
                     HomeActivity.mCountFood = 1;
                     HomeActivity.checkCountFood();

                 }
                FragmentCart.cartAdapter.notifyDataSetChanged();
                 FragmentCart.EventUtil();

            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayFood.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgfood, btnAddToCart;
        public TextView txtfoodName, txtprice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgfood = (ImageView)itemView.findViewById(R.id.imgFood);
            txtfoodName = (TextView)itemView.findViewById(R.id.txtFoodName);
            txtprice = (TextView)itemView.findViewById(R.id.txtPrice);
            btnAddToCart = (ImageView) itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
