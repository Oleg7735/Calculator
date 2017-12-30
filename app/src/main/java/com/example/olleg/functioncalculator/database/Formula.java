package com.example.olleg.functioncalculator.database;

import java.io.FileOutputStream;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Formula extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private String description;
    private String  expression;
    RealmList<Argument> argumants;

    public Formula(){
        argumants = new RealmList<Argument>();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public RealmList<Argument> getArgumants() {
        return argumants;
    }

    public void setArgumants(RealmList<Argument> argumants) {
        this.argumants = argumants;
    }
}
