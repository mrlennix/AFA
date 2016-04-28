package com.food.abstractfood;

import android.content.Intent;
import  android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodFragment extends Fragment {
    private TextView steps;
    private EditText ingredientdescription;
    private String currentStepText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.recipefragment, container, false);
        steps = (TextView)view.findViewById(R.id.stepchanger);
        ingredientdescription = (EditText) view.findViewById(R.id.ingredientdescriptionedit);

        ingredientdescription.setGravity(Gravity.TOP);

        final Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        steps.setText("Step: "+message);


        ingredientdescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentStepText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        final Button finish=(Button)view.findViewById(R.id.add_finish_bt);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Food Uploaded Successfully",Toast.LENGTH_LONG).show();
                getActivity().finish();

            }
        });

        Button back=(Button)view.findViewById(R.id.add_cancel_bt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }


    public String getCurrentStepText() {
        return currentStepText;
    }

    public void setCurrentStepText(String currentStepText) {
        this.currentStepText = currentStepText;
    }

}
