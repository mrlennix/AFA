package com.food.abstractfood;


//THIS CLASS STORES INFO ABOUT THE RECIPES
public class Recipe
{
    private String name;
    private String description;
    private String ingredents;
    private int reportCount;

    public Recipe(){}


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getReportCount()
    {
        return reportCount;
    }

    public void setReportCount(int reportCount)
    {
        this.reportCount = reportCount;
    }

    public String getIngredents()
    {
        return ingredents;
    }

    public void setIngredents(String ingredents)
    {
        this.ingredents = ingredents;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
