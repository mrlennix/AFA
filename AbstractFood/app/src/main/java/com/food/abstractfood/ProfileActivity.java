package com.food.abstractfood;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPager viewPager2;
    SwipeAdapterMyUploads swipeAdapterMyUploads;
    SwipeAdapterMyFavorites swipeAdapterMyFavorites;
    Bitmap imageSelected;
    ImageButton imageButton1;
    ImageButton imageButton;
    final int select_image=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.activity_change_profile_picture);
        actionBar.setTitle(null);
        actionBar.setDisplayShowCustomEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager2 = (ViewPager) findViewById(R.id.view_pager2);
        swipeAdapterMyUploads = new SwipeAdapterMyUploads(this);
        swipeAdapterMyFavorites = new SwipeAdapterMyFavorites(this);
        viewPager.setAdapter(swipeAdapterMyUploads);
        viewPager2.setAdapter(swipeAdapterMyFavorites);


        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MSG","picture clicked");
                //Intent intent=new Intent(v.getContext(),ChangePassword.class);
                //startActivityForResult(intent,0);
            }
        });


        //Image source will be changed later on
        imageButton1=(ImageButton)findViewById(R.id.profile_pic);
        imageButton1.setImageResource(R.mipmap.ic_launcher);
        imageButton1.setBackgroundResource(R.drawable.round_background);

        //Text source will be changed later on
        TextView username=(TextView)findViewById(R.id.username_text_view);
        username.setText(" Username");



        TextView changeP = (TextView) findViewById(R.id.ChangePsText);
        changeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChangePassword.class);
                startActivityForResult(intent, 0);
            }
        });


    }





    //when the profile picture is clicked
    public void ButtonClickListener(View v) {


        ImageButton imageButton = (ImageButton) findViewById(R.id.profile_pic);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,select_image);
            }
        });

    }

    //This class will get the image chosen by the user and store it in Bitmap format
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==select_image)
        {
            Uri selectedImage=data.getData();
            imageButton1.setImageURI(selectedImage);
        }

    }





    private class SwipeAdapterMyFavorites extends PagerAdapter {
        private int[] image_res={R.drawable.upload,R.drawable.plate};
        private Context context;
        private LayoutInflater layoutInflater;

        public SwipeAdapterMyFavorites(Context ctx){
            this.context=ctx;
        }


        @Override
        public int getCount() {
            return image_res.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view==(LinearLayout)object);
        }



        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
            imageButton= (ImageButton)item_view.findViewById(R.id.image_button_swipe);
            imageButton.setImageResource(image_res[position]);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent next = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(next);
                }
            });
            container.addView(item_view);
            return item_view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }


    }

    private class SwipeAdapterMyUploads extends PagerAdapter {

        private int[] image_res={R.drawable.abstract_foods_default_pp,R.drawable.upload};
        private Context context;
        private LayoutInflater layoutInflater;

        public SwipeAdapterMyUploads(Context ctx){
            this.context=ctx;
        }


        @Override
        public int getCount() {
            return image_res.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view==(LinearLayout)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
            ImageButton imageButton= (ImageButton) item_view.findViewById(R.id.image_button_swipe);
            imageButton.setImageResource(image_res[position]);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent next = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(next);
                }
            });
            container.addView(item_view);
            return item_view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }





}
