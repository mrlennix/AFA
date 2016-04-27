package com.food.abstractfood;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class DBmanager {
    private static Firebase database;
    private static final String url = "https://abstractfoods.firebaseio.com";
    private static final String foodpath = "food";
    private static final String userpath = "user";
    private ArrayList<String> foodnames;

    private User u;
    private Food f;

    public DBmanager() {
        if (database == null)
            setDatabase(new Firebase(url));
        foodnames = new ArrayList<>();

    }

    public void putFood(Food food) {
        food.encodeToBase64();
        database.child(foodpath).child(String.valueOf(food.getID())).setValue(food);
    }

    public void putUser(User user) {

        user.encodeToBase64();
        database.child(userpath).child(user.getUsername()).setValue(user);
        //user.decodeBase64();
    }

    public User getUser(User user) {
        u = user;
        System.err.println(user.getUsername());
        database.child(userpath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User temp = dataSnapshot.child(u.getUsername()).getValue(User.class);
                if (temp != null) {

                    u.setPassword(temp.getPassword());
                    u.setEmail(temp.getEmail());
                    u.setSanswer(temp.getSanswer());
                    u.setSquestion(temp.getSquestion());
                    u.setImage(temp.getImage());
                    if(temp.getCompressedImage()!=null)
                    u.setCompressedImage(temp.getCompressedImage());
                    u.decodeBase64();
                } else {
                    u.setPassword(null);
                    u.setEmail(null);
                    u.setSanswer(null);
                    u.setSquestion(null);
                    u.setImage(null);
                    u.setCompressedImage(null);

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return u;
    }

    public ArrayList<User> getUsers() {
        final ArrayList<User> users = new ArrayList<>();

        database.child(userpath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    User user = shot.getValue(User.class);
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return users;
    }

    public ArrayList<Food> getFood() {
        final ArrayList<Food> foods = new ArrayList<>();

        database.child(foodpath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    Food foo = shot.getValue(Food.class);
                    foo.decodeBase64();
                    foods.add(foo);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return foods;
    }

    public ArrayList<String> getFoodNames() {


        database.child(foodpath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String tempfoodname;

                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    tempfoodname = shot.getValue(Food.class).getName();
                    foodnames.add(tempfoodname);


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return foodnames;

    }
public Food getOneFood( Food foodselected)
{
    if(foodselected == null)return foodselected;
    f = foodselected;

    database.child(foodpath).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            for(DataSnapshot shot : dataSnapshot.getChildren())
            {
                Food temp = shot.getValue(Food.class);
                System.err.println(shot.getChildrenCount());
                if(temp==null)continue;
                if(f.getName().equals(temp.getName()))
                {
                    f.setUsername(temp.getUsername());
                    System.out.println(f.getUsername());
                    f.setCompressedImage(temp.getCompressedImage());
                    f.setDate(temp.getDate());
                    f.setID(temp.getID());
                    f.setLikes(temp.getLikes());
                    f.setDislikes(temp.getDislikes());
                    f.decodeBase64();

                    break;
                }

            }

        }


        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });




    return f;
}
    public Firebase getDatabase() {
        return database;
    }

    public void setDatabase(Firebase database) {
        this.database = database;
    }
}