package com.food.abstractfood;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class FoodActivity extends AppCompatActivity {

    private Food food;
    private User user;
    ViewPager viewPagerFood;
    SwipeAdapterFoodPage swipeAdapterFoodPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        user = (User)getIntent().getSerializableExtra("user");

        viewPagerFood = (ViewPager) findViewById(R.id.food_page_view_pager);
        swipeAdapterFoodPage = new SwipeAdapterFoodPage(this);
        viewPagerFood.setAdapter(swipeAdapterFoodPage);
        Intent intention= getIntent();

        Food food = (Food)intention.getSerializableExtra("selectedfood");
        Toast.makeText(getBaseContext(),food.getDate(),Toast.LENGTH_LONG);
        Button listViewBtn = (Button) findViewById(R.id.listViewBtn);

        Toast.makeText(getBaseContext(),food.getDate(),Toast.LENGTH_SHORT).show();
        listViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {

                Intent intent = new Intent(vw.getContext(), FoodListViewActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        Button reportBtn = (Button) findViewById(R.id.reportBtn);
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vws) {

                Intent intent = new Intent(vws.getContext(), ReportActivity.class);
                startActivityForResult(intent, 0);
            }
        });



    }






}