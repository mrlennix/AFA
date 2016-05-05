package com.food.abstractfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

//MainActivity GUI
//This class controls all GUI for the Home Page


public class MainActivity extends AppCompatActivity {

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
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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

        //To fix the font of "hint"
        EditText passwordField=(EditText)findViewById(R.id.passwordLogin);
        passwordField.setTypeface(Typeface.DEFAULT);

        forgotUsername=(TextView)findViewById(R.id.forgotUsrNameOrPs);
        TextView newUser=(TextView)findViewById(R.id.newuser);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                R.drawable.pancake);
        FoodImage pan = new FoodImage();
        pan.setImage(bitmap);
        Bitmap burg = BitmapFactory.decodeResource(getResources(),R.drawable.burgerimage);
        FoodImage bu = new FoodImage();
        bu.setImage(burg);
        bu.encodeToBase64();
        pan.encodeToBase64();
        List<FoodImage> b = new ArrayList<>();
        b.add(pan);
        database.getDatabase().child("image").child("Pancakes").setValue(b);


    }


    private void signup_page()
    {
        Intent next = new Intent(this,SignupActivity.class);

        startActivity(next);

    }

    private void home_page()
    {
        Intent next = new Intent(this,Home.class);
        user.encodeToBase64();
        next.putExtra("user",user);
        startActivity(next);

    }

    public void forgotname(View v)
    {
        Intent forgotUN = new Intent(this,ForgotUsernamePassword.class);
        startActivity(forgotUN);

    }

    public void signup(View v)
    {
        Intent signup =new Intent(this,SignupActivity.class);
        startActivityForResult(signup, 0);
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


