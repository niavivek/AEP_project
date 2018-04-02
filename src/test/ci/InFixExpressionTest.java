package ci;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InFixExpressionTest {
    @Test
    public void numberOfTokensInExpressionShouldBe1() {
        InFixExpression expr = new InFixExpression("1");
        assertEquals(1, expr.getNumTokens());
    }

    @Test
    public void numberOfTokensInExpressionShouldBe3() {
        InFixExpression expr = new InFixExpression("1 + 2");
        assertEquals(3, expr.getNumTokens());
    }

    @Test
    public void plusMinusDivideMultiplyShouldBeOperators() {
        InFixExpression expr = new InFixExpression();
        assertEquals(true, expr.checkOperator("+"));
        assertEquals(true, expr.checkOperator("-"));
        assertEquals(true, expr.checkOperator("/"));
        assertEquals(true, expr.checkOperator("*"));
    }

    @Test
    public void bracketsHaveFirstPrecedence() {
        InFixExpression expr = new InFixExpression();
        assertEquals(1, expr.checkPrecedence("("));
        assertEquals(1, expr.checkPrecedence(")"));
    }

}