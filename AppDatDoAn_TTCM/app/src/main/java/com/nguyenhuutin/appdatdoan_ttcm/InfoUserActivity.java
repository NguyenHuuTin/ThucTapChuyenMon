package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.model.Users;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoUserActivity extends AppCompatActivity {
    TextView lblInfoSDT, lblInfoEmail, lblInfoName;
    CheckBox ckbChangePass;
    EditText txtpass, txtnewpass, txtpassagain;
    Button btnchange;
    CardView CardViewChangePass;
    ArrayList<Users> arrayUsers;
    Toolbar toolbar;
    private String sdt;
    private String email;
    private String Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        addLink();
        ActionToolbar();
        GetDataUsers();
        addEvents();
    }

    private void addLink() {
        CardViewChangePass = findViewById(R.id.CardViewChngePass);
        email = MainActivity.users.getEmail();
        sdt = MainActivity.users.getSDT();
        lblInfoSDT = findViewById(R.id.lblInfoUsesSDT);
        lblInfoEmail = findViewById(R.id.lblInfoUserEmail);
        lblInfoName = findViewById(R.id.lblInfoUserName);
        lblInfoName.setText(MainActivity.users.getUserName());
        lblInfoSDT.setText(sdt);
        lblInfoEmail.setText(email);
        txtpass = findViewById(R.id.txtpass);
        txtnewpass = findViewById(R.id.txtnewpass);
        txtpassagain = findViewById(R.id.txtpassagain);
        btnchange = findViewById(R.id.btnChange);
        toolbar = findViewById(R.id.ToolbarFood);
        arrayUsers = new ArrayList<>();
        ckbChangePass = findViewById(R.id.ckbChangePass);
        CardViewChangePass.setVisibility(View.INVISIBLE);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Thông tin");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void addEvents() {
        ckbChangePass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    CardViewChangePass.setVisibility(View.VISIBLE);
                }else {
                    CardViewChangePass.setVisibility(View.INVISIBLE);
                }
            }
        });
        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = checkData(email,sdt);
                Pass = arrayUsers.get(position).getPassword().toString().trim();
                if(!txtpass.getText().toString().isEmpty() && !txtnewpass.getText().toString().isEmpty() && !txtpassagain.getText().toString().isEmpty()){
                    if(txtpass.getText().toString().equalsIgnoreCase(Pass)){
                        if (txtnewpass.getText().toString().equalsIgnoreCase(txtpassagain.getText().toString())){
                            updateUser();
                        }
                        else {
                            txtnewpass.setText("");
                            txtpassagain.setText("");
                            txtpass.setText("");
                            Toast.makeText(InfoUserActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        txtnewpass.setText("");
                        txtpassagain.setText("");
                        txtpass.setText("");
                        Toast.makeText(InfoUserActivity.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(InfoUserActivity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    private void updateUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathUpdateUsers,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(InfoUserActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(InfoUserActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InfoUserActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Lỗi!\n" + error.toString());

                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("SDT",sdt);
                params.put("Password",txtnewpass.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void GetDataUsers() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.pathUsers,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    String SDT ="";
                    String Email ="";
                    String UserName ="";
                    String Password = "";
                    int Permission = 0;
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SDT = jsonObject.getString("SDT");
                            Email = jsonObject.getString("Email");
                            UserName = jsonObject.getString("UserName");
                            Password = jsonObject.getString("Password");
                            Permission = jsonObject.getInt("Permission");
                            arrayUsers.add(new Users(SDT,Email,UserName,Password,Permission));
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    private int checkData(String email, String SDT){
        int position = -1;
        for (int i=0; i< arrayUsers.size();i++){
            if(arrayUsers.get(i).Email.equalsIgnoreCase(email) && arrayUsers.get(i).SDT.equalsIgnoreCase(SDT)){
                position = i;
                break;
            }
        }
        return position;
    }
}