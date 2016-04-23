package com.food.abstractfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignupActivity extends AppCompatActivity {
    EditText passwordF;
    EditText verifyPasswordF;
    private Button cancel;
    private Button signup;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        cancel= (Button)findViewById(R.id.cancel_signup);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signup=(Button)findViewById(R.id.signup);




    }



}
