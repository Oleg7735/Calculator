package com.example.olleg.functioncalculator.calculator.functions;

public class DivideFunction extends Function {
    @Override
    public double Invoke(double a, double b) {
        return a / b;
    }
    public DivideFunction(){
        priority = 1;
        operator = "/";
    }
}
