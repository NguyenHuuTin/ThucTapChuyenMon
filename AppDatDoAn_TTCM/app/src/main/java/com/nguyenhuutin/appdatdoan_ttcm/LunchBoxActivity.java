package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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
import com.nguyenhuutin.ultil.AnimationUltil;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LunchBoxActivity extends AppCompatActivity {
    RecyclerView recyclerViewLunchBox;
    ArrayList<Food> arrayFoods;
    FoodAdapter foodAdapter;
    Toolbar toolbar;
    View viewEndAnimation;
    ImageView viewAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_box);
        addLinks();
        ActionToolbar();
        addEvents();
        GetDataFoods();
    }

    private void addLinks() {
        toolbar = findViewById(R.id.ToolbarFood);
        recyclerViewLunchBox = findViewById(R.id.RecyclerViewLunchBox);
        arrayFoods = new ArrayList<>();
        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);
//        foodAdapter = new FoodAdapter(LunchBoxActivity.this,arrayFoods);
//        recyclerViewLunchBox.setHasFixedSize(true);
//        recyclerViewLunchBox.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
//        recyclerViewLunchBox.setAdapter(foodAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewLunchBox.setLayoutManager(linearLayoutManager);
        foodAdapter = new FoodAdapter(this, arrayFoods, new FoodAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCa, Food food) {
                AnimationUltil.translateAnimation(viewAnimation, imgAddToCa, viewEndAnimation, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        recyclerViewLunchBox.setAdapter(foodAdapter);
    }

//    public View getViewEndAnimation() {
//        return viewEndAnimation;
//    }
//
//    public ImageView getViewAnimation() {
//        return viewAnimation;
//    }

    private void addEvents() {
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("C??m Tr??a");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Intent intent = new Intent(LunchBoxActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_right_in,R.anim.activity_right_out);
            }
        });

    }
    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


    private void GetDataFoods() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.pathFoodLunchBox,null, new Response.Listener<JSONArray>() {
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