package com.food.abstractfood;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Manny on 5/4/16.
 */
public class FoodImage
{
    private Bitmap image;
    private String compressedImage;


    public FoodImage(){}

    public void encodeToBase64()
    {
        if(getImage() ==null)return;
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        getImage().compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOS);
        setCompressedImage(Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT));
        setImage(null);

    }

    public void decodeBase64()
    {
        if(getCompressedImage() ==null)return;
        byte[] decodedBytes = Base64.decode(getCompressedImage(), 0);
        setImage(BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length));
        setCompressedImage(null);
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCompressedImage() {
        return compressedImage;
    }

    public void setCompressedImage(String compressedImage) {
        this.compressedImage = compressedImage;
    }
}
