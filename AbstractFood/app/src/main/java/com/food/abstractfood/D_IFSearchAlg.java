package com.food.abstractfood;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class D_IFSearchAlg extends AppCompatActivity
{

    private ArrayList<Food> InDBFood;
    private ArrayList<String> Results;
    private ArrayAdapter<String>resultadapter;
    private String [] foodingredients;
    private ArrayList<String> ingredientsrecievedA;
    private String[] ingredientsrecieved;
    private Boolean foundresult;
    private ListView results;
    private Button button;
    private Food foodselected;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlistviewz);

        results = (ListView)findViewById(R.id.foodresults);
        Results = new ArrayList<>();
        foundresult = true;
        button = (Button)findViewById(R.id.cancelif);

        Bundle bundle = getIntent().getExtras();
        InDBFood = (ArrayList<Food>)bundle.getSerializable("food");
        foodselected = new Food();

        ingredientsrecievedA = getIntent().getStringArrayListExtra("selected");
        ingredientsrecieved = new String[ingredientsrecievedA.size()];
        ingredientsrecieved = ingredientsrecievedA.toArray(ingredientsrecieved);

        button.setOnClickListener(new View.OnClickListener()
                                  {@Override
                                      public void onClick(View v)
                                      {
                                          finish();

                                      }
                                  }
        );

        if(populateResults() > 0)
        {

            resultadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Results);

            results.setAdapter(resultadapter);

        }
        else
        {
            String[] noresult ={"No Results Found."};
            resultadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,noresult);
            results.setAdapter(resultadapter);
        }
        results.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String foodname  = (String) (parent.getItemAtPosition(position));
                foodselected.setName(foodname);
                Intent foodactivity = new Intent(view.getContext(),FoodActivity.class);

                foodactivity.putExtra("selectedfood",foodselected);

                startActivity(foodactivity);


                //this will send it the food activity!!!


            }
        });

    }

    public int populateResults()
    {
        int count = 0;
        for(int i=0;i< InDBFood.size();i++)
        {
                foodingredients = InDBFood.get(i).getIngredientscontained();

            for(int j=0; j< ingredientsrecieved.length;j++)
            {
                if(!Arrays.asList(foodingredients).contains(ingredientsrecieved[j]))
                {
                    foundresult = false;
                    break;
                }

            }

            if(foundresult)
            {

                Results.add(InDBFood.get(i).getName());
                count++;

            }
            else
            {

                foundresult = true;
            }


        }
        return count;
    }
    public void goback()
    {
       finish();


    }

}
