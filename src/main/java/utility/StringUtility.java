package utility;

public class StringUtility {
    /**
     * 빈 문자열을 나타냅니다.
     */
    public static final String EMPTY_STRING = "";

    /**
     * 줄 분리자를 나타냅니다.
     */
    public static final String LINE_SEPARATOR = "\n";

    /**
     * 컬렉션 속 String을 하나의 String으로 합칩니다.
     * @param iterable String이 들어있는 Iterable
     * @return 합쳐진 String입니다.
     */
    public static String compact(Iterable<String> iterable) {
        StringBuilder builder = new StringBuilder();
        for (String text : iterable) {
            builder.append(text);
        }

        return builder.toString();
    }

    private StringUtility() {

    }
}
