import java.util.ArrayList;

public class Merger {

    Merger(){ }

    /**
     * Copy left String to Right panel
     * @param index
     * @param left
     * @param right
     */
    public void mergeLeftRight(int index, ArrayList<String> left, ArrayList<String> right) {
        right.add(index, left.get(index));
    }

    /**
     * Copy right String to left panel
     * @param index
     * @param left
     * @param right
     */
    public void mergeRightLeft(int index, ArrayList<String> left, ArrayList<String> right) {
        left.add(index, right.get(index));
    }

}
