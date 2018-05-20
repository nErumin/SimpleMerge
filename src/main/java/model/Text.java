package model;

import utility.StreamUtility;
import utility.StringUtility;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 텍스트의 한 문장을 나타내는 클래스입니다.
 */
public final class Text {
    private static final String WORD_SPLIT_REGEX = "\\s*\\S+\\s*";
    private static final String EXTRACT_PURE_WORD_REGEX = "\\s+";
    private static final String LINE_SPLIT_REGEX =
        String.format("%s*[^%s]+%s*", StringUtility.LINE_SEPARATOR
                                    , StringUtility.LINE_SEPARATOR
                                    , StringUtility.LINE_SEPARATOR);
    private static final String EXTRACT_PURE_LINE_REGEX =
        String.format("[%s]+", StringUtility.LINE_SEPARATOR);
    private static final Pattern wordSplitPattern =
        Pattern.compile(WORD_SPLIT_REGEX);
    private static final Pattern lineSplitPattern =
        Pattern.compile(LINE_SPLIT_REGEX);

    private StringBuffer buffer;

    /**
     * 빈 내용을 가지는 문장을 생성합니다.
     */
    public Text() {
        this(StringUtility.EMPTY_STRING);
    }

    /**
     * 초기 내용을 가지는 문장을 생성합니다.
     * @param text 초기 내용으로 설정할 문자열입니다.
     * @throws NullPointerException 매개변수가 null이면 발생합니다.
     */
    public Text(String text) {
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
            .flatMap(word -> Arrays.stream(word.split(EXTRACT_PURE_WORD_REGEX)))
            .filter(word -> !word.equals(StringUtility.EMPTY_STRING));
    }

    /**
     * 이 문장에서 공백 문자를 포함하는 단어에 대한 열거자를 가져옵니다.
     * @return 문장에 존재하는 공백 문자 포함 단어들의 열거자입니다.
     */
    public Iterable<String> spaceIncludingWords() {
        return StreamUtility.toIterable(spaceIncludingWordStream());
    }

    private Stream<String> spaceIncludingWordStream() {
        final Matcher wordMatcher = wordSplitPattern.matcher(toString());
        final List<String> words = new ArrayList<>();

        while (wordMatcher.find()) {
            words.add(wordMatcher.group());
        }

        return words.stream();
    }


    /**
     * 문장의 특정 위치에 문자열을 삽입합니다.
     * @param insertionPos 문자열을 삽입할 위치입니다.
     * @param insertedString 삽입할 문자열입니다.
     * @throws StringIndexOutOfBoundsException 위치가 유효하지 않으면 발생합니다.
     * @throws NullPointerException 문자열이 null이면 발생합니다.
     */
    public void insert(int insertionPos, String insertedString) {
        if (insertionPos < 0 || insertionPos >= length()) {
            throw new StringIndexOutOfBoundsException();
        }

        if (insertedString == null) {
            throw new NullPointerException();
        }

        buffer.insert(insertionPos, insertedString);
    }

    /**
     * 문장의 특정 영역을 지웁니다.
     * @param startIndex 삭제를 시작할 위치입니다.
     * @param length 삭제할 영역의 길이를 나타냅니다.
     * @throws StringIndexOutOfBoundsException startIndex가 음수거나, 문장의 길이를 벗어나면 발생합니다.
     * @throws IllegalArgumentException length가 음수면 발생합니다.
     */
    public void delete(int startIndex, int length) {
        if (startIndex < 0 || startIndex > length()) {
            throw new StringIndexOutOfBoundsException();
        }

        if (length < 0) {
            throw new IllegalArgumentException();
        }

        buffer.delete(startIndex, startIndex + length);
    }

    /**
     * 문장의 특정 영역을 교체합니다.
     * @param startIndex 교체를 시작할 문장 내 위치입니다.
     * @param replacingString 해당 위치부터 교체할 문자열입니다.
     * @throws StringIndexOutOfBoundsException startIndex가 음수거나, 문장의 길이를 벗어나면 발생합니다.
     */
    public void replace(int startIndex, String replacingString) {
        if (startIndex < 0 || startIndex >= length()) {
            throw new StringIndexOutOfBoundsException();
        }

        // startIndex = inclusive, endIndex = exclusive
        int endIndex = startIndex + replacingString.length();

        buffer.ensureCapacity(endIndex);
        buffer.replace(startIndex, endIndex, replacingString);
    }

    /**
     * 이 문장의 끝에 새로운 내용을 추가합니다.
     * @param addedString 새롭게 추가할 내용입니다.
     * @throws NullPointerException 매개변수가 null이면 발생합니다.
     */
    public void append(String addedString) {
        if (addedString == null) {
            throw new NullPointerException();
        }

        buffer.append(addedString);
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
