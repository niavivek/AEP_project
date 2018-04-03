package ci;
import org.junit.Test;

import java.util.EmptyStackException;

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

    @Test
    public void plusMinusHaveSecondPrecedence() {
        InFixExpression expr = new InFixExpression();
        assertEquals(2, expr.checkPrecedence("+"));
        assertEquals(2, expr.checkPrecedence("-"));
    }

    @Test
    public void multiplyDivideHaveThirdPrecedence() {
        InFixExpression expr = new InFixExpression();
        assertEquals(3, expr.checkPrecedence("*"));
        assertEquals(3, expr.checkPrecedence("/"));
    }

    @Test(expected = NumberFormatException.class)
    public void alphabetsAreNotValidTokens() {
        InFixExpression expr = new InFixExpression();
        expr.checkValidity("a");
    }

    @Test
    public void onePlusTwoIsValidExpression() {
        InFixExpression expr = new InFixExpression("( 1 + 2 )");
        assertEquals(true, expr.checkExpression());
    }

    @Test(expected = NumberFormatException.class)
    public void aPlusbIsNotAValidExpression() {
        InFixExpression expr = new InFixExpression("( a + b )");
        expr.checkExpression();
    }

    @Test
    public void threeTimesFourIsTwelve() {
        InFixExpression expr = new InFixExpression("( 3 * 4 )");
        assertEquals(12.0, expr.evaluate());
    }

    @Test
    public void threeTimesFourPlusFourTimesFiveIsThirtyTwo() {
        InFixExpression expr = new InFixExpression("( 3 * 4 ) + ( 4 * 5 )");
        assertEquals(32.0, expr.evaluate());
    }

    @Test(expected = EmptyStackException.class)
    public void testingAnInvalidExpression() {
        InFixExpression expr = new InFixExpression("( 3 * 4 ) + ( 4 ) )");
        assertEquals(16.0, expr.evaluate());
    }

}