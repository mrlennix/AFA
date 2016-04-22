package com.food.abstractfood;

import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manny on 4/19/16.
 */
public class IngredientAdderController

{
    protected List<EditText> editTexts;

    public IngredientAdderController()
    {
        editTexts = new ArrayList<>();

    }


    public void add(LinearLayout ingredients, EditText field)
    {

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        field.setHint(R.string.ingre);
        field.setInputType(1);
        field.setLayoutParams(lparams);
        ingredients.addView(field);
        editTexts.add(field);

    }

    public void remove(LinearLayout ingredients)
    {
        if(editTexts.isEmpty())return;
        ingredients.removeViewAt(ingredients.getChildCount()-1);
        editTexts.remove(ingredients.getChildCount()-1);
    }
}
