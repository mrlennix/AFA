package com.food.abstractfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class UploadActivity extends AppCompatActivity {
    private UploadController controller;
    private EditText foodname,description;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        controller = new UploadController();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        foodname =(EditText)findViewById(R.id.foodname_textview);
        description = (EditText)findViewById(R.id.description_edit_text);
        spinner = (Spinner)findViewById(R.id.spinner);

    }
}
