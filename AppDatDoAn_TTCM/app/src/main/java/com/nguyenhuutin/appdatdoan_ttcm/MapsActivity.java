package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbar;
    Button btnmap,btnhybirrd, btnterrain, btnsatellite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        toolbar = findViewById(R.id.ToolbarFood);
        addEvent();
        ActionToolbar();


    }

    private void addEvent() {
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Địa Chỉ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng address = new LatLng(10.845393336401367, 106.79430604160234);
        mMap.addMarker(new MarkerOptions().position(address).title("Quán T Food").snippet("448 Lê Văn Việt, Tăng Nhơn Phú A, Thành phố Thủ Đức, Thành phố Hồ Chí Minh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(address,17));
//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(address).zoom(90).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}