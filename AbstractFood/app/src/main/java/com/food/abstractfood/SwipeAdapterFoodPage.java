package com.food.abstractfood;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by John Doe on 4/19/2016.
 */
public class SwipeAdapterFoodPage extends PagerAdapter{
    private int[] image_res={R.drawable.upload,R.drawable.plate};
    private Context context;
    private LayoutInflater layoutInflater;

    public SwipeAdapterFoodPage(Context ctx){
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

    public Object instantiateItem(ViewGroup container,int position){
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.food_page_swipe_layout,container,false);
        ImageView imageView=(ImageView)item_view.findViewById(R.id.food_image_view);
        imageView.setImageResource(image_res[position]);
        container.addView(item_view);
        return item_view;



    }

    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((LinearLayout)object);
    }
}
