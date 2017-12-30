package com.example.olleg.functioncalculator.calculator.functions;

public abstract class Function {
    protected int priority;
    protected String operator;
    public abstract double Invoke(double a, double b);
    public int getPriority(){
        return priority;
    }

    public String getOperator() {
        return operator;
    }
}

