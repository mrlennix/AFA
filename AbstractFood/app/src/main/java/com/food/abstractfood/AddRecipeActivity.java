package com.food.abstractfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddRecipeActivity extends AppCompatActivity
{
    private AddRecipeController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        controller = new AddRecipeController();
        super.onCreate(savedInstanceState);
        setTheme(R.style.Discovertheme);
        setContentView(R.layout.activity_add_recipe);
    }
}
