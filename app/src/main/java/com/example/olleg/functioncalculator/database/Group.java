package com.example.olleg.functioncalculator.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Olleg on 07.12.2017.
 */

public class Group extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private RealmList<Formula> furmula;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Formula> getFurmula() {
        return furmula;
    }

    public void setFurmula(RealmList<Formula> furmula) {
        this.furmula = furmula;
    }
}
