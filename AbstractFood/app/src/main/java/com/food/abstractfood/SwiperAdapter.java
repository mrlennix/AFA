package com.food.abstractfood;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class SwiperAdapter extends FragmentStatePagerAdapter {


    public SwiperAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }
    private ArrayList<String> data = new ArrayList<>();
    private FoodFragment prevcurrent;
    @Override
    public Fragment getItem(int position) {

        Fragment foodfragment = new FoodFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count",position+1);
        foodfragment.setArguments(bundle);
        int i;
        for(i=0;i<data.size();i++)
        {
            System.err.println(data.get(i));
        }

        return foodfragment;
    }

    @Override
    public int getCount() {
        return 30;
    }


}
