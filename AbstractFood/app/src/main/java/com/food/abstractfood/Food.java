package com.food.abstractfood;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
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
    private String compressedImage;
    private Bitmap image;
    private String likes ="0";
    private String dislikes="0";


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

    public Bitmap getImage()
    {
        return image;
    }

    public void setImage(Bitmap image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return name;
    }


    public String getCompressedImage() {
        return compressedImage;
    }

    public void setCompressedImage(String compressedImage) {
        this.compressedImage = compressedImage;
    }

    public void encodeToBase64()
    {
        if(image==null)return;
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOS);
        compressedImage = Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
        image=null;

    }

    public void decodeBase64()
    {
        if(compressedImage==null)return;
        byte[] decodedBytes = Base64.decode(compressedImage, 0);
        image =  BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        compressedImage=null;
    }


    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }
}
