package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IterableUtility {
    /**
     * Iterable을 Stream으로 변환합니다.
     * @param iterable 변환할 Iterable입니다.
     * @return 생성된 Stream입니다.
     */
    public static <T> Stream<T> toStream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

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

    /**
     * Iterable 요소에서 조건을 만족하는 요소를 골라냅니다.
     * @param iterable 요소들이 들어있는 Iterable입니다.
     * @param condition 요소가 만족해야 하는 조건을 나타냅니다.
     * @return 조건을 만족하는 요소로만 이루어진 Iterable입니다.
     */
    public static <T> Iterable<T> filter(Iterable<T> iterable,
                                         Predicate<T> condition) {
        return StreamUtility.toIterable(
            toStream(iterable).filter(condition)
        );
    }

    private IterableUtility() {

    }
}
