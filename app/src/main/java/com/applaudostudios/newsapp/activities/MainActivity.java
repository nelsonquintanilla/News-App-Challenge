package com.applaudostudios.newsapp.activities;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;

import com.applaudostudios.newsapp.R;
import com.applaudostudios.newsapp.adapters.FragmentPagerAdapter;
import com.applaudostudios.newsapp.model.News;

import java.util.List;


public class MainActivity extends AppCompatActivity {

//    NewsDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Setting the theme
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ensures that your app is properly initialized with default settings.
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);

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
//        db.insert(NewsEntry.TABLE_NAME, null, values);

        //insert
//        ContentValues values = new ContentValues();
//        values.put(NewsEntry.COLUMN_NEWS_CATEGORY, "Sports");
//        values.put(NewsEntry.COLUMN_NEWS_HEADLINE, "Title1");
//        values.put(NewsEntry.COLUMN_NEWS_BODY_TEXT, "BodyText1");
//        values.put(NewsEntry.COLUMN_NEWS_THUMBNAIL, "Thumbnail1");
//        values.put(NewsEntry.COLUMN_NEWS_WEB_URL, "WebUrl1");
//
//        getContentResolver().insert(NewsEntry.CONTENT_URI, values);
//
//        ContentValues values1 = new ContentValues();
//        values1.put(NewsEntry.COLUMN_NEWS_CATEGORY, "Sports");
//        values1.put(NewsEntry.COLUMN_NEWS_HEADLINE, "Title1");
//        values1.put(NewsEntry.COLUMN_NEWS_BODY_TEXT, "BodyText1");
//        values1.put(NewsEntry.COLUMN_NEWS_THUMBNAIL, "Thumbnail1");
//        values1.put(NewsEntry.COLUMN_NEWS_WEB_URL, "WebUrl1");
//
//        getContentResolver().insert(NewsEntry.CONTENT_URI, values1);
//
//        ContentValues values2 = new ContentValues();
//        values2.put(NewsEntry.COLUMN_NEWS_CATEGORY, "Sports");
//        values2.put(NewsEntry.COLUMN_NEWS_HEADLINE, "Title1");
//        values2.put(NewsEntry.COLUMN_NEWS_BODY_TEXT, "BodyText1");
//        values2.put(NewsEntry.COLUMN_NEWS_THUMBNAIL, "Thumbnail1");
//        values2.put(NewsEntry.COLUMN_NEWS_WEB_URL, "WebUrl1");
//
//        getContentResolver().insert(NewsEntry.CONTENT_URI, values2);
//
//        ContentValues values3 = new ContentValues();
//        values3.put(NewsEntry.COLUMN_NEWS_CATEGORY, "NOTHING");
//        values3.put(NewsEntry.COLUMN_NEWS_HEADLINE, "Title5");
//        values3.put(NewsEntry.COLUMN_NEWS_BODY_TEXT, "BodyText5");
//        values3.put(NewsEntry.COLUMN_NEWS_THUMBNAIL, "Thumbnail5");
//        values3.put(NewsEntry.COLUMN_NEWS_WEB_URL, "WebUrl5");
//
//        getContentResolver().update(Uri.withAppendedPath(NewsEntry.CONTENT_URI,"5"), values3,  null, null );

//        getContentResolver().delete(Uri.withAppendedPath(NewsEntry.CONTENT_URI,"5"), null,  null);

//        getContentResolver().delete(NewsEntry.CONTENT_URI, null, null);

    }

    // When the searchView is tapped, it creates the Searchable activity to make the search there.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search2:
                Intent intent = new Intent(this, SearchableActivity.class);
                startActivity(intent);
                break;
            case R.id.action_choose_theme:
                Intent SettingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(SettingsIntent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Inflates the searchView in the MainActivity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
