package utility;

import java.util.ArrayList;
import java.util.List;

public class IterableUtility {
    /**
     * Iterable 요소를 List로 변환합니다.
     * @param iterable 변환할 Iterable입니다.
     * @return 변환된 List입니다.
     */
    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    private IterableUtility() {

    }
}
