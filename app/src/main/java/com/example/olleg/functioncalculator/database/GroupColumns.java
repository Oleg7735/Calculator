package com.example.olleg.functioncalculator.database;

/**
 * Created by Olleg on 09.12.2017.
 */

public enum GroupColumns {
    ID("id"), NAME("name"), FORMULAS("furmula");
    private final String text;
    private GroupColumns(final String text){
        this.text = text;
    }
    @Override
    public String toString(){
        return text;
    }
}
