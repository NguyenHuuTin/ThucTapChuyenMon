package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.adapter.OrderAdapter;
import com.nguyenhuutin.model.Order;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoOrderActivity extends AppCompatActivity {
    ListView lvDetailsOrder;
    TextView lblname, lblSDT;
    ImageButton btnBack;
    CircleImageView imgUser;
    ArrayList<Order> arrayListOrder;
    OrderAdapter OrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_order);
        addLink();
        getDataDetailsOrder();
        addEvent();
    }

    private void addLink() {
        btnBack = findViewById(R.id.btnback);
        imgUser = findViewById(R.id.imgUserInfo);
        lblname = findViewById(R.id.lblname);
        lblSDT = findViewById(R.id.lblsdt);
        lblname.setText(MainActivity.users.getUserName());
        lblSDT.setText(MainActivity.users.getSDT());
        lvDetailsOrder = findViewById(R.id.lvOrderOld);
        arrayListOrder = new ArrayList<>();
        OrderAdapter = new OrderAdapter(InfoOrderActivity.this,arrayListOrder);
        lvDetailsOrder.setAdapter(OrderAdapter);
        OrderAdapter.notifyDataSetChanged();
    }

    private void addEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoOrderActivity.this, InfoUserActivity.class);
                startActivity(intent);

            }
        });
    }

    private void getDataDetailsOrder() {
        String SDT = MainActivity.users.getSDT();
        if (!SDT.isEmpty()){
            RequestQueue requestQueue = Volley.newRequestQueue(InfoOrderActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathGetDataOrder, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null){
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0; i< jsonArray.length(); i++){
                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    int ID = jsonObject.getInt("id");
                                    String Total = jsonObject.getString("total");
                                    String Time = jsonObject.getString("time");
                                    String address = jsonObject.getString("address");
                                    arrayListOrder.add(new Order(ID,Time,address,Total));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("old", e.toString());
                                }
                            }
                            OrderAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                  }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(InfoOrderActivity.this, "Lỗi dữ liệu", Toast.LENGTH_LONG).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("SDT",SDT);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(this, "Lỗi dữ liệu", Toast.LENGTH_LONG).show();
        }

    }
}