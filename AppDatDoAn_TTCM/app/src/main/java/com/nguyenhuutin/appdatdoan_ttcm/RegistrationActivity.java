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
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    Button btnNext;
    EditText txtSDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        addLink();
        addEvent();

    }

    private void addLink() {
        btnNext = findViewById(R.id.btnNext);
        txtSDT = findViewById(R.id.txtSDT);
    }

    private void addEvent() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSDT.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Vui lòng nhập số điện thoại!", Toast.LENGTH_LONG).show();
                }
                else {

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentOTP fragmentOTP = new FragmentOTP();
                    fragmentTransaction.add(R.id.FrameContent,fragmentOTP);
                    fragmentTransaction.commit();
                }
            }
        });
    }




}