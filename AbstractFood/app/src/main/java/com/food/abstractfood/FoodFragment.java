package com.food.abstractfood;

import  android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FoodFragment extends Fragment {
    private TextView steps;
    private EditText ingredientdescription;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipefragment,container,false);
        steps = (TextView)view.findViewById(R.id.stepchanger);
        ingredientdescription = (EditText) view.findViewById(R.id.ingredientdescriptionedit);
        ingredientdescription.setGravity(Gravity.TOP);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        steps.setText("Step: "+message);
        return view;
    }


}
