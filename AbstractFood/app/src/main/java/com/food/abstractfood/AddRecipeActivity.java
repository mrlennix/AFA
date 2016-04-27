package com.food.abstractfood;


import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class AddRecipeActivity extends FragmentActivity {

    private AddRecipeController controller;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        controller = new AddRecipeController();
        super.onCreate(savedInstanceState);

        //setTheme(R.style.AddRtheme);
        setContentView(R.layout.activity_add_recipe);
        viewpager = (ViewPager) findViewById(R.id.pager);
        SwiperAdapter padapter = new SwiperAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);

    }
}
