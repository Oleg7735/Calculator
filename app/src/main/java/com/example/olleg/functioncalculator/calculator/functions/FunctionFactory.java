package com.example.olleg.functioncalculator.calculator.functions;

public class FunctionFactory {
    public Function CreateFunction(String operator){
        switch (operator){
            case "+":{
                return new AddFunction();
            }
            case "-":{
                return new SubFunction();
            }
            case "*":{
                return new MultiplyFunction();
            }
            case "/":{
                return new DivideFunction();
            }
            case "(":{
                return new OpenBracket();
            }
            case ")":{
                return new CloseBracket();
            }
            case "^":{
                return new PowFunction();
            }
            default:{
                throw new IllegalArgumentException("Can not find operator "+operator+"in FunctionFactory templates");
            }
        }
    }
}
