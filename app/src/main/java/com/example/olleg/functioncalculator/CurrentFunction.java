package com.example.olleg.functioncalculator;

import com.example.olleg.functioncalculator.database.Formula;

/**
 * Created by Olleg on 14.12.2017.
 */

public class CurrentFunction {
    Formula formula;
    public boolean fromDB = false;
    private static final CurrentFunction ourInstance = new CurrentFunction();

    public static CurrentFunction getInstance() {
        return ourInstance;
    }

    public Formula getFormula(){
        return formula;
    }
    private CurrentFunction() {
        formula = new Formula();
    }
    public void setFormula(Formula f){
        formula = f;
    }
}
