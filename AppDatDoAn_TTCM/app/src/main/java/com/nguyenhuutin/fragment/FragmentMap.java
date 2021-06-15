package com.nguyenhuutin.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nguyenhuutin.appdatdoan_ttcm.R;

public class FragmentMap extends Fragment {
    private GoogleMap mMap;
    LinearLayout itemCallPhone, itemSendEmail, itemSendSMS;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        //Asyc map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;
                LatLng address = new LatLng(10.845393336401367, 106.79430604160234);
                mMap.addMarker(new MarkerOptions().position(address).title("Food Order App").snippet("448 Lê Văn Việt, Tăng Nhơn Phú A, Thành phố Thủ Đức, Thành phố Hồ Chí Minh"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(address,15));
            }
        });
        addLink();
        addEvent();
        return view;
    }

    private void addLink() {
        itemCallPhone = view.findViewById(R.id.itemCallPhone);
        itemSendEmail = view.findViewById(R.id.itemSendEmail);
        itemSendSMS = view.findViewById(R.id.itemSendSMS);
    }

    private void addEvent() {
        itemCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0941866373"));
                startActivity(intent);
            }
        });
        itemSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:nguyenhuutin369@gmail.com"));
                startActivity(intent);
            }
        });
        itemSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("sms:0941866373"));
                startActivity(intent);

            }
        });
    }
}