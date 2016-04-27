package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class FoodActivity extends AppCompatActivity {
    private DBmanager database;
    private Food food;
    private User user = new User();
    ViewPager viewPagerFood;
    SwipeAdapterFoodPage swipeAdapterFoodPage;
    private TextView username,dateposted,likes,dislikes,randomTextbox;
    private ImageView image,aniImage;
    private Button stepview,listview,report;
    private ViewPager view;
    private ImageView happyImage, sadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        food = (Food)getIntent().getSerializableExtra("selectedfood");
        Firebase.setAndroidContext(this);



        database = new DBmanager();
        food = database.getOneFood(food);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        aniImage = (ImageView)findViewById(R.id.aniImage);
        username = (TextView)findViewById(R.id.textView10);
        dateposted=(TextView)findViewById(R.id.textView11);
        likes = (TextView)findViewById(R.id.textView12) ;
        dislikes = (TextView)findViewById(R.id.textView13);
        image = (ImageView)findViewById(R.id.imageView3);
        view =(ViewPager)findViewById(R.id.food_page_view_pager);
        listview=(Button)findViewById(R.id.listViewBtn);
        report =(Button)findViewById(R.id.reportBtn);
        stepview = (Button)findViewById(R.id.button9);
        happyImage=(ImageView)findViewById(R.id.imageView4);
        sadImage = (ImageView)findViewById(R.id.imageView5);
        randomTextbox = (TextView)findViewById(R.id.textView8);

        randomTextbox.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.INVISIBLE);
        report.setVisibility(View.INVISIBLE);
        stepview.setVisibility(Button.INVISIBLE);
        view.setVisibility(View.INVISIBLE);
        username.setVisibility(TextView.INVISIBLE);
        dateposted.setVisibility(TextView.INVISIBLE);
        dateposted.setVisibility(TextView.INVISIBLE);
        image.setVisibility(ImageView.INVISIBLE);
        dislikes.setVisibility(TextView.INVISIBLE);
        happyImage=(ImageView)findViewById(R.id.imageView4);
        sadImage = (ImageView)findViewById(R.id.imageView5);
        happyImage.setVisibility(View.INVISIBLE);
        sadImage.setVisibility(View.INVISIBLE);
        likes.setVisibility(View.INVISIBLE);



        user.setUsername(food.getUsername());

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.loading);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vws) {

                        Intent intent = new Intent(vws.getContext(), ReportActivity.class);
                        startActivityForResult(intent, 0);
                    }
                });

                report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vw) {

                        Intent intent = new Intent(vw.getContext(), FoodListViewActivity.class);
                        startActivityForResult(intent, 0);
                    }
                });
                randomTextbox.setVisibility(View.VISIBLE);
                aniImage.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
                report.setVisibility(View.VISIBLE);
                stepview.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                username.setVisibility(TextView.VISIBLE);
                dateposted.setVisibility(TextView.VISIBLE);
                dateposted.setVisibility(TextView.VISIBLE);
                image.setVisibility(ImageView.VISIBLE);
                dislikes.setVisibility(TextView.VISIBLE);
                happyImage.setVisibility(View.VISIBLE);
                sadImage.setVisibility(View.VISIBLE);
                likes.setVisibility(View.VISIBLE);



                viewPagerFood = (ViewPager) findViewById(R.id.food_page_view_pager);
                swipeAdapterFoodPage = new SwipeAdapterFoodPage(getApplicationContext());
                viewPagerFood.setAdapter(swipeAdapterFoodPage);


                Toast.makeText(getBaseContext(),food.getDate(),Toast.LENGTH_LONG);
                Button listViewBtn = (Button) findViewById(R.id.listViewBtn);

                Toast.makeText(getBaseContext(),food.getDate(),Toast.LENGTH_SHORT).show();


                //if(food.getImage()!=null)viewPagerFood.addView();
                dateposted.setText(food.getDate());
                username.setText(food.getUsername());
                likes.setText(food.getLikes());
                dislikes.setText(food.getDislikes());
                if(user.getImage()!=null)image.setImageBitmap(user.getImage());

            }




            @Override
            public void onAnimationRepeat(Animation animation)
            {
                user.setUsername(food.getUsername());
              user =database.getUser(user);
            }
        });
        aniImage.startAnimation(anim);


    }






}