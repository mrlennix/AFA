package com.food.abstractfood;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.Firebase;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*This is the discover activity. where browse and search will
be implemented. It includes features such as the tabbed layout and
ability to redirect the user from a few buttons
 */
public class DiscoverActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ArrayList <Food> allfoods;
    private ArrayList<String> ingredientitems;
    private User user;
    private ArrayList<String> foodtobesent;
    private DBmanager database;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoActionBarTheme); //removing the default action bar
        setContentView(R.layout.activity_discover); // setting up layout
        Firebase.setAndroidContext(this);
        user = (User)getIntent().getSerializableExtra("user");
        database = new DBmanager();
        foodtobesent = database.getFoodNames();
        allfoods = database.getFood();
        try {
            this.loadIngredients();
        } catch (IOException e) {
            e.printStackTrace();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar); //tool bar for tabs
        setSupportActionBar(toolbar); // this will be our new actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        viewpager = (ViewPager) findViewById(R.id.viewpager); // view pager for new fragments
        setupViewPager(viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout); //tabs
        tabLayout.setupWithViewPager(viewpager);


    }

    private void setupViewPager(ViewPager viewpager)
    {
        D_TabLayoutAdapter adapter = new D_TabLayoutAdapter(getSupportFragmentManager()); //adapter for tabs
        adapter.addSFragment(new D_SearchTab(), "Search");
        adapter.addDFragment(new D_BrowseTab(), "Browse");
        viewpager.setAdapter(adapter);
    }

    /*This is the Search by name button that is going to redirect to the search by name activity
    based on the method that was referenced in the image button in the XML file.
     */
   public void redirectSBN(View v)
   {
       Intent searchbyname = new Intent(this, D_SearchBN.class);
       searchbyname.putExtra("user",user);
       searchbyname.putStringArrayListExtra("foodnames",foodtobesent);
       startActivity(searchbyname);


   }

    public void redirectIF(View v)
    {

        //ingredientitems = getIntent().getStringArrayListExtra("ingredientitems");
        Intent ingredientfinder = new Intent(this, D_IngredientFinder.class);
        ingredientfinder.putStringArrayListExtra("ingredientitems",ingredientitems);
        startActivity(ingredientfinder);

    }

    public void loadIngredients() throws IOException
    {
        int i = 0;


        AssetManager am = getBaseContext().getAssets();
        InputStream ingredienttxt = am.open("Ingredients.txt");
        String tempingredient = "";
        ingredientitems = new ArrayList<>();

        Scanner lineinput = new Scanner(ingredienttxt);

        while (lineinput.hasNextLine())
        {

            tempingredient = lineinput.nextLine();
            ingredientitems.add(i,tempingredient);
            i++;

        }


    }
    public void redirectRandomize(View v){
        int min = 0;
        int max = allfoods.size()-1;
        int randomindex;
        random = new Random();
        randomindex= random.nextInt(max - min + 1) + min;
        Intent random = new Intent(this , FoodActivity.class);
        random.putExtra("selectedfood", allfoods.get(randomindex));
        startActivity(random);







    }

}

