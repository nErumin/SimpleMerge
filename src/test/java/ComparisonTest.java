import javafx.util.Pair;
import model.Splittable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

public class ComparisonTest {

    private Comparison c = new Comparison();
    private Pair<List<String>, List<String>> twoPanel;
    private String x, y;
    private ArrayList<String>
        left = new ArrayList<String>(),
        right = new ArrayList<String>();

    private Splittable splitLeftMock, splitRightMock;
    private Splittable txt = EasyMock.createMock(Splittable.class);


    @Before
    public void baseSetting(){
        splitLeftMock = EasyMock.createMock(Splittable.class);
        splitRightMock = EasyMock.createMock(Splittable.class);
    }

    /** lineIsEqual Class Testing
     *
     * Case 1: Same length number
     * Left: String
     * Right: String
     */
    @Test
    public void sameStringTest() {
        x = "123456789";
        y = "123456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    /**
     * Case 2: Same length, but different contents
     * Left: String
     * Right: String
     */
    @Test
    public void nomalCaseTest() {
        x = "123456789";
        y = "321456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 3
     * Left: null,
     * Right: String
     */
    @Test
    public void nullLeftStringTest() {
        x = "";
        y = "321456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 4
     * Left: Character,
     * Right: null
     */
    @Test
    public void nullRightStringTest() {
        x = "123456789";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Caes 5
     * Left: null
     * Right: null
     */
    @Test
    public void nullStringTest() {
        x = "";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    /**
     * Case 6: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void shortLeftStringTest() {
        x = "123456789";
        y = "2345";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 7: Different length
     * LEft: String
     * Right: String
     */
    @Test
    public void shortRightStringTest() {
        x = "2345";
        y = "123456789";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** Korean
     * Case 8
     * Left: String
     * Right: String
     */
    @Test
    public void sameKorStringTest() {
        x = "컴퓨터공학부";
        y = "컴퓨터공학부";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    /**
     * Case 9: Different contents
     * Left: String
     * Right: String
     */
    @Test
    public void nomalCaseKorTest() {
        x = "컴퓨터공학";
        y = "소프트웨어";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 10: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void korShortRStrTest() {
        x = "컴퓨터공학";
        y = "컴공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 11: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void korShortLStrTest() {
        x = "컴공";
        y = "컴퓨터공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 12
     * Left: null
     * Right: String
     */
    @Test
    public void nullLeftKorTest() {
        x = "가나다라마";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 13
     * Left: String
     * Right: null
     */
    @Test
    public void nullRightKorTest() {
        x = "가나다라마";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 14
     * Left: Symbol
     * Right: Symbol
     */
    @Test
    public void sameSymbolTest() {
        x = "!@#$%^&*";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    /**
     * Case 15: Different Symbol
     * Left: String
     * Right: String
     */
    @Test
    public void normalSymbolTest() {
        x = "!@#$%^&*";
        y = "+}:{?><)";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 16
     * Left: null
     * Right: String
     */
    @Test
    public void nullLeftSymbolTest() {
        x = "";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 17
     * Left: String
     * Right: null
     */
    @Test
    public void nullRightSymbolTest() {
        x = "!@#$%^&*";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 18: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void shortLeftSymbolTest() {
        x = "!@#$";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 19: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void shortRightSymbolTest() {
        x = "!@#$%^&*";
        y = "!@#$";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** English
     * Case 20
     * Left: String
     * Right: String
     */
    @Test
    public void sameEngStringTest() {
        x = "CompEngScien";
        y = "CompEngScien";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertTrue(result);
    }

    /**
     * Case 21
     * Left: String
     * Right: String
     */
    @Test
    public void nomalCaseEngTest() {
        x = "SoftWare";
        y = "Computer";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 21: Different legnth
     * Left: String
     * Right: String
     */
    @Test
    public void engShortRStrTest() {
        x = "ComputerEngineering";
        y = "CE";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 22: Different legnth
     * Left: String
     * Right: String
     */
    @Test
    public void engShortLStrTest() {
        x = "CE";
        y = "ComputerEngineering";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 23
     * Left: String
     * Right: null
     */
    @Test
    public void nullLeftEngTest() {
        x = "ABCDE";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 24
     * Left: String
     * Right: null
     */
    @Test
    public void nullRightEngTest() {
        x = "ABCDE";
        y = "";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** English - Korean
     * Case 25
     * Left: String
     * Right: String
     */
    @Test
    public void engKorStringTest() {
        x = "Computer";
        y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 26
     * Left: String
     * Right: String
     */
    @Test
    public void korEngStringTest() {
        x = "컴퓨터공학부전공";
        y = "Computer";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 27: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenEngKorTest() {
        x = "Computer";
        y = "컴퓨터";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 28: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenKorEngTest() {
        x = "컴퓨터";
        y = "Computer";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** Number - Korean
     * Case 29
     * Left: String
     * Right: String
     */
    @Test
    public void numKorStringTest() {
        x = "12345678";
        y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 30
     * Left: String
     * Right: String
     */
    @Test
    public void korNumStringTest() {
        x = "컴퓨터공학부전공";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 31: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenNumKorTest() {
        x = "12345678";
        y = "컴퓨터";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 32: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenKorNumTest() {
        x = "컴퓨터";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** Symbol - Korean
     * Case 33
     * Left: String
     * Right: String
     */
    @Test
    public void symKorStringTest() {
        x = "!@#$%^&*";
        y = "컴퓨터공학부전공";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 34
     * Left: String
     * Right: String
     */
    @Test
    public void korSymStringTest() {
        x = "컴퓨터공학부전공";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 35: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenSymKorTest() {
        x = "!@#$%^&*";
        y = "컴퓨터";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 36: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenKorSymTest() {
        x = "컴퓨터";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 37: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void symNumStringTest() {
        x = "!@#$%^&*";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** Number - Symbol
     * Case 38
     * Left: String
     * Right: String
     */
    @Test
    public void numSymStringTest() {
        x = "12345678";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 39: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenSymNumTest() {
        x = "!@#$%^&*";
        y = "123";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 40: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenNumSymTest() {
        x = "123";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** Symbol - English
     * Case 41
     * Left: String
     * Right: String
     */
    @Test
    public void symEngStringTest() {
        x = "!@#$%^&*";
        y = "ABCDEFGH";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 42
     * Left: String
     * Right: String
     */
    @Test
    public void engSymStringTest() {
        x = "ABCDEFGH";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 43: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenSymEngTest() {
        x = "!@#$%^&*";
        y = "ABC";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 44: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenEngSymTest() {
        x = "ABC";
        y = "!@#$%^&*";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 45: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void numEngStringTest() {
        x = "12345678";
        y = "ABCDEFGH";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** English - Number
     * Case 46
     * Left: String
     * Right: String
     */
    @Test
    public void engNumStringTest() {
        x = "ABCDEFGH";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 47: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenNumEngTest() {
        x = "12345678";
        y = "ABC";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /**
     * Case 48: Different length
     * Left: String
     * Right: String
     */
    @Test
    public void diffLenEngNumTest() {
        x = "ABC";
        y = "12345678";
        boolean result = c.lineIsEqual(x, y);
        Assert.assertFalse(result);
    }

    /** panelFix Method TestCase
     * Smae contents panel
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


        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left);
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right);

        EasyMock.replay(splitLeftMock);
        EasyMock.replay(splitRightMock);

    }

    /**
     * Left panel: null
     */
    @Test
    public void leftNullPanelTest() {

        left.add("Hello world!");
        left.add("");
        left.add("안녕하세요.");
        left.add("");
        left.add("123456");
        left.add("");
        left.add("!@#$%^");
    }

    /**
     * Right panel: null
     */
    @Test
    public void rightNullPanelTest() {
        right.add("Hello world!");
        right.add("");
        right.add("안녕하세요.");
        right.add("");
        right.add("123456");
        right.add("");
        right.add("!@#$%^");
    }

    /**
     * Both panel: null
     */
    @Test
    public void bothNullPanelTest() {

    }

    /**
     * Different two panel
     */
    @Test
    public void difPanelTest() {
        left.add("안녕하세요.");
        left.add("");
        left.add("Hello world!");
        left.add("");

        right.add("Hello world!");
        right.add("");
        right.add("안녕하세요.");
        right.add("");

    }
}
