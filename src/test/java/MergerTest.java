import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class MergerTest {

    private Merger merger = new Merger();
    private ArrayList<String>
        left = new ArrayList<String>(),
        right = new ArrayList<String>();

    @Test
    public void nuㅣlToNullMergeTest() {
        left.add("");
        right.add("");
        merger.mergeLeftRight(0, left, right);
        Assert.assertEquals("", right.get(0));
        Assert.assertEquals("", left.get(0));
    }

    @Test
    public void leftNullMergeTest() {
        left.add("");
        right.add("Hello world!");
        merger.mergeLeftRight(0, left, right);
        Assert.assertEquals("", right.get(0));
    }

    @Test
    public void rightNullMergeTest() {
        left.add("Hello world!");
        right.add("");
        merger.mergeRightLeft(0, left, right);
        Assert.assertEquals("", left.get(0));
    }

    @Test
    public void lefStringMergeTest() {
        left.add("Hello world!");
        right.add("");
        merger.mergeLeftRight(0, left, right);
        Assert.assertEquals("Hello world!", right.get(0));
    }

    @Test
    public void rightStringMergeTest() {
        left.add("");
        right.add("Hello world!");
        merger.mergeRightLeft(0, left, right);
        Assert.assertEquals("Hello world!", left.get(0));
    }

    @Test
    public void lefStringReplaceTest() {
        left.add("Hello world!");
        right.add("헬로우 월드!");
        merger.mergeLeftRight(0, left, right);
        Assert.assertEquals("Hello world!", right.get(0));
    }

    @Test
    public void rightStringReplaceTest() {
        left.add("Hello world!");
        right.add("헬로우 월드!");
        merger.mergeLeftRight(0, left, right);
        Assert.assertEquals("Hello world!", right.get(0));
    }

}
