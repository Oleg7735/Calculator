package com.example.olleg.functioncalculator.calculator.functions;

public class SubFunction extends Function {
    @Override
    public double Invoke(double a, double b) {
        return a-b;
    }
    public SubFunction(){
        operator = "-";
        priority = 2;
    }
}
