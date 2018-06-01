import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ComparisonTest {

    private Comparison c = new Comparison();
    private String x, y;
    private ArrayList<String>
        left = new ArrayList<String>(),
        right = new ArrayList<String>(),
        array;

    /**
     * Case 1: Same length input String
     *
     */
    @Test
    public void sameStringTest() {
        x = "123456789";
        y = "123456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }


    @Test
    public void nomalCaseTest() {
        x = "123456789";
        y = "321456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }


    @Test
    public void nullLeftStringTest() {
        x = "";
        y = "321456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }


    @Test
    public void nullRightStringTest() {
        x = "123456789";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }


    @Test
    public void nullStringTest() {
        x = "";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }


    @Test
    public void shortLeftStringTest() {
        x = "123456789";
        y = "2345";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }


    @Test
    public void shortRightStringTest() {
        x = "2345";
        y = "123456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void sameKorStringTest() {
        x = "컴퓨터공학부";
        y = "컴퓨터공학부";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    @Test
    public void nomalCaseKorTest() {
        x = "컴퓨터공학";
        y = "소프트웨어";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }


    @Test
    public void korShortRStrTest() {
        x = "컴퓨터공학";
        y = "컴공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void korShortLStrTest() {
        x = "컴공";
        y = "컴퓨터공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullLeftKorTest() {
        x = "가나다라마";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullRightKorTest() {
        x = "가나다라마";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void sameSymbolTest() {
        x = "!@#$%^&*";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    @Test
    public void normalSymbolTest() {
        x = "!@#$%^&*";
        y = "+}:{?><)";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullLeftSymbolTest() {
        x = "";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullRightSymbolTest() {
        x = "!@#$%^&*";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void shortLeftSymbolTest() {
        x = "!@#$";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void shortRightSymbolTest() {
        x = "!@#$%^&*";
        y = "!@#$";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void sameEngStringTest() {
        x = "CompEngScien";
        y = "CompEngScien";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    @Test
    public void nomalCaseEngTest() {
        x = "SoftWare";
        y = "Computer";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }


    @Test
    public void engShortRStrTest() {
        x = "ComputerEngineering";
        y = "CE";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void engShortLStrTest() {
        x = "CE";
        y = "ComputerEngineering";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullLeftEngTest() {
        x = "ABCDE";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void nullRightEngTest() {
        x = "ABCDE";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void engKorStringTest() {
        x = "Computer";
        y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void korEngStringTest() {
        x = "컴퓨터공학부전공";
        y = "Computer";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenEngKorTest() {
        x = "Computer";
        y = "컴퓨터";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenKorEngTest() {
        x = "컴퓨터";
        y = "Computer";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void numKorStringTest() {
        x = "12345678";
        y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void korNumStringTest() {
        x = "컴퓨터공학부전공";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenNumKorTest() {
        x = "12345678";
        y = "컴퓨터";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenKorNumTest() {
        x = "컴퓨터";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void symKorStringTest() {
        x = "!@#$%^&*";
        y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void korSymStringTest() {
        x = "컴퓨터공학부전공";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenSymKorTest() {
        x = "!@#$%^&*";
        y = "컴퓨터";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenKorSymTest() {
        x = "컴퓨터";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void symNumStringTest() {
        x = "!@#$%^&*";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void numSymStringTest() {
        x = "12345678";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenSymNumTest() {
        x = "!@#$%^&*";
        y = "123";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenNumSymTest() {
        x = "123";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void symEngStringTest() {
        x = "!@#$%^&*";
        y = "ABCDEFGH";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void engSymStringTest() {
        x = "ABCDEFGH";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenSymEngTest() {
        x = "!@#$%^&*";
        y = "ABC";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenEngSymTest() {
        x = "ABC";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void numEngStringTest() {
        x = "12345678";
        y = "ABCDEFGH";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void engNumStringTest() {
        x = "ABCDEFGH";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenNumEngTest() {
        x = "12345678";
        y = "ABC";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    @Test
    public void diffLenEngNumTest() {
        x = "ABC";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * panelFix Method TestCase
     */
    @Test
    public void samePanelTest() {
        left.add("Hello world!");
        left.add("");
        left.add("안녕하세요.");
        left.add("");
        left.add("123456");
        left.add("");
        left.add("!@#$%^");

        right.add("Hello world!");
        right.add("");
        right.add("안녕하세요.");
        right.add("");
        right.add("123456");
        right.add("");
        right.add("!@#$%^");

        array = c.panelFix(left, right);
        Assert.assertEquals(0, array.size());
    }

    @Test
    public void leftNullPanelTest() {
        left.add("Hello world!");
        left.add("");
        left.add("안녕하세요.");
        left.add("");
        left.add("123456");
        left.add("");
        left.add("!@#$%^");

        array = c.panelFix(left, right);
        Assert.assertEquals(7, array.size());
    }

    @Test
    public void rightNullPanelTest() {
        right.add("Hello world!");
        right.add("");
        right.add("안녕하세요.");
        right.add("");
        right.add("123456");
        right.add("");
        right.add("!@#$%^");

        array = c.panelFix(left, right);
        Assert.assertEquals(7, array.size());
    }

    @Test
    public void bothNullPanelTest() {
        array = c.panelFix(left, right);
        Assert.assertEquals(0, array.size());
    }

    @Test
    public void Test() {
        left.add("안녕하세요.");
        left.add("");
        left.add("Hello world!");
        left.add("");

        right.add("Hello world!");
        right.add("");
        right.add("안녕하세요.");
        right.add("");

        array = c.panelFix(left, right);
        Assert.assertEquals(2, array.size());
    }
}
