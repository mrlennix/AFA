package com.food.abstractfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Lacherois on 4/21/2016.
 */
public class ReportActivity extends AppCompatActivity {

EditText reportcomments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        reportcomments = (EditText)findViewById(R.id.txtoutput);
        reportcomments.setGravity(Gravity.TOP);


        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        Button submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Your report was sent to the admin for further consideration", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}