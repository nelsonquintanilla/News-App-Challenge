package com.applaudostudios.newsapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.applaudostudios.newsapp.CategoryFragment;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 5;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns a fragment associated wih each position
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CategoryFragment.newInstance(0, "Sports");
            case 1:
                return CategoryFragment.newInstance(1, "Politics");
            case 2:
                return CategoryFragment.newInstance(2, "Business");
            case 3:
                return CategoryFragment.newInstance(3, "Tech");
            case 4:
                return CategoryFragment.newInstance(4, "World");
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
