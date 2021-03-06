package com.nguyenhuutin.appdatdoan_ttcm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.nguyenhuutin.adapter.ViewPagerAdapter;

import com.nguyenhuutin.model.Cart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    public static AHBottomNavigation bottomNavigationView;
    AHBottomNavigationViewPager viewPager;
    public static int mCountFood = 0;
    public static ArrayList<Cart> arrayListCart;
    TextView NameUser;
    CircleImageView imguser;
    LinearLayout LinearInfoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addLink();
        actionImg();
        addEvent();
        actionToolBar();
        setUpViewPager();
        checkCountFood();
        if (arrayListCart != null){

        }else {
            arrayListCart = new ArrayList<>();
        }
    }

    private void actionImg() {
        if (MainActivity.img.toString().trim() =="none"){
            imguser.setImageResource(R.drawable.baseline_account_circle_white_20);
        }
        else if (MainActivity.img.toString().trim() != "none"){
            Picasso.with(HomeActivity.this).load(MainActivity.img.toString().trim()).into(imguser);
        }
    }

    private void addLink() {
        imguser = findViewById(R.id.imgUser);
        NameUser = findViewById(R.id.NameUsers);
        NameUser.setText(MainActivity.users.getUserName().toString().trim());
        toolbar = findViewById(R.id.Toolbar);
        viewPager = findViewById(R.id.ViewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setDefaultBackgroundColor(Color.parseColor("#006db3"));
        bottomNavigationView.setAccentColor(Color.parseColor("#FF5722"));
        bottomNavigationView.setInactiveColor(Color.parseColor("#FFFFFF"));
        LinearInfoUser = findViewById(R.id.LinearInfoUsers);
    }

    private void addEvent() {
        LinearInfoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InfoOrderActivity.class);
                startActivity(intent);

            }
        });
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

    public static void setCountFoodInart(int count){
        mCountFood = count;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .build();
        bottomNavigationView.setNotification(notification, 1);
    }
    public static void setNoCountFoodInart(){
        AHNotification notification = new AHNotification.Builder()
                .setText(null)
                .build();
        bottomNavigationView.setNotification(notification, 1);
    }
    public static void checkCountFood() {
        if(mCountFood > 0){
            setCountFoodInart(mCountFood);
        }
        else if (mCountFood == 0){
            setNoCountFoodInart();

        }
    }

    public static int getmCountFood() {
        return mCountFood;
    }

    //    private void actionMenu() {
//        arrayList = new ArrayList<>();
//        arrayList.add(new itemMenu("Trang Ch??nh",R.drawable.house));
//        arrayList.add(new itemMenu("Th??ng Tin",R.drawable.info));
//        arrayList.add(new itemMenu("Li??n H???",R.drawable.contact));
//        arrayList.add(new itemMenu("T??i Kho???n",R.drawable.user));
//        arrayList.add(new itemMenu("????ng Xu???t",R.drawable.arrow));
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
//                            toolbar.setTitle("Trang Ch??nh");
//                        }
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 1:
//                        if(FRAGMENT_BOOK != curron_fragment){
////                            replaceFragment(new FragmentBook());
////                            curron_fragment = FRAGMENT_BOOK;
////                            toolbar.setTitle("?????t B??n");
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
//                            toolbar.setTitle("Th??ng Tin Li??n H???");
//                        }
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 3:
//                        if(FRAGMENT_INFO != curron_fragment){
//                            replaceFragment(new FragmentInfo());
//                            curron_fragment = FRAGMENT_INFO;
//                            toolbar.setTitle("Th??ng Tin");
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