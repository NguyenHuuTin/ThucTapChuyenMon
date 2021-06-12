package com.nguyenhuutin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nguyenhuutin.adapter.CartAdapter;
import com.nguyenhuutin.appdatdoan_ttcm.HomeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.InfoUsersActivity;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.ultil.CheckConnection;

import java.text.DecimalFormat;

public class FragmentCart extends Fragment {
    View view;
    ListView lvCart;
    public static TextView txtnotification;
    static TextView txtToltalMoney;
    Button btnNextBuy, btnOrder;
    public static CartAdapter cartAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart,container,false);
        addLink();
        addEvents();
        checkData();
        EventUtil();
        return view;
    }

    private void addEvents() {
        btnNextBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HomeActivity.arrayListCart.size() > 0){
                    Intent intent = new Intent(getActivity(), InfoUsersActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.ShowToast_Short(getActivity(),"Giỏ hàng của bạn đang trống");

                }
            }
        });
    }

    private void addLink() {
        lvCart = view.findViewById(R.id.lvCart);
        txtnotification = view.findViewById(R.id.txtnotification);
        txtToltalMoney = view.findViewById(R.id.txtToltalMoney);
        btnNextBuy = view.findViewById(R.id.btnNextBuy);
        btnOrder = view.findViewById(R.id.btnOrder);
        cartAdapter = new CartAdapter(getActivity(), HomeActivity.arrayListCart);
        cartAdapter.notifyDataSetChanged();
        lvCart.setAdapter(cartAdapter);

    }
    private void checkData() {
        if (HomeActivity.arrayListCart.size()<=0){
            cartAdapter.notifyDataSetChanged();
            txtnotification.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);
        }
        else {
            cartAdapter.notifyDataSetChanged();
            txtnotification.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);
        }
    }
    public static void EventUtil() {
        long ToltalMoney =0;
        for (int i=0;i< HomeActivity.arrayListCart.size();i++){
            ToltalMoney += HomeActivity.arrayListCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtToltalMoney.setText(decimalFormat.format(ToltalMoney) + " đ");
    }
}
