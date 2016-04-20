package com.food.abstractfood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Manny on 4/16/16.
 */
public class ReportActivity extends AppCompatActivity
{
    private ReportController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ReportController();
        setContentView(R.layout.activity_report);

    }
}
