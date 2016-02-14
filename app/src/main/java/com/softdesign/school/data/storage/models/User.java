package com.softdesign.school.data.storage.models;

import android.graphics.drawable.Drawable;

public class User {
    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPhoneNumber;
    private Drawable mImage;
    private String mVkProfile;
    private String mGithubRepo;
    private int mRate;
    private int mHometask;

    public User(String lastName, String firstName, Drawable image) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mImage = image;
    }

    public int getId() {
        return mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public Drawable getImage() {
        return mImage;
    }

    public String getVkProfile() {
        return mVkProfile;
    }

    public String getGithubRepo() {
        return mGithubRepo;
    }

    public int getRate() {
        return mRate;
    }

    public int getHometask() {
        return mHometask;
    }

}
