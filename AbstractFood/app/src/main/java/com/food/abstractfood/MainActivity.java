package com.food.abstractfood;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

//MainActivity GUI
//This class controls all GUI for the Home Page


public class MainActivity extends AppCompatActivity {

    private LoginController controller;
    private User user= new User();
    private ArrayList<String> ingredientitems;
    private Intent ingredientintent;
    private DBmanager database;
    private Button button;
    private EditText username,pass;
    //private List<Food> foods;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // set the app to start at the login
        try {
            loadIngredients();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        Firebase.setAndroidContext(this);
        database = new DBmanager();
        Food food = new Food();
        food.setName("AlexTest");
        food.setDate("1/1/1/1");
        food.setCategory("sex");
        food.setID(1111114);

        database.putFood(food);
        foods =database.getFood();
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        controller = new LoginController();
        username = (EditText)findViewById(R.id.editText);
        pass = (EditText)findViewById(R.id.editText2);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.menu_food:food_page();return true;
            case R.id.menu_profile:profile_page();return true;
            case R.id.menu_discover:discover_page();return true;
            case R.id.menu_signup:signup_page();return true;
            case R.id.menu_update:update_page();return true;
            case R.id.menu_report:report_page();return true;
            case R.id.menu_add_recipe:add_res_page();return true;
            case R.id.menu_home:home_page();return true;
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
        Intent next = new Intent(this, DiscoverActivity.class);
        next.putStringArrayListExtra("ingredientitems", ingredientitems);
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



    private void update_page()
    {
        Intent next = new Intent(this,UploadActivity.class);

        startActivity(next);

    }

    private void report_page()
    {
        Intent next = new Intent(this,ReportActivity.class);

        startActivity(next);

    }
    private void add_res_page()
    {
        Intent next = new Intent(this,AddRecipeActivity.class);

        startActivity(next);

    }

    private void home_page()
    {
        Intent next = new Intent(this,Home.class);

        startActivity(next);

    }

    public void loadIngredients() throws IOException
    {
        int i = 0;


        AssetManager am = getBaseContext().getAssets();
        InputStream ingredienttxt = am.open("Ingredients.txt");
        String tempingredient = "";
        ingredientitems = new ArrayList<>();

        Scanner lineinput = new Scanner(ingredienttxt);

        while (lineinput.hasNextLine())
        {

            tempingredient = lineinput.nextLine();
            ingredientitems.add(i,tempingredient);
            i++;

        }


    }

    public void signin(View v)
    {

        user.setUsername(username.getText().toString());
        user = database.getUser(user);
        System.out.println(user);
        if(pass.getText().toString().equals(user.getPassword()))home_page();


    }
}


