package com.food.abstractfood;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    private Button done;
    private EditText pass;
    private EditText verifyP;
    private User user;
    private DBmanager database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = new DBmanager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        user = (User) getIntent().getSerializableExtra("user");
        database.getUser(user);
        //To fix the font of "hint"
         pass = (EditText) findViewById(R.id.passwordChangePS);
        verifyP = (EditText) findViewById(R.id.verifyPasswordChangePs);
        done = (Button) findViewById(R.id.done_changePassword);
        pass.setTypeface(Typeface.DEFAULT);
        verifyP.setTypeface(Typeface.DEFAULT);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Must contain: 8 letters, one uppercase and a number", Toast.LENGTH_LONG).show();
            }
        });







        done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //check if text field is empty(user didn't enter anything)
                            if(verifyP.getText().toString().equals("")||pass.getText().toString().equals("")){
                                Toast.makeText(getBaseContext(),"Please enter your new password",Toast.LENGTH_LONG).show();
                            }
                            else {

                                if (!(verifyP.getText().toString().equals(pass.getText().toString()))) {
                                    Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getBaseContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                                    user.setPassword(verifyP.getText().toString());
                                    database.putUser(user);
                                    finish();
                                }
                            }

                    }
        });
    }
}



