package com.food.abstractfood;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FoodActivity extends AppCompatActivity {
    ViewPager viewPagerFood;
    SwipeAdapterFoodPage swipeAdapterFoodPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        viewPagerFood=(ViewPager) findViewById(R.id.food_page_view_pager);
        swipeAdapterFoodPage=new SwipeAdapterFoodPage(this);
        viewPagerFood.setAdapter(swipeAdapterFoodPage);

    }
}
