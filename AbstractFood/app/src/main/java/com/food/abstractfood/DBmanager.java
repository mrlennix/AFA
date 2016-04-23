package com.food.abstractfood;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class DBmanager
{
    private static Firebase database;
    private static final String url ="https://abstractfoods.firebaseio.com";
    private static final String foodpath = "food";
    private static final String userpath = "user";
    private User u;

    public DBmanager()
    {
        if(database==null)
        setDatabase(new Firebase(url));
    }

    public void putFood(Food food)
    {
     database.child(foodpath).child(String.valueOf(food.getID())).setValue(food);
    }

    public void putUser(User user)
    {
        database.child(userpath).child(user.getUsername()).setValue(user);
    }

    public User getUser(User user)
    {

        u = user;

        database.child(userpath).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                User temp = dataSnapshot.child(u.getUsername()).getValue(User.class);
                if(temp!=null)
                {
                    u.setUsername(temp.getUsername());
                    u.setPassword(temp.getPassword());
                    u.setEmail(temp.getEmail());
                    u.setSanswer(temp.getSanswer());
                    u.setSquestion(temp.getSquestion());
                    u.setImage(u.getImage());
               }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError)
            {

            }
        });
        return u;
    }
    public ArrayList<Food> getFood()
    {
        final ArrayList<Food> foods = new ArrayList<>();

        database.child(foodpath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Food foo = shot.getValue(Food.class);
                    foods.add(foo);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return foods;
    }

    public Firebase getDatabase() {
        return database;
    }

    public void setDatabase(Firebase database) {
        this.database = database;
    }
}
