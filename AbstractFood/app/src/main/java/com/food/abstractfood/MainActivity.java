package com.food.abstractfood;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

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
    private TextView forgotUsername;
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

        //To fix the font of "hint"
        EditText passwordField=(EditText)findViewById(R.id.passwordLogin);
        passwordField.setTypeface(Typeface.DEFAULT);

        forgotUsername=(TextView)findViewById(R.id.forgotUsrNameOrPs);
        forgotUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotUN=new Intent(v.getContext(),ForgotUsernamePassword.class);
                startActivityForResult(forgotUN,0);
            }
        });

        TextView newUser=(TextView)findViewById(R.id.newuser);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup =new Intent(v.getContext(),SignupActivity.class);
                startActivityForResult(signup,0);
            }
        });




        Firebase.setAndroidContext(this);
        database = new DBmanager();
        Food food = new Food();
        food.setName("Pancakes");
        food.setDate("04/26/2016");
        food.setCategory("Breakfast Food");
        food.setID(0000001);
        food.setDescription("Delicious hotcakes made to perfection.");
        String[] tempingri = {"Eggs","Milk","Butter"};
        food.setIngredientscontained(tempingri);
        food.setUsername("ghh");

        database.putFood(food);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        user = new User();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        controller = new LoginController();
        username = (EditText)findViewById(R.id.editText);
        pass = (EditText)findViewById(R.id.editText2);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setUsername(s.toString());
                System.out.println(user);
                Toast.makeText(getBaseContext(),s.toString(),Toast.LENGTH_SHORT).show();
                user = database.getUser(user);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        if(user==null)return;
        System.out.println(user);
        if(pass.getText().toString().equals(user.getPassword()))home_page();

    }
}


