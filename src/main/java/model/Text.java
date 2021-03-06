package model;

import utility.IterableUtility;
import utility.StreamUtility;
import utility.StringUtility;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 텍스트를 나타내는 클래스입니다.
 */
public final class Text implements Splittable {
    private static final String WORD_SPLIT_REGEX = "\\s*\\S+\\s*";
    private static final String EXTRACT_PURE_WORD_REGEX = "\\s+";
    private static final String LINE_SPLIT_REGEX =
        String.format("%s*[^%s]+%s*", StringUtility.LINE_SEPARATOR
                                    , StringUtility.LINE_SEPARATOR
                                    , StringUtility.LINE_SEPARATOR);
    private static final String EXTRACT_PURE_LINE_REGEX =
        String.format("(?<=%s)", StringUtility.LINE_SEPARATOR);

    private StringBuffer buffer;

    /**
     * 빈 내용을 가지는 텍스트를 생성합니다.
     */
    public Text() {
        this(StringUtility.EMPTY_STRING);
    }

    /**
     * 초기 내용을 가지는 텍스트를 생성합니다.
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
     * 텍스트에서 내용이 처음 시작되는 위치를 탐색합니다.
     * @param content 탐색할 내용입니다.
     * @return 내용이 시작되는 위치입니다. 찾지 못할경우, -1이 반환됩니다.
     */
    public int indexOf(String content) {
        return indexOf(content, 0);
    }

    /**
     * 지정 위치에서 시작해서, 텍스트에서 내용이 처음 시작되는 위치를 탐색합니다.
     * @param content 탐색할 내용입니다.
     * @param startPos 탐색을 시작할 위치입니다.
     * @return 내용이 시작되는 위치입니다. 찾지 못할경우, -1이 반환됩니다.
     */
    public int indexOf(String content, int startPos) {
        if (startPos >= length()) {
            return -1;
        }

        return buffer.indexOf(content, startPos);
    }

    /**
     * 텍스트에서 내용이 처음 시작되는 위치를 특정 라인부터 탐색합니다.
     * @param content 탐색할 내용입니다.
     * @param lineIndex 탐색을 시작할 라인 번호를 가리킵니다.
     * @return 내용이 시작되는 위치입니다. 찾지 못할경우. -1이 반환됩니다.
     */
    public int indexOfStartFromLine(String content, int lineIndex) {
        int startIndex = 0;
        int countedLine = 0;
        for (String line : StreamUtility.toIterable(lineStream())) {
            if (countedLine == lineIndex) {
                break;
            }

            startIndex += line.length();
            ++countedLine;
        }
        return buffer.indexOf(content, startIndex);
    }

    /**
     * 이 텍스트에 존재하는 문장에 대한 열거자를 가져옵니다.
     * @return 텍스트에 존재하는 문장에 대한 열거자입니다.
     */
    public Iterable<String> lines() {
        return StreamUtility.toIterable(lineNewLineReplacedStream());
    }

    private Stream<String> lineNewLineReplacedStream() {
        return lineStream()
            .map(line -> line.replace(StringUtility.LINE_SEPARATOR,
                                      StringUtility.EMPTY_STRING));
    }

    private Stream<String> lineStream() {
        if (toString().replace(StringUtility.LINE_SEPARATOR, StringUtility.EMPTY_STRING)
                      .length() == 0) {
            return toString().chars()
                             .mapToObj(c -> (char) c)
                             .map(c -> c.toString());
        }

        return newLineIncludingStream()
            .flatMap(line ->
                Arrays.stream(line.split(EXTRACT_PURE_LINE_REGEX)));
    }

    /**
     * 이 텍스트에서 줄 분리자를 포함하는 문장에 대한 열거자를 가져옵니다.
     * @return 텍스트에 존재하는 분리자 포함 문장들의 열거자입니다.
     */
    public Iterable<String> newLineIncludingLines() {
        return StreamUtility.toIterable(newLineIncludingStream());
    }

    private Stream<String> newLineIncludingStream() {
        return decomposeText(LINE_SPLIT_REGEX);
    }

    private Stream<String> decomposeText(String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(toString());
        final List<String> decomposedParts = new ArrayList<>();

        while (matcher.find()) {
            decomposedParts.add(matcher.group());
        }

        return decomposedParts.stream();
    }

    /**
     * 이 텍스트에 존재하는 단어에 대한 열거자를 가져옵니다.
     * @return 텍스트에 존재하는 단어에 대한 열거자입니다.
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
     * 이 텍스트에서 공백 문자를 포함하는 단어에 대한 열거자를 가져옵니다.
     * @return 텍스트에 존재하는 공백 문자 포함 단어들의 열거자입니다.
     */
    public Iterable<String> spaceIncludingWords() {
        return StreamUtility.toIterable(spaceIncludingWordStream());
    }

    private Stream<String> spaceIncludingWordStream() {
        return decomposeText(WORD_SPLIT_REGEX);
    }

    /**
     * 주어진 범위의 부분 텍스트를 가져옵니다.
     * @param startIndex 부분 텍스트를 가져올 시작 위치입니다.
     * @param length 시작 위치에서 가져올 길이를 나타냅니다.
     * @return 주어진 범위의 부분 텍스트를 나타냅니다.
     * @throws StringIndexOutOfBoundsException
     * startIndex가 음수거나, 텍스트의 길이를 벗어나면 발생합니다.
     * @throws IllegalArgumentException length가 음수면 발생합니다.
     */
    public Text subText(int startIndex, int length) {
        if (startIndex < 0 || startIndex > length()) {
            throw new StringIndexOutOfBoundsException();
        }

        if (length < 0) {
            throw new IllegalArgumentException();
        }

        int endIndex = startIndex + length;

        // clamp length.
        endIndex = endIndex >= length() ? length() : endIndex;

        // startIndex = Inclusive, endIndex = Exclusive
        return new Text(buffer.substring(startIndex, endIndex));
    }

    /**
     * 텍스트의 특정 위치에 문자열을 삽입합니다.
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
     * 텍스트의 특정 영역을 지웁니다.
     * @param startIndex 삭제를 시작할 위치입니다.
     * @param length 삭제할 영역의 길이를 나타냅니다.
     * @throws StringIndexOutOfBoundsException
     *  startIndex가 음수거나, 텍스트의 길이를 벗어나면 발생합니다.
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
     * 텍스트의 특정 영역을 교체합니다.
     * @param startIndex 교체를 시작할 텍스트 내 위치입니다.
     * @param replacingString 해당 위치부터 교체할 문자열입니다.
     * @throws StringIndexOutOfBoundsException
     *  startIndex가 음수거나, 텍스트의 길이를 벗어나면 발생합니다.
     */
    public void replace(int startIndex, String replacingString) {
        if (replacingString.length() == 0) {
            return;
        }

        if (length() == 0) {
            append(replacingString);
            return;
        }

        if (startIndex < 0 || startIndex >= length()) {
            throw new StringIndexOutOfBoundsException();
        }

        // startIndex = inclusive, endIndex = exclusive
        int endIndex = startIndex + replacingString.length();

        buffer.ensureCapacity(endIndex);
        buffer.replace(startIndex, endIndex, replacingString);
    }

    /**
     * 이 텍스트의 끝에 새로운 내용을 추가합니다.
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
     * 텍스트의 길이를 반환합니다.
     * @return 텍스트의 길이입니다.
     */
    public int length() {
        return buffer.length();
    }

    /**
     * 주어진 위치에서 가장 가까운 문장을 가져옵니다.
     * @param position 검색할 위치를 나타냅니다.
     * @return 주어진 위치에서 가장 가까운 문장입니다.
     * @throws IllegalArgumentException 위치가 음수면 발생합니다.
     */
    public String findNearestLine(int position) {
        return findNearestString(newLineIncludingLines(), position);
    }

    /**
     * 특정 줄의 내용을 가져옵니다.
     * @param lineIndex 가져올 줄 번호입니다.
     * @return 해당 줄 번호에 대한 내용입니다.
     */
    public String getLine(int lineIndex) {
        return IterableUtility.toList(lines()).get(lineIndex);
    }

    /**
     * 전체 텍스트에 대한 위치를 포함하는 줄의 번호를 가져옵니다.
     * @param position 전체 텍스트에 대한 위치를 나타냅니다.
     * @return 해당 위치를 포함하는 줄 번호입니다.
     */
    public int positionToLineIndex(int position) {
        int countedLine = 0;
        for (String line : StreamUtility.toIterable(lineStream())) {
            if (position < 0) {
                break;
            }

            ++countedLine;
            position -= line.length();
        }

        return countedLine - 1;
    }

    /**
     * 주어진 위치에서 가장 가까운 단어를 가져옵니다.
     * @param position 검색할 위치를 나타냅니다.
     * @return 주어진 위치에서 가장 가까운 단어입니다.
     * @throws IllegalArgumentException 위치가 음수면 발생합니다.
     */
    public String findNearestWord(int position) {
        return findNearestString(spaceIncludingWords(), position);
    }

    private String findNearestString(Iterable<String> iterableString,
                                     int position) {
        if (position < 0) {
            throw new IllegalArgumentException();
        }

        int remainPosition = position;
        String nearestString = StringUtility.EMPTY_STRING;

        for (String string : iterableString) {
            nearestString = string;

            if (remainPosition - string.length() < 0) {
                break;
            }

            remainPosition -= string.length();
        }

        return nearestString;
    }

    /**
     * 두 텍스트의 동일 여부에 대해 질의합니다.
     * @param obj 질의할 객체를 나타냅니다.
     * @return 두 텍스트의 동일 여부입니다.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Text comparedText = (Text) obj;
        return this.toString().equals(comparedText.toString());
    }

    /**
     * 해당 텍스트에 대한 해쉬 코드 값을 가져옵니다.
     * @return
     * 텍스트에 대한 해쉬 코드 값입니다. 내용이 변할 경우 해쉬 값은 변할 수 있습니다.
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * 텍스트 전체를 문자열로 반환합니다.
     * @return 텍스트 전체의 정보입니다.
     */
    @Override
    public String toString() {
        return buffer.toString();
    }
}
