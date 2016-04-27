package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class FoodActivity extends AppCompatActivity {
    private DBmanager database;
    private Food food;
    private User user;
    ViewPager viewPagerFood;
    SwipeAdapterFoodPage swipeAdapterFoodPage;
    private TextView username,dateposted,likes,dislikes;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        food = (Food)getIntent().getSerializableExtra("selectedfood");
        Firebase.setAndroidContext(this);
        database = new DBmanager();
        food = database.getOneFood(food);
        user = new User();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);



        username = (TextView)findViewById(R.id.textView10);
        dateposted=(TextView)findViewById(R.id.textView11);
        likes = (TextView)findViewById(R.id.textView12) ;
        dislikes = (TextView)findViewById(R.id.textView13);
        image = (ImageView)findViewById(R.id.imageView3);



        viewPagerFood = (ViewPager) findViewById(R.id.food_page_view_pager);
        swipeAdapterFoodPage = new SwipeAdapterFoodPage(this);
        viewPagerFood.setAdapter(swipeAdapterFoodPage);


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

        dateposted.setText(food.getDate());
        if(user.getImage()!=null)image.setImageBitmap(user.getImage());

    }






}