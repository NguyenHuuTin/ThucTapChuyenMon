package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.adapter.FoodAdapter;
import com.nguyenhuutin.model.Food;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CakeActivity extends AppCompatActivity {
    ImageView imgBack, imgCart;
    TextView txtTitleToolbar;
    RecyclerView recyclerViewCake;
    ArrayList<Food> arrayFoods;
    FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        addLinks();
        GetDataFoods();
        addEvents();
    }

    private void addLinks() {
        imgBack = findViewById(R.id.imgBack);
        imgCart = findViewById(R.id.imgCarts);
        txtTitleToolbar = findViewById(R.id.txtTitleToolbar);
        txtTitleToolbar.setText("Các Món Bánh");
        recyclerViewCake = findViewById(R.id.RecyclerViewCake);
        arrayFoods = new ArrayList<>();
        foodAdapter = new FoodAdapter(getApplicationContext(),arrayFoods);
        recyclerViewCake.setHasFixedSize(true);
        recyclerViewCake.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerViewCake.setAdapter(foodAdapter);

    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CakeActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void GetDataFoods() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.pathFoodCake,null, new Response.Listener<JSONArray>() {
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
                            arrayFoods.add(new Food(ID,namefood,price,image,tyoeoffood));
                            foodAdapter.notifyDataSetChanged();
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