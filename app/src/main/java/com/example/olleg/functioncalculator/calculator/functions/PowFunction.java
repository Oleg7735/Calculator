package com.example.olleg.functioncalculator.calculator.functions;

public class PowFunction extends Function {
    @Override
    public double Invoke(double a, double b) {
        return Math.pow(a,b);
    }
    public PowFunction(){
        operator = "^";
        priority = 0;
    }
}
