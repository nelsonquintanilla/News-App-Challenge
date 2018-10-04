package com.applaudostudios.newsapp.activities;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.applaudostudios.newsapp.R;
import com.applaudostudios.newsapp.adapters.FragmentPagerAdapter;
import com.applaudostudios.newsapp.data.NewsContract;
import com.applaudostudios.newsapp.data.NewsContract.NewsEntry;
import com.applaudostudios.newsapp.data.NewsDbHelper;
import com.applaudostudios.newsapp.model.News;


public class MainActivity extends AppCompatActivity {

//    NewsDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());

        //Sets the adapter to the ViewPager
        viewPager.setAdapter(adapter);
        viewPager.getCurrentItem();

        // Attaches the TabLayout to the ViewPager
        tabLayout.setupWithViewPager(viewPager);

        // Removes shadow from the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }

//        mDbHelper = new NewsDbHelper(this);
//
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        //category missing...
//        values.put(NewsEntry.COLUMN_NEWS_HEADLINE, "Title1");
//        values.put(NewsEntry.COLUMN_NEWS_BODY_TEXT, "BodyText1");
//        values.put(NewsEntry.COLUMN_NEWS_THUMBNAIL, "Thumbnail1");
//        values.put(NewsEntry.COLUMN_NEWS_WEB_URL, "WebUrl1");
//
//        db.insert(NewsEntry.TABLE_NAME, null, values);
    }

    // When the searchView is tapped, it creates the Searchable activity to make the search there.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search2:
                Intent intent = new Intent(this, SearchableActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Inflates the searchView in the MainActivity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
