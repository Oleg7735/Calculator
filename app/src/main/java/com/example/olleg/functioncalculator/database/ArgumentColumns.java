package com.example.olleg.functioncalculator.database;

/**
 * Created by Olleg on 09.12.2017.
 */

public enum ArgumentColumns {
    ID("id"), NAME("name"), DEFAULT_VALUE("defaulValue"), DESCRIPTION("argumentDescription");
    private final String text;
    private ArgumentColumns(final String text){
        this.text = text;
    }
    @Override
    public String toString(){
        return text;
    }
}
