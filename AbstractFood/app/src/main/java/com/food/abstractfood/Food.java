package com.food.abstractfood;


public class Food
{
    private long ID;
    private String name;
    private String[] ingredientscontained;
    private String category;
    private String description;
    //still missing stuff... image perhaps?


    public Food()
    {
        ID=0;
        name="Default_Name";
        ingredientscontained = new String[400];
        category = "Default_Category";
        description ="The food description goes here";
    }

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
}
