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
    }

    @Test
    public void leftNullMergeTest() {
        left.add("");
        right.add("Hello world!");

    }

    @Test
    public void rightNullMergeTest() {
        left.add("Hello world!");
        right.add("");

    }

    @Test
    public void lefStringMergeTest() {
        left.add("Hello world!");
        right.add("");

    }

    @Test
    public void rightStringMergeTest() {
        left.add("");
        right.add("Hello world!");

    }

    @Test
    public void lefStringReplaceTest() {
        left.add("Hello world!");
        right.add("헬로우 월드!");

    }

    @Test
    public void rightStringReplaceTest() {
        left.add("Hello world!");
        right.add("헬로우 월드!");

    }

}
