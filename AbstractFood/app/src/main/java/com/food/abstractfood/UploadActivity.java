package com.food.abstractfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class UploadActivity extends AppCompatActivity {

    private EditText foodname, description;
    private ImageButton imgbutton;
    private Spinner spinner;
    private final int stupid = 1;
    private Uri image;
    private DBmanager database;
    private Bitmap imageBitmap = null;
    ArrayAdapter<String> indataAdapter;
    private User user;
    private Food food = new Food();
    private String[] categoryList;
    private String selectedCategory;
    private List<FoodImage> foods = new ArrayList<>();
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Upload");
        foodname = (EditText) findViewById(R.id.editText12);
        description = (EditText) findViewById(R.id.description_edit_text);
        imgbutton = (ImageButton) findViewById(R.id.upload_image);
        spinner = (Spinner) findViewById(R.id.spinner);
        database = new DBmanager();
        user = (User) getIntent().getSerializableExtra("user");
        user.decodeBase64();
        categoryList = new String[]{"Breakfast Food", "Dessert", "Appetizers", "Soup", "Sea Food", "Other", "Vegetarian", "Vegan"};
        Arrays.sort(categoryList);
        description.setGravity(Gravity.TOP);

        indataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);
        indataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(indataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedCategory = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == stupid)
        {
            image = data.getData();

            try
            {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                imageBitmap.createScaledBitmap(imageBitmap,100,50,true);
            } catch (IOException e) {
            }

            imgbutton.setImageURI(image);
            FoodImage temp = new FoodImage();
            temp.setImage(imageBitmap);
            temp.encodeToBase64();
            foods.add(temp);

        }
    }

    public void cancel (View v)
    {
        finish();
    }

    public void getImage(View v)
    {
        Intent next = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(next, stupid);
    }

    public void ingred_adder(View v)
    {
        int x = new Random().nextInt();
        if(x<0)x=-1*x;
        Intent next = new Intent(this, IngredientAdderActivity.class);
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        food.setName(foodname.getText().toString());
        food.setDescription(description.getText().toString());
        food.setID(x);
        food.setUsername(user.getUsername());
        food.setCategory(selectedCategory);
        food.setDate(DateFormat.format(date));
        food.setIngredientscontained(null);
        database.getDatabase().child("image").child(food.getName()).setValue(foods);
        next.putExtra("food", food);
        startActivity(next);
        finish();

    }

}
