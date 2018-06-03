import model.Splittable;
import utility.IterableUtility;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Merger {

    Merger(){ }

    /**
     * left String to Right panel
     * @param index: Copy line
     * @param leftSplitter: Left panel data
     * @param rightSplitter: Right panel data
     */
    public Pair<List<String>,List<String>> mergeLeftRight(int index, Splittable leftSplitter, Splittable rightSplitter) {
        List<String> left = IterableUtility.toList(leftSplitter.lines());
        List<String> right = IterableUtility.toList(rightSplitter.lines());
        right.add(index, left.get(index));
        return new Pair<>(left,right);
    }

    /**
     * Copy right String to left panel
     * @param index: Copy line
     * @param leftSplitter: Left panel data
     * @param rightSplitter: Right panel data
     */
    public Pair<List<String>,List<String>> mergeRightLeft(int index, Splittable leftSplitter, Splittable rightSplitter) {
        List<String> left = IterableUtility.toList(leftSplitter.lines());
        List<String> right = IterableUtility.toList(rightSplitter.lines());
        left.add(index, right.get(index));
        return new Pair<>(left,right);
    }
}
