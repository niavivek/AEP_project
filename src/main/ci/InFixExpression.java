package ci;

import java.util.ArrayList;

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
    }

    public int getNumTokens() {
        tokenize();
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
                float value_float = Float.parseFloat(value);
            }
            catch(NumberFormatException e){
                System.out.println("The expression can only contain numbers, parenthesis and operators.");
                throw e;
            }
            return true;
        }

        }
    }
