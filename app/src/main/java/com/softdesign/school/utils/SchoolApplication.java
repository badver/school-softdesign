package com.softdesign.school.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.activeandroid.ActiveAndroid;

public class SchoolApplication extends com.activeandroid.app.Application {

    private static SharedPreferences preferences;

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        ActiveAndroid.initialize(this);
    }
}
