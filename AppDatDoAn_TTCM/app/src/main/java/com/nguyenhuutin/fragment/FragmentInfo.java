package com.nguyenhuutin.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.appdatdoan_ttcm.ChangePassActivity;
import com.nguyenhuutin.appdatdoan_ttcm.HomeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.MainActivity;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.model.Users;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentInfo extends Fragment {
    EditText txtsdt, txtemail, txtname;
    Button btnchangepass;
    View view;
    private HomeActivity homeActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info,container,false);
        addLink();

        addEvents();
        return view;
    }


    private void addLink() {
        txtemail = view.findViewById(R.id.txtmail);
        txtsdt = view.findViewById(R.id.txtSDT);
        txtname = view.findViewById(R.id.txtname);
        btnchangepass = view.findViewById(R.id.btnChange);
//        homeActivity = (HomeActivity) getActivity();
//        txtsdt.setText(homeActivity.getSDT());
//        txtname.setText(homeActivity.getName());
//        txtemail.setText(homeActivity.getEmail());
        txtname.setText(MainActivity.users.getUserName().toString().trim());
        txtsdt.setText(MainActivity.users.getSDT().toString().trim());
        txtemail.setText(MainActivity.users.getEmail().toString().trim());

    }

    private void addEvents() {
        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassActivity.class);
//                intent.putExtra("Emails",homeActivity.getEmail().toString().trim());
//                intent.putExtra("sdt",homeActivity.getSDT().toString().trim());
                startActivity(intent);

            }
        });

    }




}
