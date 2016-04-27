package com.food.abstractfood;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

//USER CLASS STORES INFORMATION ABOUT THE USER
public class User implements Serializable
{
    private String username;
    private String password;
    private String email;
    private String squestion;
    private String sanswer;
    private Bitmap image;
    private String compressedImage;

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

    public Bitmap getImage() {return image;}

    public void setImage(Bitmap image) {this.image = image;}



    @Override
    public String toString() {
        return getUsername();
    }

    public void encodeToBase64()
    {
        if(image==null)return;
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 1, byteArrayOS);
        setCompressedImage(Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT));
        setImage(null);

    }

    public void decodeBase64()
    {
        if(getCompressedImage() ==null)return;
        byte[] decodedBytes = Base64.decode(getCompressedImage(), 0);
        image =  BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        setCompressedImage(null);
    }

    public String getCompressedImage() {
        return compressedImage;
    }

    public void setCompressedImage(String compressedImage) {
        this.compressedImage = compressedImage;
    }

}