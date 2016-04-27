package com.food.abstractfood;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        Firebase.setAndroidContext(this);
        database = new DBmanager();

        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        user = new User();

        controller = new LoginController();

        username = (EditText)findViewById(R.id.editText);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))return;
                user.setUsername(s.toString());
                user = database.getUser(user);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pass = (EditText)findViewById(R.id.passwordLogin);
        //Don't need these at login
         // set the app to start at the login
        try {
            loadIngredients();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //To fix the font of "hint"
        EditText passwordField=(EditText)findViewById(R.id.passwordLogin);
        passwordField.setTypeface(Typeface.DEFAULT);

        forgotUsername=(TextView)findViewById(R.id.forgotUsrNameOrPs);


        TextView newUser=(TextView)findViewById(R.id.newuser);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

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
            case R.id.menu_listview:listview_page();return true;
            case R.id.splat:splat();return true;
            default: return false;

        }
    }
    private void splat()
    {
        Intent next = new Intent(this,LoadingActivity.class);

        startActivity(next);
    }
    private void signup_page()
    {
        Intent next = new Intent(this,SignupActivity.class);

        startActivity(next);

    }
    private void listview_page()
    {
        Intent next = new Intent(this,FoodListViewActivity.class);

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
        user.encodeToBase64();
        next.putExtra("object",user);
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
        user.encodeToBase64();
        next.putExtra("user",user);
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
    public void forgotname(View v)
    {
        Intent forgotUN = new Intent(this,ForgotUsernamePassword.class);
        startActivity(forgotUN);

    }

    public void signup(View v)
    {
        Intent signup =new Intent(this,SignupActivity.class);
        startActivityForResult(signup,0);
    }

    public void signin(View v)
    {
        if(user.getPassword()==null)
        {
            Toast.makeText(getBaseContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            return;
        }
        System.err.println(user.getPassword());
        if(pass.getText().toString().equals(user.getPassword().toString()))
            home_page();
        else
        {
            Toast.makeText(getBaseContext(),"Invalid Username of Password",Toast.LENGTH_LONG).show();
        }

    }
}


