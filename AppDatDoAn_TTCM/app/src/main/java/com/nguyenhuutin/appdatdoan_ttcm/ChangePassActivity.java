package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ChangePassActivity extends AppCompatActivity {
    EditText txtpass, txtnewpass, txtpassagain;
    Button btnchange;
    ArrayList<Users> arrayUsers;
    Toolbar toolbar;
    private String sdt;
    private String email;
    private String Pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        addLink();
        ActionToolbar();
        GetDataUsers();
        addEvents();
    }

    private void addLink() {
        txtpass = findViewById(R.id.txtpass);
        txtnewpass = findViewById(R.id.txtnewpass);
        txtpassagain = findViewById(R.id.txtpassagain);
        btnchange = findViewById(R.id.btnChange);
        toolbar = findViewById(R.id.ToolbarFood);
        arrayUsers = new ArrayList<>();
        email = MainActivity.users.getEmail().toString().trim();
        sdt = MainActivity.users.getSDT().toString().trim();

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("?????i M???t Kh???u");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addEvents() {
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
                            Toast.makeText(ChangePassActivity.this, "M???t kh???u kh??ng tr??ng kh???p", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        txtnewpass.setText("");
                        txtpassagain.setText("");
                        txtpass.setText("");
                        Toast.makeText(ChangePassActivity.this, "M???t kh???u kh??ng ????ng", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ChangePassActivity.this, "H??y nh???p ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(ChangePassActivity.this, "?????i m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(ChangePassActivity.this, "?????i m???t kh???u th???t b???i", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChangePassActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("AAA","L???i!\n" + error.toString());

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