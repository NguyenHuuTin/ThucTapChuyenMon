package com.nguyenhuutin.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.nguyenhuutin.appdatdoan_ttcm.HomeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.fragment.FragmentCart;
import com.nguyenhuutin.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> arrayListCart;

    public CartAdapter(Context context, ArrayList<Cart> arrayListCart) {
        this.context = context;
        this.arrayListCart = arrayListCart;
    }

    @Override
    public int getCount() {
        return arrayListCart.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txtFoodNameCart, txtPriceCart, txtslFood;
        public ImageView imgViewCart;
        public ImageButton btnminus, btnplus, btndelete;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart,null);
            viewHolder.txtFoodNameCart = (TextView) convertView.findViewById(R.id.txtFoodNameCart);
            viewHolder.txtPriceCart = (TextView) convertView.findViewById(R.id.txtPriceCart);
            viewHolder.txtslFood = (TextView) convertView.findViewById(R.id.txtslFood);
            viewHolder.imgViewCart = (ImageView) convertView.findViewById(R.id.imgViewCart);
            viewHolder.btnminus = (ImageButton) convertView.findViewById(R.id.btnminus);
            viewHolder.btnplus = (ImageButton) convertView.findViewById(R.id.btnplus);
            viewHolder.btndelete = (ImageButton) convertView.findViewById(R.id.btndelete);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Cart cart = (Cart) getItem(position);
        viewHolder.txtFoodNameCart.setText(cart.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPriceCart.setText(decimalFormat.format(cart.getPrice()) + "đ");
        Picasso.with(context).load(cart.getImg())
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(viewHolder.imgViewCart);
        viewHolder.txtslFood.setText(cart.getSL() +"");
        int sl = Integer.parseInt(viewHolder.txtslFood.getText().toString());
        if(sl >=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        else if (sl<=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }
        else if(sl>=1){
            viewHolder.btnminus.setTransitionVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newSL = Integer.parseInt(finalViewHolder.txtslFood.getText().toString()) + 1;
                int sl = HomeActivity.arrayListCart.get(position).getSL();
                int price = HomeActivity.arrayListCart.get(position).getPrice();
                HomeActivity.arrayListCart.get(position).setSL(newSL);
                int newPrice = (price * newSL) / sl;
                HomeActivity.arrayListCart.get(position).setPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtPriceCart.setText(decimalFormat.format(newPrice) + "đ");
                FragmentCart.EventUtil();
                if (newSL > 9){
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.txtslFood.setText(String .valueOf(newSL));
                }else {
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.txtslFood.setText(String .valueOf(newSL));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newSL = Integer.parseInt(finalViewHolder.txtslFood.getText().toString()) -1;
                int sl = HomeActivity.arrayListCart.get(position).getSL();
                int price = HomeActivity.arrayListCart.get(position).getPrice();
                HomeActivity.arrayListCart.get(position).setSL(newSL);
                int newPrice = (price * newSL) / sl;
                HomeActivity.arrayListCart.get(position).setPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtPriceCart.setText(decimalFormat.format(newPrice) + "đ");
                FragmentCart.EventUtil();
                if (newSL < 2){
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.txtslFood.setText(String .valueOf(newSL));
                }else {
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.txtslFood.setText(String .valueOf(newSL));
                }
            }
        });
        viewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác Nhận");
                builder.setMessage("Bạn có chắc muốn xóa món ăn này ra khỏi giỏ hàng!!!");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(HomeActivity.arrayListCart.size() <=0){
                            FragmentCart.txtnotification.setVisibility(View.VISIBLE);
                        }
                        else {
                            HomeActivity.arrayListCart.remove(position);
                            FragmentCart.cartAdapter.notifyDataSetChanged();
                            FragmentCart.EventUtil();
                            if (HomeActivity.arrayListCart.size() <= 0){
                                FragmentCart.txtnotification.setVisibility(View.VISIBLE);
                            }else {
                                FragmentCart.txtnotification.setVisibility(View.INVISIBLE);
                                FragmentCart.cartAdapter.notifyDataSetChanged();
                                FragmentCart.EventUtil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentCart.cartAdapter.notifyDataSetChanged();
                        FragmentCart.EventUtil();
                    }
                });
                builder.show();
            }
        });
        return convertView;
    }
}
