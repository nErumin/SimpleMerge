package utility;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StreamUtility {
    public static <T> Iterable<T> toIterable(Stream<T> stream) {
        final List<T> list = stream.collect(Collectors.toList());
        return Collections.unmodifiableCollection(list);
    }

    private StreamUtility() {

    }
}
