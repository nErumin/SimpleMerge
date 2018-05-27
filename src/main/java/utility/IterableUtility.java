package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IterableUtility {
    public static <T> Stream<T> toStream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <T> Iterable<T> filter(Iterable<T> iterable,
                                         Predicate<T> condition) {
        return StreamUtility.toIterable(
            toStream(iterable).filter(condition)
        );
    }

    private IterableUtility() {

    }
}
