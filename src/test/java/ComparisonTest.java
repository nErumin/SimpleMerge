import org.junit.Assert;
import org.junit.Test;

public class ComparisonTest {
    private Comparison c = new Comparison();
    private String X, Y;

    /**
     * Case 1: Same length input String
     *
     */
    @Test
    public void sameStringTest() {
        X = "123456789";
        Y = "123456789";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertTrue(result);
    }


    @Test
    public void nomalCaseTest() {
        X = "123456789";
        Y = "321456789";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }


    @Test
    public void nullLeftStringTest() {
        X = "";
        Y = "321456789";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }


    @Test
    public void nullRightStringTest() {
        X = "123456789";
        Y = "";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }


    @Test
    public void nullStringTest() {
        X = "";
        Y = "";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertTrue(result);
    }


    @Test
    public void shortLeftStringTest() {
        X = "123456789";
        Y = "2345";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }


    @Test
    public void shortRightStringTest() {
        X = "2345";
        Y = "123456789";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void differentStringTest(){
        X = "123456789";
        Y = "abcdefghi";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void difLenStringTest(){
        X = "123456789";
        Y = "abcd";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }
}
