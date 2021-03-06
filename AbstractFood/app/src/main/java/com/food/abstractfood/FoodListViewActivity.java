package com.food.abstractfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by John Doe on 4/22/2016.
 */
public class FoodListViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlistviewz);
        populateListview();
        Button cancelif = (Button) findViewById(R.id.cancelif);
        cancelif.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
    }

    private void populateListview() {

        String[] myItems = {"step 1", "step 2", "step 3", "step 4", "step 5", "step 6", "step 7", "step 8", "step 9", "step 10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.da_item, myItems);
        ListView list = (ListView) findViewById(R.id.foodresults);
        list.setAdapter(adapter);
    }

}