package com.example.olleg.functioncalculator.calculator.functions;

public class AddFunction extends Function {

    @Override
    public double Invoke(double a, double b) {
        return a+b;
    }
    public AddFunction(){
        operator = "+";
        priority = 2;
    }
}
