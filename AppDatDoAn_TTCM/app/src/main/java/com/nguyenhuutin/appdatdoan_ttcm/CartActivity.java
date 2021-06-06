package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhuutin.adapter.CartAdapter;
import com.nguyenhuutin.model.Cart;
import com.nguyenhuutin.ultil.CheckConnection;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    ListView lvCart;
    public static TextView txtnotification;
    static TextView txtToltalMoney;
    Button btnNextBuy, btnOrder;
    Toolbar toolbarCart;
    public static CartAdapter cartAdapter;
    int tmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        addLink();
        addEvents();
        ActionToolbar();
        checkData();
        EventUtil();
        tmp = HomeActivity.arrayListCart.size();

    }

    private void addEvents() {
        btnNextBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HomeActivity.arrayListCart.size() > 0){
                    Intent intent = new Intent(CartActivity.this, InfoUsersActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn đang trống");

                }
            }
        });

    }


    public static void EventUtil() {
        long ToltalMoney =0;
        for (int i=0;i< HomeActivity.arrayListCart.size();i++){
            ToltalMoney += HomeActivity.arrayListCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtToltalMoney.setText(decimalFormat.format(ToltalMoney) + " đ");
    }

    private void addLink() {
        lvCart = findViewById(R.id.lvCart);
        txtnotification = findViewById(R.id.txtnotification);
        txtToltalMoney = findViewById(R.id.txtToltalMoney);
        btnNextBuy = findViewById(R.id.btnNextBuy);
        btnOrder = findViewById(R.id.btnOrder);
        toolbarCart = findViewById(R.id.ToolbarFood);
        cartAdapter = new CartAdapter(CartActivity.this,HomeActivity.arrayListCart);
        lvCart.setAdapter(cartAdapter);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCart.setTitle("Giỏ Hàng");
        toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
}