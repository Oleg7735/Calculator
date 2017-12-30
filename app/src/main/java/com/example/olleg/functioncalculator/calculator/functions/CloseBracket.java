package com.example.olleg.functioncalculator.calculator.functions;

public class CloseBracket extends Function {
    @Override
    public double Invoke(double a, double b) {
        return 0;
    }
    public CloseBracket(){
        operator = ")";
        priority = -1;
    }
}
