package com.food.abstractfood;


import android.graphics.Bitmap;

import java.io.Serializable;

public class Food implements Serializable
{
    private long ID;
    private String name;
    private String[] ingredientscontained;
    private String category;
    private String description;
    private String Date;
    private String username;
    //private Recipe recipe;
    //private Bitmap image;
    //still missing stuff... image perhaps?


    public Food (){}


    public long getID()
    {
        return ID;
    }

    public void setID(long ID)
    {
        this.ID = ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getIngredientscontained()
    {
        return ingredientscontained;
    }

    public void setIngredientscontained(String[] ingredientscontained)
    {
        this.ingredientscontained = ingredientscontained;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDate()
    {
        return Date;
    }

    public void setDate(String date)
    {
        Date = date;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
/*
    public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
*/
    @Override
    public String toString()
    {
        return name;
    }
}
