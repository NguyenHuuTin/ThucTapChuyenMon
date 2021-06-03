package com.nguyenhuutin.appdatdoan_ttcm;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.nguyenhuutin.adapter.menuAdapter;

import com.nguyenhuutin.fragment.FragmentBook;
import com.nguyenhuutin.fragment.FragmentHome;
import com.nguyenhuutin.fragment.FragmentInfo;
import com.nguyenhuutin.fragment.FragmentMap;
import com.nguyenhuutin.model.itemMenu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView lvMenu;
    ArrayList<itemMenu> arrayList;
    menuAdapter adapter;
    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_BOOK = 2;
    private static final int FRAGMENT_MAP = 3;
    private static final int FRAGMENT_INFO = 4;
    private int curron_fragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addLink();
        actionToolBar();
        actionMenu();
        catchOnItemListView();
        replaceFragment(new FragmentHome());


    }

    private void addLink() {
        toolbar = findViewById(R.id.Toolbar);
        drawerLayout = findViewById(R.id.Drawlayout);
        navigationView = findViewById(R.id.navigationview);
        lvMenu = findViewById(R.id.lvMenu);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void actionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new itemMenu("Trang Chính",R.drawable.house));
        arrayList.add(new itemMenu("Đặt Bàn",R.drawable.clipboard));
        arrayList.add(new itemMenu("Bản Đồ",R.drawable.map));
        arrayList.add(new itemMenu("Thông tin",R.drawable.info));
        arrayList.add(new itemMenu("Đăng Xuất",R.drawable.arrow));
        adapter = new menuAdapter(this,R.layout.item_menu,arrayList);
        lvMenu.setAdapter(adapter);
    }

    private void catchOnItemListView() {
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(FRAGMENT_HOME != curron_fragment){
                            replaceFragment(new FragmentHome());
                            curron_fragment = FRAGMENT_HOME;
                            toolbar.setTitle("Trang Chính");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(FRAGMENT_BOOK != curron_fragment){
                            replaceFragment(new FragmentBook());
                            curron_fragment = FRAGMENT_BOOK;
                            toolbar.setTitle("Đặt Bàn");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(FRAGMENT_MAP != curron_fragment){
                            replaceFragment(new FragmentMap());
                            curron_fragment = FRAGMENT_MAP;
                            toolbar.setTitle("Bản Đồ");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(FRAGMENT_INFO != curron_fragment){
                            replaceFragment(new FragmentInfo());
                            curron_fragment = FRAGMENT_INFO;
                            toolbar.setTitle("Thông Tin");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmenthooome,fragment);
        fragmentTransaction.commit();
    }

}