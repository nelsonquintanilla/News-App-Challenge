package com.applaudostudios.newsapp.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.applaudostudios.newsapp.R;

public class MySettingsFragment extends PreferenceFragment {

    public MySettingsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);

        final SwitchPreference themePreference = (SwitchPreference) findPreference(this.getResources().getString(R.string.theme_switch));
        themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                if(themePreference.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    themePreference.setChecked(false);
                }else {
                    getActivity().setTheme(AppCompatDelegate.MODE_NIGHT_NO);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    themePreference.setChecked(true);
                }
                return false;
            }
        });
    }

}
