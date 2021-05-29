package com.nguyenhuutin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.model.itemMenu;

import java.util.List;

public class menuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<itemMenu> list;

    public menuAdapter() {
    }

    public menuAdapter(Context context, int layout, List<itemMenu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.txtitemMenu = (TextView) convertView.findViewById(R.id.txtNameIcon);
            viewHolder.imgIconMenu = (ImageView) convertView.findViewById(R.id.imgIconMenu);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtitemMenu.setText(list.get(position).itemName);
        viewHolder.imgIconMenu.setImageResource(list.get(position).icon);

        return convertView;
    }
    private class ViewHolder{
        TextView txtitemMenu;
        ImageView imgIconMenu;
    }
}
