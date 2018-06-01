import java.util.ArrayList;

public class Merger {

    Merger(){

    }

    public void mergeLeft2Right(int index, ArrayList<String> left, ArrayList<String> right) {
        right.add(index, left.get(index));
    }

    public void mergeRight2Left(int index, ArrayList<String> left, ArrayList<String> right) {
        left.add(index, right.get(index));
    }

}
