package com.softdesign.school.data.storage.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Teams")
public class Team extends Model {

    @Column(name = "name")
    public String name;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public List<User> users() {
        return getMany(User.class, "team");
    }
}
