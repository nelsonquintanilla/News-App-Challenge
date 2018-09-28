package com.applaudostudios.newsapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.applaudostudios.newsapp.CategoryFragment;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static int NUM_ITEMS = 5;

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

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns a fragment associated wih each position
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CategoryFragment.newInstance(0, "Sports", SPORTS_URL);
            case 1:
                return CategoryFragment.newInstance(1, "Politics", POLITICS_URL);
            case 2:
                return CategoryFragment.newInstance(2, "Business", BUSINESS_URL);
            case 3:
                return CategoryFragment.newInstance(3, "Tech", TECH_URL);
            case 4:
                return CategoryFragment.newInstance(4, "World", WORLD_URL);
            default:
                break;
        }
        return null;
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
        return "page" + position;
    }
}
