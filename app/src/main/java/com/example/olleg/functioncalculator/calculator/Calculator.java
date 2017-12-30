package com.example.olleg.functioncalculator.calculator;



import com.example.olleg.functioncalculator.calculator.functions.Function;
import com.example.olleg.functioncalculator.calculator.functions.FunctionFactory;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private Pattern operationPattern = Pattern.compile("^(\\+|-|\\*|/|\\(|\\)|\\^)");
    private Pattern doublePattern = Pattern.compile("^([-]?\\d+(\\.\\d+)?)");//("([-]?[0-9]+(.[0-9]+)?)");;
    //private String operaionsPattern = "^(+|-|\\(|\\)|\\*|/|\\^)";
    private Stack<Double> operandsStack = new Stack<>();
    private Stack<Function> functionsStack = new Stack<>();

    double a;
    double b;
    private void popFunction(){
        b = operandsStack.pop();
        a = operandsStack.pop();
        operandsStack.push(functionsStack.pop().Invoke(a,b));
    }
    private boolean canPopFunction(Function f){
        if(functionsStack.empty()){
            return false;
        }
        int prevFunctionPriority = functionsStack.peek().getPriority();
        return f.getPriority() >= 0 && prevFunctionPriority >= 0 && f.getPriority() > prevFunctionPriority;
    }
    private void processFunction(Function operator){

        if(operator.getOperator().equals(")")){
            while((!functionsStack.empty()) && !functionsStack.peek().getOperator().equals("(")){
                popFunction();
            }
            functionsStack.pop();
        }
        else {
            while(canPopFunction(operator)){
                popFunction();
            }
            functionsStack.push(operator);
        }
    }
    /*private void processTockenAsOperator(){

    }
    private void processTockenAsOperand(){

    }*/
    public double calculate(String stringToCalculate){
        FunctionFactory factory = new FunctionFactory();

        String workString = stringToCalculate;

        boolean prevTockenDouble = true;
        String prevTocken = "";

        workString = "("+workString+")";
        workString = workString.replaceAll(" ","");
        workString = workString.replaceAll("\\+-","-");
        workString = workString.replaceAll("--","+");
        workString = workString.replaceAll("-\\+","-");
        workString = workString.replaceAll("\\+\\+","+");

        Matcher operationMatcher;
        Matcher doubleMatcher;
        String token;
        while(workString.length() > 0) {
            operationMatcher = operationPattern.matcher(workString);
            if (operationMatcher.find()) {
                token = operationMatcher.group();
                if(token.equals("-")){
                    doubleMatcher = doublePattern.matcher(workString);
                    if (!doubleMatcher.find()) {
                        throw new IllegalArgumentException("Can not parse the string " + workString);
                    }
                    prevTockenDouble = true;
                    token = doubleMatcher.group();
                    processFunction(factory.CreateFunction("+"));
                    operandsStack.push(Double.parseDouble(token));

                }
                else {
                    processFunction(factory.CreateFunction(token));
                    prevTockenDouble = false;
                }

            } else {
                doubleMatcher = doublePattern.matcher(workString);

                if (!doubleMatcher.find()) {
                    throw new IllegalArgumentException("Can not parse the string " + workString);
                }
                prevTockenDouble = true;
                token = doubleMatcher.group();
                operandsStack.push(Double.parseDouble(token));
            }
            prevTocken = token;
            workString = workString.substring(token.length());
        }
        return operandsStack.pop();

    }
}
