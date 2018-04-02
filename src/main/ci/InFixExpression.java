package ci;

import java.util.ArrayList;

public class InFixExpression {

    private String wholeExpr = "";
    private ArrayList<String> tokens = new ArrayList<>();

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

}