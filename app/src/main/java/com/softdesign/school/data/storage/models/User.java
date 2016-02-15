package com.softdesign.school.data.storage.models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Users")
public class User {
    @Column(name = "first_name") public String mFirstName;
    @Column(name = "last_name") public String mLastName;
    @Column(name = "email") public String mEmail;
    @Column(name = "phone") public String mPhoneNumber;
    @Column(name = "image") public String mImage;
    @Column(name = "vk_link") public String mVkProfile;
    @Column(name = "github_link") public String mGithubRepo;
    @Column(name = "rate") public int mRate;
    @Column(name = "hometask") public int mHometask;

    public User() {
    }

    public User(String lastName, String firstName, String image) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mImage = image;
    }
}
