package com.example.olleg.functioncalculator.calculator.functions;

public class OpenBracket extends Function {
    @Override
    public double Invoke(double a, double b) {
        return 0;
    }
    public OpenBracket(){
        operator = "(";
        priority = -1;
    }
}
