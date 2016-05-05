package com.food.abstractfood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


public class FoodActivity extends AppCompatActivity {
    private DBmanager database;
    private Food food;
    private User user = new User();
    ViewPager viewPagerFood;
    SwipeAdapterFoodPage swipeAdapterFoodPage;
    private TextView username,dateposted,likes,dislikes, discriptionTextView;
    private ImageView image,aniImage;
    private Button stepview,listview,report;
    private ViewPager view;
    private ImageView happyImage, sadImage;
    private ArrayList<Bitmap> map = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        food = (Food)getIntent().getSerializableExtra("selectedfood");
        Firebase.setAndroidContext(this);



        database = new DBmanager();
        food = database.getOneFood(food);


        database.getDatabase().child("image").child(food.getName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot shot : dataSnapshot.getChildren())

                {
                    FoodImage temp = shot.getValue(FoodImage.class);
                    if (temp.getCompressedImage() != null) {
                        temp.decodeBase64();
                        map.add(temp.getImage());

                    }
                }
                if(map.isEmpty())map.add(BitmapFactory.decodeResource(getResources(),R.drawable.plate));
                viewPagerFood = (ViewPager) findViewById(R.id.food_page_view_pager);
                swipeAdapterFoodPage = new SwipeAdapterFoodPage(getApplicationContext());
                viewPagerFood.setAdapter(swipeAdapterFoodPage);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        getSupportActionBar().setTitle(food.getName());
        aniImage = (ImageView)findViewById(R.id.aniImage);
        username = (TextView)findViewById(R.id.textView10);
        dateposted=(TextView)findViewById(R.id.textView11);
        likes = (TextView)findViewById(R.id.textView12) ;
        dislikes = (TextView)findViewById(R.id.textView13);
        image = (ImageView)findViewById(R.id.imageView3);
        view =(ViewPager)findViewById(R.id.food_page_view_pager);
        listview=(Button)findViewById(R.id.listViewBtn);
        report =(Button)findViewById(R.id.reportBtn);
        stepview = (Button)findViewById(R.id.button9);
        happyImage=(ImageView)findViewById(R.id.imageView4);
        sadImage = (ImageView)findViewById(R.id.imageView5);
        discriptionTextView = (TextView)findViewById(R.id.textView8);

        discriptionTextView.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.INVISIBLE);
        report.setVisibility(View.INVISIBLE);
        stepview.setVisibility(Button.INVISIBLE);
        view.setVisibility(View.INVISIBLE);
        username.setVisibility(TextView.INVISIBLE);
        dateposted.setVisibility(TextView.INVISIBLE);
        dateposted.setVisibility(TextView.INVISIBLE);
        image.setVisibility(ImageView.INVISIBLE);
        dislikes.setVisibility(TextView.INVISIBLE);
        happyImage=(ImageView)findViewById(R.id.imageView4);
        sadImage = (ImageView)findViewById(R.id.imageView5);
        happyImage.setVisibility(View.INVISIBLE);
        sadImage.setVisibility(View.INVISIBLE);
        likes.setVisibility(View.INVISIBLE);

        database.getDatabase().child(database.getUserpath()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(food.getUsername()).getValue(User.class).getCompressedImage() == null)
                    {
                        user.setImage(BitmapFactory.decodeResource(getResources(),R.drawable.addprofilepicture));
                        return;
                    }else
                    {
                    user.setCompressedImage(dataSnapshot.child(food.getUsername()).getValue(User.class).getCompressedImage());
                    user.decodeBase64();
                    }
                    image.setImageBitmap(user.getImage());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.loading);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                user.setUsername(food.getUsername());
                report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vws) {

                        Intent intent = new Intent(vws.getContext(), ReportActivity.class);
                        startActivityForResult(intent, 0);
                    }
                });

                listview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vw) {

                        Intent intent = new Intent(vw.getContext(), FoodListViewActivity.class);
                        startActivityForResult(intent, 0);
                    }
                });
                discriptionTextView.setVisibility(View.VISIBLE);
                aniImage.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
                report.setVisibility(View.VISIBLE);
                stepview.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                username.setVisibility(TextView.VISIBLE);
                dateposted.setVisibility(TextView.VISIBLE);
                dateposted.setVisibility(TextView.VISIBLE);
                image.setVisibility(ImageView.VISIBLE);
                dislikes.setVisibility(TextView.VISIBLE);
                happyImage.setVisibility(View.VISIBLE);
                sadImage.setVisibility(View.VISIBLE);
                likes.setVisibility(View.VISIBLE);


                Toast.makeText(getBaseContext(),food.getDate(),Toast.LENGTH_LONG);
                Button listViewBtn = (Button) findViewById(R.id.listViewBtn);

                dateposted.setText(food.getDate());
                username.setText(food.getUsername());
                likes.setText(food.getLikes());
                dislikes.setText(food.getDislikes());
                discriptionTextView.setText(food.getDescription());
                if(food.getUsername()!=null)
                database.getDatabase().child("user").child(food.getUsername()).addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        User temp = dataSnapshot.getValue(User.class);
                        if(temp.getImage()!=null)image.setImageBitmap(temp.getImage());

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }


            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        aniImage.startAnimation(anim);


    }

    private class SwipeAdapterFoodPage extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        public SwipeAdapterFoodPage(Context ctx){
            this.context=ctx;
        }



        @Override
        public int getCount() {
            return map.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view==(LinearLayout)object);
        }

        public Object instantiateItem(ViewGroup container, int position){
            layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View item_view=layoutInflater.inflate(R.layout.food_page_swipe_layout,container,false);
            ImageView imageView=(ImageView)item_view.findViewById(R.id.food_image_view);
            //imageView.setImageBitmap(map.get(position));
            imageView.setImageBitmap(map.get(position ));
            container.addView(item_view);
            return item_view;
        }

        public void destroyItem(ViewGroup container,int position,Object object){
            container.removeView((LinearLayout)object);
        }
    }

    @Override
    protected void onDestroy() {
        System.out.println("Finished!");

        for(int x =0;x<map.size();x++)
            map.get(x).recycle();
        super.onDestroy();
        System.gc();
        finish();
    }
}