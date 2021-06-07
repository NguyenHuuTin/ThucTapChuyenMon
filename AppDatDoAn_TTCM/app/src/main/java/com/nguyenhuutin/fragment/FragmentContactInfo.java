package com.nguyenhuutin.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.gms.maps.GoogleMap;
import com.nguyenhuutin.appdatdoan_ttcm.R;

public class FragmentContactInfo extends Fragment{
    CardView cardViewSDT, cardViewEmail;
    View view;

    GoogleMap map;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_contact_info,container,false);
        addLink();
        addEvent();
        return view;
    }

    private void addEvent() {
        cardViewSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0941866373"));
                startActivity(intent);
            }
        });
        cardViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:nguyenhuutin369@gmail.com"));
                startActivity(intent);
            }
        });
    }

    private void addLink() {
        cardViewSDT = view.findViewById(R.id.carđViewSDT);
        cardViewEmail = view.findViewById(R.id.cardViewEmail);

    }


}
