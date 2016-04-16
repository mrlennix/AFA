package com.food.abstractfood;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity{

    ViewPager viewPager;
    ViewPager viewPager2;
    SwipeAdapterMyUploads swipeAdapterMyUploads;
    SwipeAdapterMyFavorites swipeAdapterMyFavorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager2=(ViewPager) findViewById(R.id.view_pager2);
        swipeAdapterMyUploads = new SwipeAdapterMyUploads(this);
        swipeAdapterMyFavorites= new SwipeAdapterMyFavorites(this);
        viewPager.setAdapter(swipeAdapterMyUploads);
        viewPager2.setAdapter(swipeAdapterMyFavorites);


    }
}
