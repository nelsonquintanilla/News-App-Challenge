package com.applaudostudios.newsapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.applaudostudios.newsapp.CategoryFragment;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static int NUM_ITEMS = 5;
    private String[] mTitles = new String[]{"Sports", "Politics", "Business", "Tech", "World"};

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Builds the complete url depending on the category.
     *
     * @param q is the category
     * @return the complete url to then use it when creating a new instance of the CategoryFragment
     */
    private String buildUrl(String q) {
        return "https://content.guardianapis.com/search?show-fields=headline%2Cthumbnail%2CbodyText&page=1&page-size=30&q="
                + q + "&api-key=f8bc1c2f-a416-4927-b866-b05b70de8f11";
    }

    // Returns a fragment associated wih each position
    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(buildUrl(mTitles[position]));
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
        return mTitles[position];
    }

}
