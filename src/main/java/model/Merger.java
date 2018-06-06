package model;

import utility.IterableUtility;
import javafx.util.Pair;
import java.util.List;

public class Merger {

    public Merger() {

    }
    /**
     * left String to Right panel
     * @param index: Copy line
     * @param leftSplitter: Left panel data
     * @param rightSplitter: Right panel data
     */
    public Pair<List<String>,List<String>> mergeLeftRight(int index, Splittable leftSplitter, Splittable rightSplitter) {
        List<String> left = IterableUtility.toList(leftSplitter.lines());
        List<String> right = IterableUtility.toList(rightSplitter.lines());

        if (right.size() == 0 && left.size() == 0) {
        } else if (right.size() == 0 && left.size() != 0) {
            right.add(left.get(index));
        } else {
            right.remove(index);
            right.add(index, left.get(index));
        }
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

        if (right.size() == 0 && left.size() == 0) {
        } else if (left.size() == 0 && right.size() != 0) {
            left.add(right.get(index));
        } else {
            left.remove(index);
            left.add(index, right.get(index));
        }
        return new Pair<>(left,right);
    }
}
