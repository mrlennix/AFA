package com.food.abstractfood;

import android.net.Uri;

//USER CLASS STORES INFORMATION ABOUT THE USER
public class User
{
    private String username;
    private String password;
    private String email;
    private String squestion;
    private String sanswer;
    private Uri image;

    public User(){}


    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSquestion()
    {
        return squestion;
    }

    public void setSquestion(String squestion)
    {
        this.squestion = squestion;
    }

    public String getSanswer() {
        return sanswer;
    }

    public void setSanswer(String sanswer)
    {
        this.sanswer = sanswer;
    }

    public Uri getImage() {return image;}

    public void setImage(Uri image) {this.image = image;}

    @Override
    public String toString() {
        return getUsername();
    }


}
