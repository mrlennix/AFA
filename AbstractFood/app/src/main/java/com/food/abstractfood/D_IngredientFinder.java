package com.food.abstractfood;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;


public class D_IngredientFinder extends Activity implements OnClickListener
{

    Spinner fromList;
    ListView toList;
    ArrayAdapter<String> outdataAdapter;
    ArrayList<String> ingredientitems;
    ArrayAdapter<String> indataAdapter;
    ArrayList<String> ingredientsselected;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientfinder);


        ingredientitems = getIntent().getStringArrayListExtra("ingredientitems");


        fromList = (Spinner)findViewById(R.id.fromList);

        indataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ingredientitems);

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

                }


            // More buttons go here (if any) ...

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }




}


