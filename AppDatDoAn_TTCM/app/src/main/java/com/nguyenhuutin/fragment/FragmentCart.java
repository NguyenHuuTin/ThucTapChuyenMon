package com.nguyenhuutin.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.adapter.CartAdapter;
import com.nguyenhuutin.appdatdoan_ttcm.HomeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.MainActivity;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.ultil.CheckConnection;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class FragmentCart extends Fragment {
    View view;
    public static ListView lvCart;
    public static LinearLayout txtnotification;
    static TextView txtToltalMoney;
    Button  btnOrder;
    EditText edtAddress;
    public static CartAdapter cartAdapter;
    private int money;
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

    private void addLink() {
        lvCart = view.findViewById(R.id.lvCart);
        txtnotification = view.findViewById(R.id.txtnotification);
        txtToltalMoney = view.findViewById(R.id.txtToltalMoney);
        btnOrder = view.findViewById(R.id.btnOrder);
        cartAdapter = new CartAdapter(getActivity(), HomeActivity.arrayListCart);
        cartAdapter.notifyDataSetChanged();
        lvCart.setAdapter(cartAdapter);
        edtAddress = view.findViewById(R.id.edtAddressUsers);
        money = getMoney();

    }

    private void addEvents() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(HomeActivity.arrayListCart.size() > 0){
                    final String address = edtAddress.getText().toString().trim();
                    final String SDT = MainActivity.users.getSDT();
                    final String name = MainActivity.users.getUserName().toString().trim();
                    final String Money = String.valueOf(money);
                    final String DateTime = java.time.LocalDateTime.now().toString();
                    if(address.length() > 0 && SDT.length() > 0 && name.length() > 0 && Money.length() > 0 && DateTime.length() > 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Xác nhận");
                        builder.setMessage("Bạn có chắc đặt đơn hàng này!!");
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathInsertDataDH, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(final String MaDH) {
                                        Log.d("MaDH",MaDH);
                                        if (Integer.parseInt(MaDH) > 0){
                                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                                            StringRequest request = new StringRequest(Request.Method.POST, Server.pathInsertDataCTDH, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    Log.d("test",response);
                                                    if (response.equals("1")){
                                                        HomeActivity.arrayListCart.clear();
                                                        HomeActivity.mCountFood = 0;
                                                        HomeActivity.checkCountFood();
                                                        CheckConnection.ShowToast_Short(getActivity(),"Bạn đã thêm dữ liệu giỏ hàng thành công");
                                                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                        startActivity(intent);
                                                        CheckConnection.ShowToast_Short(getActivity(),"Mời bạn tiếp tục mua hàng");
                                                    }else {
                                                        CheckConnection.ShowToast_Short(getActivity(),response.toString());
                                                    }

                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(getActivity(), "lỗi", Toast.LENGTH_SHORT).show();

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

                    }else {
                        CheckConnection.ShowToast_Short(getActivity(),"Hãy cho biết bạn muốn giao đến đâu");
                    }
                }else {
                    CheckConnection.ShowToast_Short(getActivity(),"Giỏ hàng của bạn đang trống");

                }
            }
        });
    }


    public static void checkData() {
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
    private int getMoney(){
        int money = 0;
        for (int i=0; i< HomeActivity.arrayListCart.size(); i++){
            money += HomeActivity.arrayListCart.get(i).getPrice();
        }
        return money;
    }
}
