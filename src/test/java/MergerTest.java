import javafx.util.Pair;
import model.Splittable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @Before
    public void baseSetting(){
        splitLeftMock = EasyMock.createMock(Splittable.class);
        splitRightMock = EasyMock.createMock(Splittable.class);
    }

    @Test
    public void nullToNullMergeTest() {
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
