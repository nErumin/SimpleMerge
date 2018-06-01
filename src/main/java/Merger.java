import java.util.ArrayList;

public class Merger {

    Merger(){

    }

    public void mergeLeftRight(int index, ArrayList<String> left, ArrayList<String> right) {
        right.add(index, left.get(index));
    }

    public void mergeRightLeft(int index, ArrayList<String> left, ArrayList<String> right) {
        left.add(index, right.get(index));
    }

}
