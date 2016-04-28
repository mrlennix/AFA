package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;


public class D_Randomize extends AppCompatActivity
{
    Food randomfood;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        randomfood = (Food)bundle.getSerializable("randomfood");
        Intent intent = new Intent(this , FoodActivity.class);
        intent.putExtra("selectedfood",randomfood);
        startActivity(intent);



    }
}
