package com.softdesign.school.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.activeandroid.ActiveAndroid;
import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;
import com.softdesign.school.data.storage.models.User;

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

        generateData(); // fill database with fake users and teams
    }

    private void generateData() {
        // fill users
        new User("Morlee", "Andreatta", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Kaye", "Santo", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Cly", "Dasse", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Ianthe", "Packer", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Cleve", "Arnold", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Marylee", "Pliego", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Dory", "Sen", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Kikelia", "Huber", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("John", "Maugham", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Perceval", "Reynolds", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Billie", "Hubsch", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Pedro", "Chuang", String.valueOf(R.drawable.ic_face_24dp)).save();

        // fill teams
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < 10; ++i) {
                new Team("Team #" + i).save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
