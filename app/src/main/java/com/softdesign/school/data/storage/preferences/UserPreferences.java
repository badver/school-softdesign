package com.softdesign.school.data.storage.preferences;

import android.content.SharedPreferences;

import com.softdesign.school.utils.SchoolApplication;

import java.util.ArrayList;
import java.util.List;

public class UserPreferences {

    private static final String USER_PROFILE_PHONE = "phone";
    private static final String USER_PROFILE_EMAIL = "email";
    private static final String USER_PROFILE_VK = "vk";
    private static final String USER_PROFILE_GIT = "git";
    private static final String USER_PROFILE_BIO = "bio";
    private static final String[] USER_FIELDS = {USER_PROFILE_PHONE, USER_PROFILE_EMAIL, USER_PROFILE_VK, USER_PROFILE_GIT, USER_PROFILE_BIO};
    private SharedPreferences mPreferences;

    public UserPreferences() {
    }

    public void saveUserProfileData(List<String> userFields) {
        mPreferences = SchoolApplication.getPreferences();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();

        for (int i = 0; i < userFields.size(); ++i) {
            preferenceEditor.putString(USER_FIELDS[i], userFields.get(i));
        }

        preferenceEditor.apply();
    }

    public List<String> loadUserProfileData() {
        mPreferences = SchoolApplication.getPreferences();
        List<String> userFields = new ArrayList<>();
        userFields.add(mPreferences.getString(USER_PROFILE_PHONE, "pref tel"));
        userFields.add(mPreferences.getString(USER_PROFILE_EMAIL, "pref mail"));
        userFields.add(mPreferences.getString(USER_PROFILE_VK, "pref vk"));
        userFields.add(mPreferences.getString(USER_PROFILE_GIT, "pref git"));
        userFields.add(mPreferences.getString(USER_PROFILE_BIO, "pref bio"));
        return userFields;
    }
}
