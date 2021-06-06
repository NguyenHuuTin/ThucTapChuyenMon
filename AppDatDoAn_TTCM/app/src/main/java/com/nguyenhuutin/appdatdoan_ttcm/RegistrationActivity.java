package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.nguyenhuutin.model.Users;
import com.nguyenhuutin.ultil.Server;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    Button btnNext;
    EditText txtSDT, txtname, txtmail, txtpassword, txtpassAgain;
    ImageView imvhinh;
    Users users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        addLink();
        addEvent();
        getInfomation();

    }

    private void getInfomation() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

                txtname.setText(acct.getDisplayName().toString());
                txtmail.setText(acct.getEmail().toString());
                Picasso.with(this).load(String.valueOf(acct.getPhotoUrl())).into(imvhinh);

        }
    }

    private void addLink() {
        btnNext = findViewById(R.id.btnNext);
        txtSDT = findViewById(R.id.txtSDT);
        txtname = findViewById(R.id.txtname);
        txtmail = findViewById(R.id.txtmail);
        imvhinh = findViewById(R.id.imvhinh);
        txtpassword = findViewById(R.id.txtpassword);
        txtpassAgain = findViewById(R.id.txtpasAgain);
    }

    private void addEvent() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtSDT.getText().toString().isEmpty() && !txtpassword.getText().toString().isEmpty() && !txtpassAgain.getText().toString().isEmpty()){
                    if (txtpassword.getText().toString().equalsIgnoreCase(txtpassAgain.getText().toString())){
                        String SDT = txtSDT.getText().toString().trim();
                        String Email = txtmail.getText().toString().trim();
                        String Name = txtname.getText().toString().trim();
                        String Pass = txtpassword.getText().toString().trim();
                        MainActivity.users = new Users(SDT,Email,Name,Pass,2);
                        insertDataUsers();
                    }
                    else {
                        txtpassword.setText("");
                        txtpassAgain.setText("");
                        Toast.makeText(RegistrationActivity.this, "Invalid PassWord", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Add My NumberPhone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertDataUsers() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathInsertUsers,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(RegistrationActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent =  new Intent(RegistrationActivity.this, HomeActivity.class);
                            String SDT = txtSDT.getText().toString().trim();
                            String Email = txtmail.getText().toString().trim();
                            String Name = txtname.getText().toString().trim();
                            String Pass = txtpassword.getText().toString().trim();
//                            intent.putExtra("SDT",SDT);
//                            intent.putExtra("Email",Email);
//                            intent.putExtra("Name",Name);
//                            intent.putExtra("Pass",Pass);
                            users = new Users(SDT,Email,Name,Pass,2);
                            intent.putExtra("user",users);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistrationActivity.this, "Xảy ra lỗi!!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Lỗi!\n" + error.toString());

                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("SDT",txtSDT.getText().toString().trim());
                params.put("UserName", txtname.getText().toString().trim());
                params.put("Password", txtpassword.getText().toString().trim());
                params.put("Email", txtmail.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}