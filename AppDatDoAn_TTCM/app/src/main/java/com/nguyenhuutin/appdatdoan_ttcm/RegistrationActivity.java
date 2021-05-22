package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class RegistrationActivity extends AppCompatActivity {
    Button btnNext;
    EditText txtSDT, txtname, txtmail, txtpassword, txtpassAgain;
    ImageView imvhinh;


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
                Glide.with(this).load(String.valueOf(acct.getPhotoUrl())).into(imvhinh);

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
                        Toast.makeText(RegistrationActivity.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Invalid PassWord", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Add My NumberPhone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}