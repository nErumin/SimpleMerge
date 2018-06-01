import java.util.ArrayList;

public class Merger {

    Merger(){ }

    /**
     * Copy left String to Right panel
     * @param index: Copy line
     * @param left: Left panel data
     * @param right: Right panel data
     */
    public void mergeLeftRight(int index, ArrayList<String> left, ArrayList<String> right) {
        right.add(index, left.get(index));
    }

    /**
     * Copy right String to left panel
     * @param index: Copy line
     * @param left: Left panel data
     * @param right: Right panel data
     */
    public void mergeRightLeft(int index, ArrayList<String> left, ArrayList<String> right) {
        left.add(index, right.get(index));
    }
}
