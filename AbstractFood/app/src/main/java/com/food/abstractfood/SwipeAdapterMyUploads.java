package com.food.abstractfood;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Mistere on 4/15/2016.
 */
public class SwipeAdapterMyUploads extends PagerAdapter {

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
        ImageView imageView= (ImageView)item_view.findViewById(R.id.image_view);
        imageView.setImageResource(image_res[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
