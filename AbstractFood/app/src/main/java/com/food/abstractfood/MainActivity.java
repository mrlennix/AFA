package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//Home Page GUI
//This class controls all GUI for the Home Page


public class MainActivity extends AppCompatActivity {
   
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // set the app to start at the login



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        controller = new MainController();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.menu_food:food_page();return true;
            case R.id.menu_login:login_page();return true;
            case R.id.menu_profile:profile_page();return true;
            case R.id.menu_discover:discover_page();return true;
            case R.id.menu_signup:signup_page();return true;
            case R.id.menu_update:update_page();return true;
            default: return false;
        }
    }

    private void signup_page()
    {
        Intent next = new Intent(this,SignupActivity.class);

        startActivity(next);

    }
    private void discover_page()
    {
        Intent next = new Intent(this,DiscoverActivity.class);

        startActivity(next);
    }

    private void profile_page()
    {
        Intent next = new Intent(this,ProfileActivity.class);

        startActivity(next);
    }

    private void food_page()
    {
        Intent next = new Intent(this,FoodActivity.class);

        startActivity(next);

    }

    private void login_page()
    {
        Intent next = new Intent(this,LoginActivity.class);

        startActivity(next);
    }

    private void update_page()
    {
        Intent next = new Intent(this,UpdateActivity.class);

        startActivity(next);

    }

}
