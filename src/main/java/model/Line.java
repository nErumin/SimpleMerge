package model;

import utility.StreamUtility;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 텍스트의 한 문장을 나타내는 클래스입니다.
 */
public final class Line {
    private static final String SPLIT_REGEX = "\\S+\\s*";
    private static final String PURE_REGEX = "\\s+";
    private static final Pattern splitPattern = Pattern.compile(SPLIT_REGEX);

    private StringBuffer buffer;

    /**
     * 빈 내용을 가지는 문장을 생성합니다.
     */
    public Line() {
        this("");
    }

    /**
     * 초기 내용을 가지는 문장을 생성합니다.
     * @param text 초기 내용으로 설정할 문자열입니다.
     * @throws NullPointerException 매개변수가 null이면 발생합니다.
     */
    public Line(String text) {
        if (text == null) {
            throw new NullPointerException();
        }

        buffer = new StringBuffer(text);
    }

    /**
     * 이 문장에 존재하는 단어에 대한 열거자를 가져옵니다.
     * @return 문장에 존재하는 단어에 대한 열거자입니다.
     */
    public Iterable<String> words() {
        return StreamUtility.toIterable(wordStream());
    }

    private Stream<String> wordStream() {
        return spaceIncludingWordStream()
            .flatMap(word -> Arrays.stream(word.split(PURE_REGEX)));
    }

    /**
     * 이 문장에서 공백 문자를 포함하는 단어에 대한 열거자를 가져옵니다.
     * @return 문장에 존재하는 공백 문자 포함 단어들의 열거자입니다.
     */
    public Iterable<String> spaceIncludingWords() {
        return StreamUtility.toIterable(spaceIncludingWordStream());
    }

    private Stream<String> spaceIncludingWordStream() {
        final Matcher wordMatcher = splitPattern.matcher(toString());
        final List<String> words = new ArrayList<>();

        while (wordMatcher.find()) {
            words.add(wordMatcher.group());
        }

        return words.stream();
    }

    /**
     * 문장의 길이를 반환합니다.
     * @return 문장의 길이입니다.
     */
    public int length() {
        return buffer.length();
    }

    /**
     * 문장 전체를 문자열로 반환합니다.
     * @return 문장 전체의 정보입니다.
     */
    @Override
    public String toString() {
        return buffer.toString();
    }
}
