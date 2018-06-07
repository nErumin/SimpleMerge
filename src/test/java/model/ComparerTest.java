package model;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

public class ComparerTest {

    private Comparer c = new Comparer();
    private Pair<List<String>, List<String>> twoPanel;
    private String x, y;
    private ArrayList<String>
        left = new ArrayList<String>(),
        right = new ArrayList<String>();
    private List<String> actual = new ArrayList<String>();
    private Splittable splitLeftMock, splitRightMock;


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
     * Case 1: Same contents panel
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
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertEquals(twoPanel.getKey(), twoPanel.getValue());
    }

    /**
     * Case 2: Right panel: null
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

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertEquals(left, twoPanel.getKey());
    }

    /**
     * Case 3: Left panel: null
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

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);
        Assert.assertEquals(right, twoPanel.getValue());
    }

    /**
     * Case 4: Both panel: null
     */
    @Test
    public void bothNullPanelTest() {

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertEquals(0,twoPanel.getKey().size());
        Assert.assertEquals(0,twoPanel.getValue().size());
    }

    /**
     * Case 5: Different two panel
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

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        left.add(0, "");
        left.add(0, "");
        right.add("");
        right.add("");

        Assert.assertEquals(left, twoPanel.getKey());
        Assert.assertEquals(right, twoPanel.getValue());
    }

    /** findDifLine Method TestCase
     * Case 1: Both panel: null
     */
    @Test
    public void nullNullPanelTest() {

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(0, actual.size());
    }

    /**
     * Case 2: Left panel: null
     */
    @Test
    public void nullPanelTest() {
        right.add("Hello world!");
        right.add("");
        right.add("안녕하세요.");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(0, actual.size());
    }

    /**
     * Case 3: Right panel: null
     */
    @Test
    public void panelNullTest() {
        left.add("안녕하세요.");
        left.add("");
        left.add("Hello world!");
        left.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(0, actual.size());
    }

    /**
     * Case 4: Both panel: empty
     */
    @Test
    public void emptyEmptyTest() {
        left.add("");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(0, actual.size());
    }

    /**
     * Case 5: Left panel: empty
     */
    @Test
    public void emptyPanelTest() {
        left.add("");
        left.add("");
        left.add("");
        left.add("");

        right.add("안녕하세요.");
        right.add("");
        right.add("Hello world!");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(2, actual.size());
    }

    /**
     * Case 6: Right panel: empty
     */
    @Test
    public void panelEmptyTest() {
        left.add("안녕하세요.");
        left.add("");
        left.add("Hello world!");
        left.add("");

        right.add("");
        right.add("");
        right.add("");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(2, actual.size());
    }

    /**
     * Case 7: Same panel
     */
    @Test
    public void equalPanelTest() {
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
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(0, actual.size());
    }

    /**
     * Case8: Different panel
     */
    @Test
    public void panelPenlTest() {
        left.add("안녕하세요.");
        left.add("");
        left.add("Hello world!");
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
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        actual = c.findDifLine(splitLeftMock, splitRightMock);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void professorSample1() {
        left.add("1");
        left.add("2");
        left.add("3");
        left.add("4");
        left.add("5");
        left.add("6");
        left.add("7");
        left.add("8");
        left.add("9");
        left.add("10");

        right.add("1");
        right.add("2");
        right.add("3");
        right.add("4");
        right.add("5");
        right.add("6");
        right.add("7");
        right.add("8");
        right.add("9");
        right.add("10");


        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertEquals(twoPanel.getKey(), twoPanel.getValue());
    }

    @Test
    public void professorSample2() {
        left.add("1");
        left.add("");
        left.add("3");
        left.add("4");
        left.add("5");
        left.add("6");
        left.add("7");
        left.add("8");
        left.add("9");
        left.add("10");

        right.add("1");
        right.add("2");
        right.add("3");
        right.add("4");
        right.add("5");
        right.add("6");
        right.add("7");
        right.add("8");
        right.add("9");
        right.add("10");


        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertNotEquals (twoPanel.getKey().get(1), twoPanel.getValue().get(1));
    }

    @Test
    public void professorSample8() {
        left.add("");
        left.add("");
        left.add("mainprog illegal;");
        left.add("");
        left.add("");
        left.add("");
        left.add("procedure proc1 (a : integer; b,c : integer);");
        left.add("var d,e:string;");
        left.add("begin");
        left.add("\ta := 10;");
        left.add("\tb := 20;");
        left.add("\tc := 30;");
        left.add("end");
        left.add("");
        left.add("function max (a: integer; b: integer) : integer; ");
        left.add("");
        left.add("var r,y:float;");
        left.add("");
        left.add("");
        left.add("begin");
        left.add("\tif a >= b then return a; ");
        left.add("\telse return b;");
        left.add("end");
        left.add("");
        left.add("function func1(a,b : integer) : float;");
        left.add("");
        left.add("var fval : float;");
        left.add("begin");
        left.add("");
        left.add("\treturn a;");
        left.add("");
        left.add("end");
        left.add("");
        left.add("");
        left.add("begin");
        left.add("");
        left.add("   proc1(10,20.0,30.0);");
        left.add("   proc1(10,20);");
        left.add("");
        left.add("");
        left.add("");
        left.add("   a := 33;");
        left.add("   print a;");
        left.add("   print max(10,2.4);");
        left.add("   print func1(3,4);");
        left.add("");
        left.add("end. ");
        left.add("");

        right.add("");
        right.add("");
        right.add("");
        right.add("");
        right.add("");
        right.add("mainprog illegal;");
        right.add("");
        right.add("procedure proc1 (a : integer; b,c : integer);");
        right.add("var d,e:string;");
        right.add("begin");
        right.add("\ta := 10;");
        right.add("\tb := 20;");
        right.add("\tc := 30;");
        right.add("end");
        right.add("");
        right.add("function max (a: integer; b: integer) : integer; ");
        right.add("var r,y:float;");
        right.add("begin");
        right.add("\tif a >= b then return a; ");
        right.add("\telse return b;");
        right.add("end");
        right.add("");
        right.add("function func1(a,b : integer) : float;");
        right.add("var fval : float;");
        right.add("begin");
        right.add("\treturn a;");
        right.add("end");
        right.add("");
        right.add("");
        right.add("begin");
        right.add("");
        right.add("   proc1(10,20.0,30.0);");
        right.add("   proc1(10,20);");
        right.add("");
        right.add("   a := 33;");
        right.add("   print a;");
        right.add("   print max(10,2.4);");
        right.add("   print func1(3,4);");
        right.add("");
        right.add("end. ");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        for(int i = 0; i < right.size(); i++) {
            Assert.assertEquals(twoPanel.getKey().get(i), twoPanel.getValue().get(i));
        }
    }

    @Test
    public void professorSample9() {
        left.add("A");
        left.add("AA");
        left.add("AAA");
        left.add(" AAAA");
        left.add("AAAAA");
        left.add("  AAAAAA");
        left.add("AAAAAAA");
        left.add("AAAAAA");
        left.add("AAAAA");
        left.add("AAAA");
        left.add("AAA");
        left.add("AA");
        left.add("A");
        left.add("A");
        left.add("");
        left.add("");
        left.add("");
        left.add("");
        left.add("A");
        left.add("A");
        left.add("");
        left.add("");
        left.add("A");
        left.add("");

        right.add("A");
        right.add("AA");
        right.add("AAA");
        right.add("AAAA");
        right.add("AAAAA");
        right.add("AAAAAA");
        right.add("AAAAAAA");
        right.add("AAAAAA");
        right.add("AAAAA");
        right.add("AAAA");
        right.add("AAA");
        right.add("AA");
        right.add("A");
        right.add("A");
        right.add("A");
        right.add("A");
        right.add("A");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        for(int i = 0; i < right.size(); i++) {
            if(i == 3 || i == 5) {
                Assert.assertNotEquals(twoPanel.getKey().get(i), twoPanel.getValue().get(i));
            } else {
                Assert.assertEquals(twoPanel.getKey().get(i), twoPanel.getValue().get(i));
            }
        }
    }


    @Test
    public void professorSample10() {
        left.add("1");
        left.add("X");
        left.add("X");
        left.add("X");
        left.add("2");
        left.add("3");
        left.add("4");
        left.add("5");
        left.add("6");
        left.add("7");
        left.add("8");
        left.add("9");
        left.add("10");

        right.add("1");
        right.add("2");
        right.add("3");
        right.add("4");
        right.add("5");
        right.add("6");
        right.add("7");
        right.add("8");
        right.add("9");
        right.add("10");


        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertNotEquals(twoPanel.getKey().get(1), twoPanel.getValue().get(1));
        Assert.assertNotEquals(twoPanel.getKey().get(2), twoPanel.getValue().get(2));
        Assert.assertNotEquals(twoPanel.getKey().get(3), twoPanel.getValue().get(3));
    }


    @Test
    public void professorSample11() {
        left.add("");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertEquals(twoPanel.getKey(), twoPanel.getValue());
    }

    @Test
    public void professorSample14() {
        left.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        left.add("X                                               X");
        left.add("X XXXXX X  X XXXX    XXXX X   X XXXX    XX      X");
        left.add("X   X   X  X X       X    XX  X X   X   XX      X");
        left.add("X   X   XXXX XXXX    XXXX X X X X   X   XX      X");
        left.add("X   X   X  X X       X    X  XX X   X           X");
        left.add("X   X   X  X XXXX    XXXX X   X XXXX    XX      X");
        left.add("X                                               X");
        left.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        right.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        right.add("X                                               X");
        right.add("X XXXXX X  X XXXX    XXXX X   X XXXX            X");
        right.add("X   X   X  X X       X    XX  X X   X           X");
        right.add("X   X   XXXX XXXX    XXXX X X X X   X           X");
        right.add("X   X   X  X X       X    X  XX X   X           X");
        right.add("X   X   X  X XXXX    XXXX X   X XXXX            X");
        right.add("X                                               X");
        right.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");


        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = c.panelFix(splitLeftMock, splitRightMock);

        Assert.assertNotEquals (twoPanel.getKey().get(2), twoPanel.getValue().get(2));
        Assert.assertNotEquals (twoPanel.getKey().get(3), twoPanel.getValue().get(3));
        Assert.assertNotEquals (twoPanel.getKey().get(4), twoPanel.getValue().get(4));
        Assert.assertNotEquals (twoPanel.getKey().get(6), twoPanel.getValue().get(6));
    }
}
