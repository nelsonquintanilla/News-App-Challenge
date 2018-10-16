package com.applaudostudios.newsapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;

import com.applaudostudios.newsapp.R;

/**
 * PreferenceFragment that handles the logic about the preferenceswhen the switch in the settings
 * is toggled.
 */
public class MySettingsFragment extends PreferenceFragment {
    private ThemeChange mThemeChange;
    private SwitchPreference switchPreference;

    public MySettingsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mThemeChange = (ThemeChange) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);

        switchPreference = (SwitchPreference) findPreference(
                this.getResources().getString(R.string.theme_switch));

        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                if (switchPreference.isChecked()) {
                    // If the switch is toggled to it's off state, sets the theme to day/light and
                    // sets the switch to off.
                    mThemeChange.themeHasChanged(true);
                    switchPreference.setChecked(false);
                } else {
                    // If the switch is toggled to it's on state, sets the theme to night/dark and
                    // sets the switch to on.
                    mThemeChange.themeHasChanged(false);
                    switchPreference.setChecked(true);
                }
                return false;
            }
        });
    }

    public interface ThemeChange {
        void themeHasChanged(boolean variable);
    }

}
