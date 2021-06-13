package com.nguyenhuutin.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhuutin.adapter.FoodAdapter;
import com.nguyenhuutin.appdatdoan_ttcm.R;
import com.nguyenhuutin.model.Food;
import com.nguyenhuutin.ultil.AnimationUltil;
import com.nguyenhuutin.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentSearch extends Fragment {
    View view;
    RecyclerView recyclerViewSearch;
    ArrayList<Food> arrayFoods;
    FoodAdapter foodAdapter;
    View viewEndAnimation;
    ImageView viewAnimation;
    EditText edtSearch;
    ImageButton btnSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,container,false);
        addLinks();
        addEvent();
        return view;
    }

    private void addLinks() {
        recyclerViewSearch = view.findViewById(R.id.RecyclerViewSearch);
        arrayFoods = new ArrayList<>();
        edtSearch = view.findViewById(R.id.edtSearch);
        edtSearch.setText("m");
        btnSearch = view.findViewById(R.id.btnSearch);
        viewEndAnimation = view.findViewById(R.id.view_end_animation);
        viewAnimation = view.findViewById(R.id.view_animation);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerViewSearch.setLayoutManager(linearLayoutManager);
        foodAdapter = new FoodAdapter(getActivity(), arrayFoods, new FoodAdapter.IClickAddToCartListener() {
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

        recyclerViewSearch.setAdapter(foodAdapter);
    }

    private void addEvent() {
        if (!edtSearch.getText().toString().trim().isEmpty()){
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetDataFoods();

                }
            });
        }

    }
    private void GetDataFoods() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathGetDataFoodSearch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){

                    try {
                        JSONArray array = new JSONArray(response);
                        int ID = 0;
                        String namefood  = "";
                        int price = 0;
                        String image = "";
                        int tyoeoffood = 0;
                        for (int i=0;i<array.length();i++){
                            try {
                                JSONObject jsonObject = array.getJSONObject(i);
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

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "lỗi", Toast.LENGTH_SHORT).show();
                
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Search",edtSearch.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);;
        
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, Server.pathGetDataFoodSearch,null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response != null){
//                    int ID = 0;
//                    String namefood  = "";
//                    int price = 0;
//                    String image = "";
//                    int tyoeoffood = 0;
//                    for (int i=0;i<response.length();i++){
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            ID = jsonObject.getInt("id");
//                            namefood = jsonObject.getString("tenmonan");
//                            price = jsonObject.getInt("gia");
//                            image = jsonObject.getString("hinhanh");
//                            tyoeoffood = jsonObject.getInt("loaimonan");
//                            arrayFoods.add(new Food(ID,namefood,price,image,tyoeoffood));
//                            foodAdapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//                    }
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("search",error.toString());
//            }
//        }
//        ){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("Search",edtSearch.getText().toString().trim());
//                return params;
//            }
//        };
//        requestQueue.add(jsonArrayRequest);

    }
}
