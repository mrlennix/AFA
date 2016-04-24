package com.food.abstractfood;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;
/*
This class is going to manage the tabs and add new tabs to tidy up things
mainly only used in the Discover Activity.
 */
public class D_TabLayoutAdapter extends FragmentStatePagerAdapter
{

    private final List <Fragment> mFragmentList = new ArrayList<>();
    private final List <String> mFragmentTitleList = new ArrayList<>();


    public D_TabLayoutAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragmentTitleList.get(position);
    }

   public void addSFragment(D_SearchTab fragment, String title)
    {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    public void addDFragment(D_BrowseTab fragment, String title)
    {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }




}
