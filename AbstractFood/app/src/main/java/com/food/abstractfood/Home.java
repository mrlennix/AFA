package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Home extends AppCompatActivity {
    private User user= new User();
    private ArrayList<Food> myfood = new ArrayList<>();
    private DBmanager db = new DBmanager();


    protected void onCreate(Bundle savedInstanceState)
    {
        user = (User) getIntent().getSerializableExtra("user");
        user.decodeBase64();
        myfood = db.getUsersFood(user);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setCustomView(R.layout.activity_homeactionbar);
        actionBar.setDisplayShowCustomEnabled(true);
        ImageView profilePic;
        TextView username;
        username=(TextView)findViewById(R.id.username_home);
        username.setText(user.getUsername());
        profilePic=(ImageView) findViewById(R.id.home_pic);
        profilePic.setImageBitmap(user.getImage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu home_menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,home_menu);

        return super.onCreateOptionsMenu(home_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId()) {
            case R.id.home_menu_about:about_page();return true;
            case R.id.home_menu_profile:profile_page();return true;
            case R.id.home_menu_logout:logout_page();return true;
            default: return super.onOptionsItemSelected(item);
        }}

    private void about_page()
    {
        Intent next = new Intent(this,AboutActivity.class);

        startActivity(next);

    }

    private void profile_page()
    {
        Intent next = new Intent(this,ProfileActivity.class);
        user.encodeToBase64();
        next.putExtra("object",user);
        next.putExtra("food",myfood);
        startActivity(next);

        startActivity(next);

    }

    private void logout_page()
    {
        Intent next = new Intent(this,MainActivity.class);

        startActivity(next);

    }




            public void redirectDiscover(View view)
            {
                Intent intent = new Intent(view.getContext(), DiscoverActivity.class);
                user.encodeToBase64();
                intent.putExtra("user",user);
                startActivity(intent);





            }




            public void redirectUpload(View vws)
            {

                Intent intent = new Intent(vws.getContext(), UploadActivity.class);
                user.encodeToBase64();
                intent.putExtra("user",user);
                startActivity(intent);

            }

    }




