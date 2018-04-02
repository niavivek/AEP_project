package ci;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InFixExpressionTest {
    @Test
    public void numberOfTokensInExpressionIs1() {
        InFixExpression expr = new InFixExpression("1");
        assertEquals(1, expr.getNumTokens());
    }
}