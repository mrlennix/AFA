package com.food.abstractfood;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class SignupActivity extends AppCompatActivity {
    private Button cancel;
    private Button signup;
    EditText pass;
    EditText verifyPass;
    EditText username;
    EditText email;
    DBmanager dBmanager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        dBmanager=new DBmanager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        verifyPass = (EditText) findViewById(R.id.verifyPasswordtxt);
        pass = (EditText) findViewById(R.id.passwordtxt);
        username=(EditText) findViewById(R.id.username_signup);
        email=(EditText) findViewById(R.id.email_signup);
        pass.setTypeface(Typeface.DEFAULT);
        verifyPass.setTypeface(Typeface.DEFAULT);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Must contain: 8 letters, one uppercase and a number", Toast.LENGTH_LONG).show();
            }
        });

        cancel = (Button) findViewById(R.id.cancel_signup);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signup = (Button) findViewById(R.id.signup);


    }
    public void stuff(View v){

        if(pass.getText().toString().equals("")||verifyPass.getText().toString().equals("")||username.getText().toString().equals("")||email.getText().toString().equals(""))
        {
            Toast.makeText(getBaseContext(),"Some fields are incomplete",Toast.LENGTH_LONG).show();
        }

        else {
            if (!(verifyPass.getText().toString().equals(pass.getText().toString()))) {
                Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
            } else {
                User user = new User();
                user.setPassword(pass.getText().toString());
                user.setUsername(username.getText().toString());
                user.setEmail(email.getText().toString());
                dBmanager.putUser(user);
                startLogin();

            }
        }
    }




    private void startLogin(){
        Intent next=new Intent(this,MainActivity.class);
        startActivity(next);

    }

}
