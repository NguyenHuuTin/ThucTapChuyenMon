package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.ultil.CheckConnection;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InfoUsersActivity extends AppCompatActivity {
    EditText edtAddress, edtName, edtSDT;
    Button btnConfirm, btnPrev;
    TextView txtTotalMoney;
    private int money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_users);
        addLink();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            addEvents();
        }
        else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra kết nối Internet");

        }
    }

    private void addLink() {
        edtName = findViewById(R.id.edtNameUsers);
        edtSDT = findViewById(R.id.edtSDTUsers);
        edtAddress = findViewById(R.id.edtAddressUsers);
        btnConfirm = findViewById(R.id.btncomfirm);
        btnPrev = findViewById(R.id.btnPrev);
        txtTotalMoney = findViewById(R.id.txtMoney);
        money = getMoney();
        txtTotalMoney.setText(String.valueOf(money));
        edtName.setText(MainActivity.users.getUserName());
        edtSDT.setText(MainActivity.users.getSDT());
    }

    private void addEvents() {
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @RequiresApi()
            @Override
            public void onClick(View v) {
               finish();

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final String address = edtAddress.getText().toString().trim();
                final String SDT = MainActivity.users.getSDT();
                final String name = MainActivity.users.getUserName().toString().trim();
                final String Money = String.valueOf(money);
                final String DateTime = java.time.LocalDateTime.now().toString();
                if(address.length() > 0 && SDT.length() > 0 && name.length() > 0 && Money.length() > 0 && DateTime.length() > 0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathInsertDataDH, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String MaDH) {
                            Log.d("MaDH",MaDH);
                            if (Integer.parseInt(MaDH) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.pathInsertDataCTDH, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("test",response);
                                        if (response.equals("1")){
                                            HomeActivity.arrayListCart.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã thêm dữ liệu giỏ hàng thành công");
                                            Intent intent = new Intent(InfoUsersActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
                                        }else {
                                            CheckConnection.ShowToast_Short(getApplicationContext(),response.toString());
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(InfoUsersActivity.this, "lỗi", Toast.LENGTH_SHORT).show();

                                    }
                                }){
                                    @Nullable
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i=0; i<HomeActivity.arrayListCart.size();i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                int priceAFood =(int) HomeActivity.arrayListCart.get(i).getPrice() / HomeActivity.arrayListCart.get(i).getSL();
                                                jsonObject.put("MaDH",MaDH);
                                                jsonObject.put("MaMonAn", HomeActivity.arrayListCart.get(i).getID());
                                                jsonObject.put("SL",HomeActivity.arrayListCart.get(i).getSL());
                                                jsonObject.put("Gia",priceAFood);
                                                jsonObject.put("TongTien",HomeActivity.arrayListCart.get(i).getPrice());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMapCT = new HashMap<String,String>();
                                        hashMapCT.put("json",jsonArray.toString());
                                        return hashMapCT;
                                    }
                                };
                                queue.add(request);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("SDT",SDT);
                            hashMap.put("Money",Money);
                            hashMap.put("DataTime",DateTime);
                            hashMap.put("Address",address);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại gữ liệu");
                }
            }
        });
    }
    private int getMoney(){
        int money = 0;
        for (int i=0; i< HomeActivity.arrayListCart.size(); i++){
            money += HomeActivity.arrayListCart.get(i).getPrice();
        }
        return money;
    }
}