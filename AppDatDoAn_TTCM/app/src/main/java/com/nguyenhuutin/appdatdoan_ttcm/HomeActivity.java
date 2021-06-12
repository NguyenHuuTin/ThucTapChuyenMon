package com.nguyenhuutin.appdatdoan_ttcm;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nguyenhuutin.adapter.ViewPagerAdapter;
import com.nguyenhuutin.adapter.menuAdapter;

import com.nguyenhuutin.fragment.FragmentHome;
import com.nguyenhuutin.fragment.FragmentInfo;
import com.nguyenhuutin.fragment.FragmentContactInfo;
import com.nguyenhuutin.model.Cart;
import com.nguyenhuutin.model.itemMenu;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    ListView lvMenu;
//    ArrayList<itemMenu> arrayList;
//    menuAdapter adapter;
    public static AHBottomNavigation bottomNavigationView;
    AHBottomNavigationViewPager viewPager;
    public static int mCountFood;

    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_BOOK = 2;
    private static final int FRAGMENT_MAP = 3;
    private static final int FRAGMENT_INFO = 4;
    private int curron_fragment = FRAGMENT_HOME;
    public static ArrayList<Cart> arrayListCart;

//    public static Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addLink();
        actionToolBar();
        setUpViewPager();
        setCountFoodInart(2);
        //actionMenu();
        //catchOnItemListView();
//        replaceFragment(new FragmentHome());
    }

    private void addLink() {
        toolbar = findViewById(R.id.Toolbar);
//        drawerLayout = findViewById(R.id.Drawlayout);
//        navigationView = findViewById(R.id.navigationview);
//        lvMenu = findViewById(R.id.lvMenu);
        viewPager = findViewById(R.id.ViewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setDefaultBackgroundColor(Color.parseColor("#006db3"));
        bottomNavigationView.setAccentColor(Color.parseColor("#FF5722"));
        bottomNavigationView.setInactiveColor(Color.parseColor("#FFFFFF"));
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_home:
//                        viewPager.setCurrentItem(0);
//                        break;
//                    case R.id.action_cart:
//                        viewPager.setCurrentItem(1);
//                        break;
//                    case R.id.action_search:
//                        viewPager.setCurrentItem(2);
//                        break;
//                    case R.id.action_contect:
//                        viewPager.setCurrentItem(3);
//                        break;
//                }
//                return true;
//            }
//        });
        if (arrayListCart != null){

        }else {
            arrayListCart = new ArrayList<>();
        }


//        users = (Users) getIntent().getSerializableExtra("user");

    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(R.drawable.menu);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

    }

    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPagingEnabled(true);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.baseline_home_white_24, R.color.custum_color_bottom_nav);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Cart", R.drawable.baseline_shopping_cart_white_24, R.color.custum_color_bottom_nav);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Search", R.drawable.baseline_search_white_24, R.color.custum_color_bottom_nav);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Contect", R.drawable.baseline_account_circle_white_24, R.color.custum_color_bottom_nav);

// Add items
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.addItem(item3);
        bottomNavigationView.addItem(item4);
        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position);
                return true;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                switch (position){
//                    case 0:
//                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
//                        break;
//                    case 1:
//                        bottomNavigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
//                        break;
//                    case 2:
//                        bottomNavigationView.getMenu().findItem(R.id.action_search).setChecked(true);
//                        break;
//                    case 3:
//                        bottomNavigationView.getMenu().findItem(R.id.action_contect).setChecked(true);
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }
    public void setCountFoodInart(int count){
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.bottomNavigationIcon))
                .setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white))
                .build();
        bottomNavigationView.setNotification(notification, 1);
    }

    public static int getmCountFood() {
        return mCountFood;
    }
    //    private void actionMenu() {
//        arrayList = new ArrayList<>();
//        arrayList.add(new itemMenu("Trang Chính",R.drawable.house));
//        arrayList.add(new itemMenu("Thông Tin",R.drawable.info));
//        arrayList.add(new itemMenu("Liên Hệ",R.drawable.contact));
//        arrayList.add(new itemMenu("Tài Khoản",R.drawable.user));
//        arrayList.add(new itemMenu("Đăng Xuất",R.drawable.arrow));
//        adapter = new menuAdapter(this,R.layout.item_menu,arrayList);
//        lvMenu.setAdapter(adapter);
//    }
//
//    private void catchOnItemListView() {
//        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        if(FRAGMENT_HOME != curron_fragment){
//                            replaceFragment(new FragmentHome());
//                            curron_fragment = FRAGMENT_HOME;
//                            toolbar.setTitle("Trang Chính");
//                        }
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 1:
//                        if(FRAGMENT_BOOK != curron_fragment){
////                            replaceFragment(new FragmentBook());
////                            curron_fragment = FRAGMENT_BOOK;
////                            toolbar.setTitle("Đặt Bàn");
//                            curron_fragment = FRAGMENT_BOOK;
//                            Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
//                            startActivity(intent);
//                        }
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 2:
//                        if(FRAGMENT_MAP != curron_fragment){
//                            replaceFragment(new FragmentContactInfo());
//                            curron_fragment = FRAGMENT_MAP;
//                            toolbar.setTitle("Thông Tin Liên Hệ");
//                        }
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 3:
//                        if(FRAGMENT_INFO != curron_fragment){
//                            replaceFragment(new FragmentInfo());
//                            curron_fragment = FRAGMENT_INFO;
//                            toolbar.setTitle("Thông Tin");
//                        }
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 4:
//                        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                }
//            }
//        });
//    }
//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragmenthooome,fragment);
//        fragmentTransaction.commit();
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.itemCart:
//                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
//                startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }


}