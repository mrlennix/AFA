package com.food.abstractfood;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotUsernamePassword extends AppCompatActivity {
    private Button send;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_username_password);
        email=(EditText)findViewById(R.id.enterEmail);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Forgot Username/Password");
        send=(Button)findViewById(R.id.sendEmail);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(),"Please enter your email address",Toast.LENGTH_LONG).show();
                }
                else{
                    finish();
                }

            }
        });
    }
}
