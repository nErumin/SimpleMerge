package model;

import javafx.util.Pair;
import model.Merger;
import model.Splittable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;
import java.util.ArrayList;
import java.util.List;

public class MergerTest {

    private Merger merger = new Merger();
    private ArrayList<String>
        left = new ArrayList<String>(),
        right = new ArrayList<String>();
    private Pair<List<String>, List<String>> actual;
    private Splittable splitLeftMock, splitRightMock;
    private Pair<List<String>, List<String>> twoPanel;

    @Before
    public void baseSetting(){
        splitLeftMock = EasyMock.createMock(Splittable.class);
        splitRightMock = EasyMock.createMock(Splittable.class);
    }

    @Test
    public void nullToNullMergeTest() {

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = merger.mergeLeftRight(0, splitLeftMock, splitRightMock);

    }

    @Test
    public void leftNullMergeTest() {
        right.add("Hello world!");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = merger.mergeRightLeft(0, splitLeftMock, splitRightMock);
        Assert.assertEquals(right.get(0), twoPanel.getKey().get(0));
    }

    @Test
    public void rightNullMergeTest() {
        left.add("Hello world!");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = merger.mergeLeftRight(0, splitLeftMock, splitRightMock);
        Assert.assertEquals(left.get(0), twoPanel.getValue().get(0));
    }

    @Test
    public void lefStringMergeTest() {
        left.add("Hello world!");
        right.add("");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = merger.mergeLeftRight(0, splitLeftMock, splitRightMock);
        Assert.assertEquals(left.get(0), twoPanel.getValue().get(0));
    }

    @Test
    public void rightStringMergeTest() {
        left.add("");
        right.add("Hello world!");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = merger.mergeRightLeft(0, splitLeftMock, splitRightMock);
        Assert.assertEquals(right.get(0), twoPanel.getKey().get(0));
    }

    @Test
    public void lefStringReplaceTest() {
        left.add("Hello world!");
        right.add("헬로우 월드!");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = merger.mergeRightLeft(0, splitLeftMock, splitRightMock);
        Assert.assertEquals(right.get(0), twoPanel.getKey().get(0));
    }

    @Test
    public void rightStringReplaceTest() {
        left.add("Hello world!");
        right.add("헬로우 월드!");

        EasyMock.expect(splitLeftMock.lines())
            .andReturn(left)
            .anyTimes();
        EasyMock.expect(splitRightMock.lines())
            .andReturn(right)
            .anyTimes();

        EasyMock.replay(splitRightMock);
        EasyMock.replay(splitLeftMock);

        twoPanel = merger.mergeLeftRight(0, splitLeftMock, splitRightMock);
        Assert.assertEquals(left.get(0), twoPanel.getValue().get(0));
    }

}
