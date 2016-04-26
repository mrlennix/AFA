package com.food.abstractfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Home extends AppCompatActivity {
    private User user;


    protected void onCreate(Bundle savedInstanceState)
    {
        user = (User) getIntent().getSerializableExtra("user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

         public void redirectDiscover(View view)
            {
                Intent intent = new Intent(view.getContext(), DiscoverActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);





            }




            public void redirectUpload(View vws)
            {

                Intent intent = new Intent(vws.getContext(), UploadActivity.class);
                startActivity(intent);

            }

    }




