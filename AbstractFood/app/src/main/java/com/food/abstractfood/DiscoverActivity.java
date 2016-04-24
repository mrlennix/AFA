package com.food.abstractfood;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.ArrayList;
/*This is the discover activity. where browse and search will
be implemented. It includes features such as the tabbed layout and
ability to redirect the user from a few buttons
 */
public class DiscoverActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ArrayList<String> ingredientitems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoActionBarTheme); //removing the default action bar
        setContentView(R.layout.activity_discover); // setting up layout


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
       startActivity(searchbyname);


   }

    public void redirectIF(View v)
    {

        ingredientitems = getIntent().getStringArrayListExtra("ingredientitems");
        Intent ingredientfinder = new Intent(this, D_IngredientFinder.class);
        ingredientfinder.putStringArrayListExtra("ingredientitems",ingredientitems);
        startActivity(ingredientfinder);

    }


}

