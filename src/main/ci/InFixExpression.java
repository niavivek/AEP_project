package ci;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class InFixExpression {

    private String wholeExpr;
    private ArrayList<String> tokens = new ArrayList<>();

    public InFixExpression() {
        this.wholeExpr = "";
    }

    public InFixExpression(String wholeExpr) {
        if (wholeExpr != null) {
            this.wholeExpr = wholeExpr;
        }
        tokenize();
    }

    public int getNumTokens() {
        return tokens.size();
    }

    private void tokenize() {
        String[] allTokens = wholeExpr.split("[ \t]+");
        tokens.clear();
        for (int i = 0; i < allTokens.length; ++i) {
            tokens.add(allTokens[i]);
        }
    }

    public boolean checkOperator(String operator) {
        if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))
            return true;
        else
            return false;
    }


    public int checkPrecedence(String stringToken) {
        if (stringToken.equals("(") || stringToken.equals(")"))
            return 1;
        else if (stringToken.equals("+") || stringToken.equals("-"))
            return 2;
        else if (stringToken.equals("*") || stringToken.equals("/"))
            return 3;
        return 0;
    }


    public boolean checkValidity(String value) {
        if (checkPrecedence(value) > 0) return true;
        else{
            try{
                Double valueDouble = Double.parseDouble(value);
            }
            catch(NumberFormatException e){
                System.err.println("The expression can only contain numbers, parenthesis and operators.");
                throw e;
            }
            return true;
        }

        }

    public boolean checkExpression() {
        for(String t: this.tokens){
            checkValidity(t);
        }
        return true;
    }

    private void calculate(Stack<String> operatorStack, Stack<Double> valueStack) {
        Double rightOperand, leftOperand, tempVal;
        String op = operatorStack.pop();

        if (valueStack.isEmpty())
            return;
        rightOperand = valueStack.pop();
        if (valueStack.isEmpty())
            return;
        leftOperand = valueStack.pop();

        tempVal = 0.0;

        switch (op) {
            case "+":
                tempVal = leftOperand + rightOperand;
                break;
            case "-":
                tempVal = leftOperand - rightOperand;
                break;
            case "*":
                tempVal = leftOperand * rightOperand;
                break;
            case "/":
                tempVal = leftOperand / rightOperand;
                break;
        }
            valueStack.push(tempVal);
    }


    public Double evaluate() {
        Double result;
        checkExpression();
        Stack<String> operatorStack = new Stack<>();
        Stack<Double> valueStack = new Stack<>();
        try {
            for (String temp : tokens) {
                if (checkOperator(temp)) {
                    if (operatorStack.isEmpty())
                        operatorStack.push(temp);
                    else {
                        if (checkPrecedence(temp) > checkPrecedence(operatorStack.peek()))
                            operatorStack.push(temp);
                        else {
                            while (!operatorStack.isEmpty() && (checkPrecedence(temp) <= checkPrecedence(operatorStack.peek())))
                                calculate(operatorStack, valueStack);
                            operatorStack.push(temp);
                        }
                    }
                } else if (temp.equals("("))
                    operatorStack.push(temp);
                else if (temp.equals(")")) {
                    while (!operatorStack.peek().equals("("))
                        calculate(operatorStack, valueStack);
                    operatorStack.pop();
                } else {
                    Double val = Double.parseDouble(temp);
                    valueStack.push(val);
                }
            }
        }
        catch (EmptyStackException ese){
            System.err.println("The expression you entered is invalid.");
            throw ese;
        }
        while (!operatorStack.isEmpty()){
            calculate(operatorStack, valueStack);
        }
        if(valueStack.size() == 1){
            result = valueStack.pop();
        }
        else
            result = 0.0;
        return result;
    }
}
