package com.example.olleg.functioncalculator.calculator.functions;

public class MultiplyFunction extends Function {
    @Override
    public double Invoke(double a, double b) {
        return a*b;
    }
    public MultiplyFunction(){
        operator = "*";
        priority = 1;
    }
}
