package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//Home Page GUI
//This class controlls all GUI for the Home Page
//please implement the functionality in the HomeController
public class HomeActivity extends AppCompatActivity {
    //i'm alex
    private HomeController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        controller = new HomeController();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.menu_food:food_page();return true;
            case R.id.menu_login:;return true;
            case R.id.menu_profile:;return true;
            case R.id.menu_recipe:;return true;
            default: return false;
        }
    }

    private void food_page()
    {
        Intent next = new Intent(this,FoodActivity.class);

        startActivity(next);

    }
}
