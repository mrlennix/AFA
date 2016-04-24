package com.food.abstractfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Home extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageButton discoverbutton = (ImageButton) findViewById(R.id.discoverbutton);
        discoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DiscoverActivity.class);




            }
        });


        ImageButton uploadbutton = (ImageButton) findViewById(R.id.uploadbutton);
        uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vws) {

                Intent intent = new Intent(vws.getContext(), UploadActivity.class);

            }
        });
    }

}


