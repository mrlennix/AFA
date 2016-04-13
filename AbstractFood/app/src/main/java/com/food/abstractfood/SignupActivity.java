package com.food.abstractfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    EditText passwordF;
    EditText verifyPasswordF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        passwordF=(EditText) findViewById(R.id.passwordtxt);

        passwordF.setText("Password", TextView.BufferType.EDITABLE);
    }


}
