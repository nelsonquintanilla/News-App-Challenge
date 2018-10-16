package com.applaudostudios.newsapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;

import com.applaudostudios.newsapp.R;
import com.applaudostudios.newsapp.fragment.MySettingsFragment;

public class SettingsActivity extends AppCompatActivity implements MySettingsFragment.ThemeChange {
    public static final String KEY_THEME_SWITCH = "theme_switch";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean themeSettings = prefs.getBoolean(KEY_THEME_SWITCH, false);
        if (themeSettings) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_settings);

        // Sets the up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Display the fragment as the main content.
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new MySettingsFragment())
                    .commit();
        }
    }

    // Up button logic
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void themeHasChanged(boolean variable) {
        if (variable) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

}
