package com.nguyenhuutin.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.adapter.NewFoodAdapter;
import com.nguyenhuutin.appdatdoan_ttcm.BarBerCueActivity;
import com.nguyenhuutin.appdatdoan_ttcm.CakeActivity;
import com.nguyenhuutin.appdatdoan_ttcm.DrinkActivity;
import com.nguyenhuutin.appdatdoan_ttcm.HotPotActivity;
import com.nguyenhuutin.appdatdoan_ttcm.LunchBoxActivity;
import com.nguyenhuutin.appdatdoan_ttcm.NoodleActivity;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.appdatdoan_ttcm.SweetActivity;
import com.nguyenhuutin.model.Food;
import com.nguyenhuutin.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment{
    ImageButton btnLunchBox, btnNoodle, btnCake, btnSweet, btnDrink, btnHotPot, btnBarbecue;
    RecyclerView recyclerViewHome;
    ViewFlipper viewFlipper;
    View view;
    ArrayList<Food> arrayNewFood;
    NewFoodAdapter newFoodAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        addLink();
        addEvents();
        actionViewFliper();
        GetDataNewFood();
        return view;
    }

    private void addLink() {
        btnBarbecue = (ImageButton) view.findViewById(R.id.btnBarbecue);
        btnCake = (ImageButton) view.findViewById(R.id.btnCake);
        btnHotPot = (ImageButton) view.findViewById(R.id.btnHotPot);
        btnDrink = (ImageButton) view.findViewById(R.id.btnDrink);
        btnSweet = (ImageButton) view.findViewById(R.id.btnSweet);
        btnLunchBox = (ImageButton) view.findViewById(R.id.btnLunchBox);
        btnNoodle = (ImageButton) view.findViewById(R.id.btnNoodle);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        recyclerViewHome = (RecyclerView) view.findViewById(R.id.recyclerViewHome);
        arrayNewFood  =new ArrayList<>();
        newFoodAdapter = new NewFoodAdapter(getActivity(),arrayNewFood);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerViewHome.setAdapter(newFoodAdapter);

    }

    private void addEvents() {
        btnLunchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LunchBoxActivity.class);
                startActivity(intent);

            }
        });
        btnNoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoodleActivity.class);
                startActivity(intent);

            }
        });
        btnCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CakeActivity.class);
                startActivity(intent);

            }
        });
        btnSweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SweetActivity.class);
                startActivity(intent);

            }
        });
        btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DrinkActivity.class);
                startActivity(intent);
            }
        });
        btnBarbecue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BarBerCueActivity.class);
                startActivity(intent);
            }
        });
        btnHotPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HotPotActivity.class);
                startActivity(intent);
            }
        });
    }

    private void actionViewFliper() {
        ArrayList<String> arrayBaner = new ArrayList<>();
        arrayBaner.add("https://chuphinhmonan.com/wp-content/uploads/2017/09/comtam.jpg");
        arrayBaner.add("https://dichoinhatban.com/wp-content/uploads/2020/01/Takoyaki.jpg");
        arrayBaner.add("https://nld.mediacdn.vn/thumb_w/540/2018/12/20/nhung-mon-an-viet-gay-dinh-dam-khi-xuat-hien-tren-bao-ngoai-nam-2018-hinh-5-15452927504911572495568.jpg");
        arrayBaner.add("https://s3-us-west-1.amazonaws.com/dangnho/media/uploads/2020/05/26115155/tapchidangnho-summer-185219015212-am-thuc-mien-trung.jpg");
        arrayBaner.add("https://chuphinhmonan.com/wp-content/uploads/2017/03/avalon-3-1.jpg");
        arrayBaner.add("http://media.tinmoi.vn/2015/06/05/thuc-u%E1%BB%8Dng-mua-nong.jpg");
        arrayBaner.add("https://noithat4mua.com/wp-content/uploads/2018/11/Mau-thiet-ke-quan-com-ga-van-phong-lacai-05.jpg");
        arrayBaner.add("https://cosp.com.vn/uploaded/pv1148.jpg");
        for (int i=0; i<arrayBaner.size();i++){
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity()).load(arrayBaner.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);


    }

    private void GetDataNewFood() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,Server.pathNewFood,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int ID = 0;
                    String namefood  = "";
                    int price = 0;
                    String image = "";
                    int tyoeoffood = 0;
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            namefood = jsonObject.getString("tenmonan");
                            price = jsonObject.getInt("gia");
                            image = jsonObject.getString("hinhanh");
                            tyoeoffood = jsonObject.getInt("loaimonan");
                            arrayNewFood.add(new Food(ID,namefood,price,image,tyoeoffood));
                            newFoodAdapter.notifyDataSetChanged();
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
}
