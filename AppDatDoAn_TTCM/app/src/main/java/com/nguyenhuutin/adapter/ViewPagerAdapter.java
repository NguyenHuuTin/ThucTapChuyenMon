package com.nguyenhuutin.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenhuutin.fragment.FragmentCart;
import com.nguyenhuutin.fragment.FragmentHome;
import com.nguyenhuutin.fragment.FragmentSearch;
import com.nguyenhuutin.fragment.FragmentMap;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:

                return new FragmentHome();
            case 1:
                return new FragmentCart();
            case 2:
                return new FragmentSearch();
            case 3:
                return new FragmentMap();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
