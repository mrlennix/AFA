package com.food.abstractfood;

import android.app.FragmentManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;


public class SwiperAdapter extends FragmentStatePagerAdapter {


    public SwiperAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment foodfragment = new FoodFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count",position+1);

        foodfragment.setArguments(bundle);
        return foodfragment;
    }

    @Override
    public int getCount() {
        return 30;
    }
}
