package com.food.abstractfood;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotUsernamePassword extends AppCompatActivity {
    private Button send;
    private EditText email;
    private User user;
    private DBmanager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = new DBmanager();
        super.onCreate(savedInstanceState);
        user = new User();
        setContentView(R.layout.activity_forgot_username_password);
        email=(EditText)findViewById(R.id.enterUsername);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))return;
                user.setUsername(s.toString());
                database.getUser(user);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Forgot Username/Password");
        send=(Button)findViewById(R.id.sendEmail);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(),"Please enter your user name",Toast.LENGTH_LONG).show();
                }
                else{

                    finish();
                }

            }
        });
    }
}
