package com.example.olleg.functioncalculator.database;

/**
 * Created by Olleg on 07.12.2017.
 */
public enum FormulaColumns{
    ID("id"), NAME("name"), DESCRIPTION("description"), EXPRESION("expression"), ARGUMENTS("argumants");
    private final String text;
    FormulaColumns(final String text){
        this.text = text;
    }
    @Override
    public String toString(){
        return text;
    }
}
