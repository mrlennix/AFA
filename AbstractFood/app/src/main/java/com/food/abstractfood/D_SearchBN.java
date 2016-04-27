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

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Arrays;


/*
   This is the search by name class, it will recieve the food names from the database
   display them to the user. Once displayed to the user, each item in the list view is
    clickable which redirect to the corresponding foodpage!

 */
public class D_SearchBN extends AppCompatActivity
{

    private String[] items;

    private ArrayList<String> listItems;
    private ArrayList<String> databaseFoods;
    private ArrayAdapter<String> adapter;
    private ArrayList<Food> data;
    private ListView listView;
    private EditText editText;
    private String foodname;
    private DBmanager foodgrabber;
    private Food foodselected;
    private  User user;


    protected void onCreate(Bundle savedInstanceState)
    {
        foodselected = new Food();



        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        foodgrabber = new DBmanager();

        databaseFoods = foodgrabber.getFoodNames();
        setContentView(R.layout.activity_searchbyname);

        listView=(ListView)findViewById(R.id.Flistview);
        editText=(EditText)findViewById(R.id.txtNsearch);



        initList();



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().equals("")) {
                    // reset listview
                    initList();

                } else {
                    // perform search
                    searchItem(s.toString());

                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                foodname  = (String) (parent.getItemAtPosition(position));
                foodselected.setName(foodname);
                Intent foodactivity = new Intent(view.getContext(),FoodActivity.class);
                foodactivity.putExtra("selectedfood",foodselected);
                foodactivity.putExtra("user",user);
                startActivity(foodactivity);



            }
        });




    }

    public void searchItem(String textToSearch)
    {
        textToSearch =textToSearch.substring(0, 1).toUpperCase() + textToSearch.substring(1);
        for(String item:items)
        {

            if(!item.contains(textToSearch))
            {
                listItems.remove(item);
            }

        }
        adapter.notifyDataSetChanged();

    }

    public void initList()
    {


        items = new String[databaseFoods.size()];

        items= databaseFoods.toArray(items);

        listItems=new ArrayList<>(Arrays.asList(items));

        adapter=new ArrayAdapter<>(this, R.layout.food_item, R.id.fooditem, listItems);

        listView.setAdapter(adapter);



    }

}