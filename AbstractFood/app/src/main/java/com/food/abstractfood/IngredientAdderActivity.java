package com.food.abstractfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class IngredientAdderActivity extends AppCompatActivity
{
    private LinearLayout ingredients;
    private IngredientAdderController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_adder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ingredients=(LinearLayout)findViewById(R.id.layout_ingredient_plus_minus);
        controller = new IngredientAdderController();
    }

    public void add(View v)
    {
        controller.add(ingredients,new EditText(this));
    }

    public void subtract(View v)
    {
        controller.remove(ingredients);
    }

}
