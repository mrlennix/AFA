package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {
    private ImageView logo;
    private DBmanager db = new DBmanager();
    private User user;
    private Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        food = (Food) getIntent().getSerializableExtra("food");
        setContentView(R.layout.activity_loading);
        super.onCreate(savedInstanceState);
        logo = (ImageView)findViewById(R.id.logo_splat);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.loading);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            Intent next = new Intent(getApplicationContext(),Home.class);
                next.putExtra("food",food);
                startActivity(next);
            }
        });
        logo.startAnimation(anim);


    }
}
