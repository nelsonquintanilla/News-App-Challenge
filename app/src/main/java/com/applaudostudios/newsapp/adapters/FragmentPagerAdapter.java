package com.applaudostudios.newsapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.applaudostudios.newsapp.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static int NUM_ITEMS = 5;

    public static final int SPORTS_ID = 1;
    public static final int POLITICS_ID = 2;
    public static final int BUSINESS_ID = 3;
    public static final int TECH_ID = 4;
    public static final int WORLD_ID = 5;

    public static final String SPORTS_URL =
            "https://content.guardianapis.com/search?show-fields=headline&page=1&page-size=30&q=sports&api-key=f8bc1c2f-a416-4927-b866-b05b70de8f11";
    public static final String POLITICS_URL =
            "https://content.guardianapis.com/search?show-fields=headline&page=1&page-size=30&q=politics&api-key=f8bc1c2f-a416-4927-b866-b05b70de8f11";
    public static final String BUSINESS_URL =
            "https://content.guardianapis.com/search?show-fields=headline&page=1&page-size=30&q=business&api-key=f8bc1c2f-a416-4927-b866-b05b70de8f11";
    public static final String TECH_URL =
            "https://content.guardianapis.com/search?show-fields=headline&page=1&page-size=30&q=tech&api-key=f8bc1c2f-a416-4927-b866-b05b70de8f11";
    public static final String WORLD_URL =
            "https://content.guardianapis.com/search?show-fields=headline&page=1&page-size=30&q=world&api-key=f8bc1c2f-a416-4927-b866-b05b70de8f11";

    private List<CategoryFragment> fragmentList;

    private List<String> titlesList;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        titlesList = new ArrayList<>();
    }

    // Returns a fragment associated wih each position
    @Override
    public Fragment getItem(int position) {
     return fragmentList.get(position);
    }

    // Returns the number of pages in the ViewPager
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns a string describing each page
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }

    public void addFragment(String title, CategoryFragment fragment){
        fragmentList.add(fragment);
        titlesList.add(title);
    }
}
