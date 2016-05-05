package com.food.abstractfood;

import java.util.ArrayList;
import java.util.List;


public class Report
{
    private Recipe recipeReported;
    private List<User> usersWhoreported;


    public Report()
    {
        setUsersWhoreported(new ArrayList<User>());
    }

    public Recipe getRecipeReported()
    {
        return recipeReported;
    }

    public void setRecipeReported(Recipe recipeReported)
    {
        this.recipeReported = recipeReported;
    }

    public List<User> getUsersWhoreported()
    {
        return usersWhoreported;
    }

    public void setUsersWhoreported(List<User> usersWhoreported)
    {
        this.usersWhoreported = usersWhoreported;
    }
}
