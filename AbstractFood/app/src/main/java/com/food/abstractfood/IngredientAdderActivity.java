package com.food.abstractfood;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IngredientAdderActivity extends Activity implements View.OnClickListener
{
    private LinearLayout ingredients;
    private IngredientAdderController controller;
    private List<String>ingred;
    private Button but;
    Spinner fromList;
    ListView toList;
    ArrayAdapter<String> outdataAdapter;
    ArrayList<String> ingredientitems;
    ArrayAdapter<String> indataAdapter;
    ArrayList<String> ingredientsselected = new ArrayList<>();
    String[] ingredientsList;
    private Food food;
    private DBmanager db = new DBmanager();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        try {
            loadIngredients();
        } catch (IOException e) {

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientfinder);
        but = (Button)findViewById(R.id.igsearchbutton);
        but.setText("DONE");

        //ingredients=(LinearLayout)findViewById(R.id.layout_ingredient_plus_minus);


        food=(Food)getIntent().getSerializableExtra("food");
        food.decodeBase64();

        fromList = (Spinner)findViewById(R.id.fromList);

        indataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ingred);

        indataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromList.setAdapter(indataAdapter);

        toList = (ListView) findViewById(R.id.Ilistview);
        outdataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        outdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toList.setAdapter(outdataAdapter);

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
        Button remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(this);
        Button search = (Button) findViewById(R.id.igsearchbutton);
        search.setOnClickListener(this);
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

    public void loadIngredients() throws IOException
    {
        int i = 0;


        AssetManager am = getBaseContext().getAssets();
        InputStream ingredienttxt = am.open("Ingredients.txt");
        String tempingredient = "";
        ingred = new ArrayList<>();

        Scanner lineinput = new Scanner(ingredienttxt);

        while (lineinput.hasNextLine())
        {

            tempingredient = lineinput.nextLine();
            ingred.add(i, tempingredient);
            i++;

        }
    }

    public void onClick(View v)
    {

        if (fromList.getSelectedItem() == null)
        {
            return;
        }
        String myData = fromList.getSelectedItem().toString();
        int position = outdataAdapter.getPosition(myData);

        switch (v.getId()) {

            case R.id.add:

                if(position >= 0)
                {
                    Toast.makeText(getBaseContext(), myData + " already selected.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    outdataAdapter.add(myData);
                    outdataAdapter.notifyDataSetChanged();
                    toList.setSelection(outdataAdapter.getPosition(myData));
                }
                break;

            case R.id.remove:

                if(position >= 0)
                {
                    outdataAdapter.remove(myData);
                    outdataAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getBaseContext(), myData + " not selected.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.igsearchbutton:
                if( outdataAdapter.isEmpty())
                {

                    Toast.makeText(getBaseContext(),"Please select at least one ingredient",Toast.LENGTH_LONG);


                }
                else
                {
                    for( int i = 0 ; i< outdataAdapter.getCount(); i++ )
                    {
                        ingredientsselected.add(outdataAdapter.getItem(i).toString());





                    }
                    ingredientsList=new String[ingredientsselected.size()];
                    ingredientsList=ingredientsselected.toArray(ingredientsList);
                    food.setIngredientscontained(ingredientsList);
                    Intent next = new Intent(this,AddRecipeActivity.class);
                    food.encodeToBase64();

                    db.putFood(food);
                    next.putExtra("food",food);
                    startActivity(next);
                }


                // More buttons go here (if any) ...

        }



    }


}
