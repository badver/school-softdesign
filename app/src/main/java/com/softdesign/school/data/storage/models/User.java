package com.softdesign.school.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Users")
public class User extends Model {
    @Column(name = "first_name") public String mFirstName;
    @Column(name = "last_name") public String mLastName;
    @Column(name = "email") public String mEmail;
    @Column(name = "phone") public String mPhoneNumber;
    @Column(name = "image") public String mImage;
    @Column(name = "vk_link") public String mVkProfile;
    @Column(name = "github_link") public String mGithubRepo;
    @Column(name = "rate") public int mRate;
    @Column(name = "hometask") public int mHometask;
    @Column(name = "team") public Team mTeam;

    public User() {
    }

    public User(String lastName, String firstName, String image) {
        super();
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mImage = image;
    }

    public User(String lastName, String firstName, Team team) {
        super();
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mTeam = team;
    }

    public static List<User> getAll() {
        return new Select()
                .from(User.class)
                .execute();
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

    public String getImage() {
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

    public Team getTeam() {
        return mTeam;
    }
}
