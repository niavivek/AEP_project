package ci;

public class InFixExpression {

    private String wholeExpr = "";

    public InFixExpression(String wholeExpr) {
        if (wholeExpr != null) {
            this.wholeExpr = wholeExpr;
        }
    }

    public int getNumTokens() {
        return 1;
    }
}