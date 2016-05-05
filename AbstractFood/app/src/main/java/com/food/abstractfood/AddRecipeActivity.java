package com.food.abstractfood;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class AddRecipeActivity extends FragmentActivity {

    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        viewpager = (ViewPager) findViewById(R.id.pager);
        SwiperAdapter padapter = new SwiperAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);

    }
}
