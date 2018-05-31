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
    public void sameKorStringTest(){
        X = "컴퓨터공학부";
        Y = "컴퓨터공학부";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertTrue(result);
    }

    @Test
    public void nomalCaseKorTest(){
        X = "컴퓨터공학";
        Y = "소프트웨어";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }


    @Test
    public void korShortRStrTest(){
        X = "컴퓨터공학";
        Y = "컴공";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void korShortLStrTest(){
        X = "컴공";
        Y = "컴퓨터공";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullLeftKorTest(){
        X = "가나다라마";
        Y = "";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullRightKorTest(){
        X = "가나다라마";
        Y = "";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void sameSymbolTest(){
        X = "!@#$%^&*";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertTrue(result);
    }

    @Test
    public void normalSymbolTest(){
        X = "!@#$%^&*";
        Y = "+}:{?><)";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullLeftSymbolTest(){
        X = "";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullRightSymbolTest(){
        X = "!@#$%^&*";
        Y = "";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void shortLeftSymbolTest(){
        X = "!@#$";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void shortRightSymbolTest(){
        X = "!@#$%^&*";
        Y = "!@#$";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void sameEngStringTest(){
        X = "CompEngScien";
        Y = "CompEngScien";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertTrue(result);
    }

    @Test
    public void nomalCaseEngTest(){
        X = "SoftWare";
        Y = "Computer";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }


    @Test
    public void engShortRStrTest(){
        X = "ComputerEngineering";
        Y = "CE";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void engShortLStrTest(){
        X = "CE";
        Y = "ComputerEngineering";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullLeftEngTest(){
        X = "ABCDE";
        Y = "";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullRightEngTest(){
        X = "ABCDE";
        Y = "";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void engKorStringTest(){
        X = "Computer";
        Y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void korEngStringTest(){
        X = "컴퓨터공학부전공";
        Y = "Computer";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenEngKorTest(){
        X = "Computer";
        Y = "컴퓨터";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenKorEngTest(){
        X = "컴퓨터";
        Y = "Computer";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void numKorStringTest(){
        X = "12345678";
        Y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void korNumStringTest(){
        X = "컴퓨터공학부전공";
        Y = "12345678";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenNumKorTest(){
        X = "12345678";
        Y = "컴퓨터";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenKorNumTest(){
        X = "컴퓨터";
        Y = "12345678";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void symKorStringTest(){
        X = "!@#$%^&*";
        Y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void korSymStringTest(){
        X = "컴퓨터공학부전공";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenSymKorTest(){
        X = "!@#$%^&*";
        Y = "컴퓨터";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenKorSymTest(){
        X = "컴퓨터";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void symNumStringTest(){
        X = "!@#$%^&*";
        Y = "12345678";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void numSymStringTest(){
        X = "12345678";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenSymNumTest(){
        X = "!@#$%^&*";
        Y = "123";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenNumSymTest(){
        X = "123";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void symEngStringTest(){
        X = "!@#$%^&*";
        Y = "ABCDEFGH";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void engSymStringTest(){
        X = "ABCDEFGH";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenSymEngTest(){
        X = "!@#$%^&*";
        Y = "ABC";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenEngSymTest(){
        X = "ABC";
        Y = "!@#$%^&*";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void numEngStringTest(){
        X = "12345678";
        Y = "ABCDEFGH";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void engNumStringTest(){
        X = "ABCDEFGH";
        Y = "12345678";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenNumEngTest(){
        X = "12345678";
        Y = "ABC";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenEngNumTest(){
        X = "ABC";
        Y = "12345678";
        boolean result = c.lineIsEqual(X, Y);
        Assert.assertFalse(result);

    }
}
