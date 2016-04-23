package com.food.abstractfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import java.io.IOException;

public class UploadActivity extends AppCompatActivity {
    private UploadController controller;
    private EditText foodname,description;
    private ImageButton imgbutton;
    private Spinner spinner;
    private final int stupid =1;
    private Uri image;
    private DBmanager database;

    private Food food = new Food();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        controller = new UploadController();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        foodname = (EditText)findViewById(R.id.editText12) ;
        description = (EditText)findViewById(R.id.description_edit_text);
        imgbutton = (ImageButton)findViewById(R.id.upload_image);
        spinner = (Spinner)findViewById(R.id.spinner);
        database=new DBmanager();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == stupid)
        {
            image = data.getData();
            imgbutton.setImageURI(image);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cancel (View v)
    {

        finish();

    }
    public void getImage(View v)
    {
        Intent next = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(next,stupid);
    }

    public void ingred_adder(View v)
    {
        Intent next = new Intent(this,IngredientAdderActivity.class);
        Bitmap map=null;
        food.setName(foodname.getText().toString());
        food.setDescription(description.getText().toString());
        try {
             map = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);
        } catch (IOException e) {

        }
        food.setImage(map);
        food.setID(444444);
        next.putExtra("food",food);
        database.putFood(food);
        startActivity(next);

    }


}
