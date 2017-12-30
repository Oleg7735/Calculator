package com.example.olleg.functioncalculator.database;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Olleg on 07.12.2017.
 */

public class Argument extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private double defaulValue;
    private String argumentDescription;
/*    public  Argument(){

    }

    public Argument(long id, String name, double defaulValue, String argumentDescription) {
        this.id = id;
        this.name = name;
        this.defaulValue = defaulValue;
        this.argumentDescription = argumentDescription;
    }*/


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

    public double getDefaulValue() {
        return defaulValue;
    }

    public void setDefaulValue(double defaulValue) {
        this.defaulValue = defaulValue;
    }

    public String getArgumentDescription() {
        return argumentDescription;
    }

    public void setArgumentDescription(String argumentDescription) {
        this.argumentDescription = argumentDescription;
    }
}
