package com.softdesign.school.data.storage.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Teams")
public class Team extends Model {

    @Column(name = "name")
    public String name;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public Team(String name) {
        super();
        this.name = name;
    }

    public static List<Team> getAll() {
        return new Select()
                .from(Team.class)
                .orderBy("name ASC")
                .execute();
    }

    public static List<String> getAllNames() {
        List<Team> teams = getAll();
        List<String> names = new ArrayList<>(teams.size());
        for (Team t: teams) {
            names.add(t.name);
        }
        return names;
    }

    public List<User> users() {
        return getMany(User.class, "team");
    }
}
