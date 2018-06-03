import model.Splittable;
import utility.IterableUtility;

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
    public void mergeLeftRight(int index, Splittable leftSplitter, Splittable rightSplitter) {
        List<String> left = IterableUtility.toList(leftSplitter.lines());
        List<String> right = IterableUtility.toList(rightSplitter.lines());
        right.add(index, left.get(index));
    }

    /**
     * Copy right String to left panel
     * @param index: Copy line
     * @param leftSplitter: Left panel data
     * @param rightSplitter: Right panel data
     */
    public void mergeRightLeft(int index, Splittable leftSplitter, Splittable rightSplitter) {
        List<String> left = IterableUtility.toList(leftSplitter.lines());
        List<String> right = IterableUtility.toList(rightSplitter.lines());
        left.add(index, right.get(index));
    }
}
