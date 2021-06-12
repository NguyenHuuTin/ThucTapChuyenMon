package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.nguyenhuutin.model.Food;
import com.nguyenhuutin.model.Users;
import com.nguyenhuutin.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtUserName, txtPass;
    Button btnLogin, btnLoginForGoogle;
    TextView lblForgot, lblRegistration;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 001;
    ArrayList<Users> arrayUsers;
    public static Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addLink();
        addEvent();
        GetDataUsers();
    }

    private void addLink() {
        txtUserName = findViewById(R.id.txtSDT);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginForGoogle = findViewById(R.id.btnLoginForGoogle);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        arrayUsers = new ArrayList<>();

//        signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        lblForgot = findViewById(R.id.lblforgot);
//        lblRegistration = findViewById(R.id.lblforgot);


    }

    private void addEvent() {
//        lblRegistration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
//                startActivity(intent);
//            }
//        });
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                    // ...
//                }
//            }
//        });
        btnLoginForGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnLoginForGoogle){
                    signIn();

                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = checkData(txtUserName.getText().toString().trim(),txtPass.getText().toString().trim());
                if(position == -1){
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    String SDT = arrayUsers.get(position).getSDT().toString().trim();
                    String Email = arrayUsers.get(position).getEmail().toString().trim();
                    String Name = arrayUsers.get(position).getUserName().toString().trim();
                    String Pass = arrayUsers.get(position).getPassword().toString().trim();
                    users = new Users(SDT,Email,Name,Pass,2);
//                    intent.putExtra("user",arrayUsers.get(position));
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            int position = checkEmail(account.getEmail().toString());
            if(position == -1){
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String SDT = arrayUsers.get(position).getSDT().toString().trim();
                String Email = arrayUsers.get(position).getEmail().toString().trim();
                String Name = arrayUsers.get(position).getUserName().toString().trim();
                String Pass = arrayUsers.get(position).getPassword().toString().trim();
                users = new Users(SDT,Email,Name,Pass,2);
//                intent.putExtra("user",arrayUsers.get(position));
                startActivity(intent);
            }


        } catch (ApiException e) {
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void GetDataUsers() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.pathUsers,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    Toast.makeText(MainActivity.this, "có dữ liệu", Toast.LENGTH_SHORT).show();
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
                else {
                    Toast.makeText(MainActivity.this, "không có dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "lỗi Get Data", Toast.LENGTH_SHORT).show();
                Log.d("Data",error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    private int checkData(String name, String pass){
        int position = -1;
        for (int i=0; i<arrayUsers.size();i++){
            if(arrayUsers.get(i).SDT.equalsIgnoreCase(name) || arrayUsers.get(i).Email.equalsIgnoreCase(name)){
                if (arrayUsers.get(i).Password.equalsIgnoreCase(pass)){
                    position = i;
                    break;
                }

            }
        }
        return position;
    }
    private int checkEmail(String email){
        int position = -1;
        for (int i=0; i< arrayUsers.size();i++){
            if(arrayUsers.get(i).Email.equalsIgnoreCase(email)){
                position = i;
                break;
            }
        }
        return position;
    }
}