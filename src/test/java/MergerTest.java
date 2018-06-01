import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class MergerTest {

    private Merger merger = new Merger();
    private ArrayList<String>
        left = new ArrayList<String>(),
        right = new ArrayList<String>();

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
}
