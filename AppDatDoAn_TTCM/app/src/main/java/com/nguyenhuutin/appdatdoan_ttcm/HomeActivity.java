package com.nguyenhuutin.appdatdoan_ttcm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.nguyenhuutin.adapter.menuAdapter;
import com.nguyenhuutin.model.itemMenu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView lvMenu;
    ArrayList<itemMenu> arrayList;
    menuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addLink();
        ActionViewFilipper();
        actionToolBar();
        actionMenu();
    }

    private void addLink() {
        viewFlipper = findViewById(R.id.viewflipper);
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
        arrayList.add(new itemMenu("Trang Chính",R.drawable.noodle_icon));
        arrayList.add(new itemMenu("Đặt Bàn",R.drawable.snack_icon));
        arrayList.add(new itemMenu("Bản Đồ",R.drawable.cake));
        arrayList.add(new itemMenu("Thông tin",R.drawable.barbecue));
        arrayList.add(new itemMenu("Đăng Xuất",R.drawable.drink));
        adapter = new menuAdapter(this,R.layout.item_menu,arrayList);
        lvMenu.setAdapter(adapter);
    }

    private void ActionViewFilipper() {
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
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arrayBaner.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);


    }
}