package com.food.abstractfood;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText editText;

    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searchbyname);

        listView=(ListView)findViewById(R.id.Flistview);

        editText=(EditText)findViewById(R.id.txtNsearch);

        initList();

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().equals(""))
                {
                    // reset listview
                    initList();
                }

                else
                {
                    // perform search
                    searchItem(s.toString());

                }

            }
            @Override
            public void afterTextChanged(Editable s)
            {



            }

        });

    }

    public void searchItem(String textToSearch)
    {

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

        items=new String[]{"Spaghetti","Brocolli","Cheese and Egg","Vomit"};

        listItems=new ArrayList<>(Arrays.asList(items));

        adapter=new ArrayAdapter<String>(this, R.layout.food_item, R.id.fooditem, listItems);

        listView.setAdapter(adapter);

    }

}