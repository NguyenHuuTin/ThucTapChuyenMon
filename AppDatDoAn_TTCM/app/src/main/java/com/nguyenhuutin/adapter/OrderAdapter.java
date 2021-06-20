package com.nguyenhuutin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.model.Cart;
import com.nguyenhuutin.model.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<Order> arrayList;

    public OrderAdapter() {
    }

    public OrderAdapter(Context context, ArrayList<Order> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_order_old,null);
            viewHolder.lblidOrder = (TextView) convertView.findViewById(R.id.lblidOrder);
            viewHolder.lblTime = (TextView) convertView.findViewById(R.id.lblTime);
            viewHolder.lbladdress = (TextView) convertView.findViewById(R.id.lblAddress);
            viewHolder.lblTotal = (TextView) convertView.findViewById(R.id.lblTotal);
            viewHolder.lblstatus = (TextView)convertView.findViewById(R.id.lblstatus);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Order order = (Order) getItem(position);
        viewHolder.lblidOrder.setText(order.getId() + "");
        viewHolder.lblTime.setText(order.getTime());
        viewHolder.lbladdress.setText(order.getAddress());
        viewHolder.lblTotal.setText(order.getTotal());
        if(order.getTt() == 0){
            viewHolder.lblstatus.setText("Đã giao");
        }else if (order.getTt() == 1){
            viewHolder.lblstatus.setText("Đang chờ");
        }

        return convertView;
    }
    public class ViewHolder{
        public TextView lblidOrder, lblTime, lbladdress, lblTotal, lblstatus;

    }
}
