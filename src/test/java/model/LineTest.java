package model;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;
import org.junit.Test;
import utility.StringUtility;

import java.util.ArrayList;
import java.util.List;

public class LineTest {
    @Test(expected = NullPointerException.class)
    public void lineCreationWithNullTest() {
        final Line line = new Line(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lineCreationWithMultiSentenceTest() {
        final Line line = new Line("Hello. \n Hello. ");
    }

    @Test
    public void emptyLineLengthTest() {
        final String originalContent = StringUtility.EMPTY_STRING;
        final Line line = new Line();

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void emptyLineConvertTest() {
        final String originalContent = StringUtility.EMPTY_STRING;
        final Line line = new Line();
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void emptyLineWordTest() {
        final Line line = new Line();

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void emptyLineSpaceWordTest() {
        final Line line = new Line();

        final List<String> words = new ArrayList<>();
        line.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void onlySpaceLineLengthTest() {
        final String originalContent = "    \t    ";
        final Line line = new Line(originalContent);

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void onlySpaceLineConvertTest() {
        final String originalContent = "    \t    ";
        final Line line = new Line(originalContent);
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void onlySpaceLineWordTest() {
        final String originalContent = "    \t    ";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void onlySpaceLineSpaceWordTest() {
        final String originalContent = "    \t    ";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void multiWordsLineLengthTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void multiWordsLineConvertTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void multiWordsLineWordTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0), is(equalTo("Hello,")));
        Assert.assertThat(words.get(1), is(equalTo("World!")));
    }

    @Test
    public void multiWordsLineSpaceWordTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0), is(equalTo("Hello, \t ")));
        Assert.assertThat(words.get(1),
            is(equalTo("World!\r\t \t\r \n\n\n\n")));
    }


    @Test
    public void spacePrefixedMultiWordsLineLengthTest() {
        final String originalContent =
            " \t Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);

        Assert.assertThat(line.length(),
            is(equalTo(originalContent.length())));
    }

    @Test
    public void spacePrefixedMultiWordsLineConvertTest() {
        final String originalContent =
            " \r Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void spacePrefixedMultiWordsLineWordTest() {
        final String originalContent =
            " \r\t Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0), is(equalTo("Hello,")));
        Assert.assertThat(words.get(1), is(equalTo("World!")));
    }

    @Test
    public void spacePrefixedMultiWordsLineSpaceWordTest() {
        final String originalContent =
            " \r\t Hello, \t World!\r\t \t\r \n\n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0),
            is(equalTo(" \r\t Hello, \t ")));
        Assert.assertThat(words.get(1),
            is(equalTo("World!\r\t \t\r \n\n\n\n")));
    }

    @Test
    public void singleWordLineLengthTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void singleWordLineConvertTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void singleWordLineWordTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0), is(equalTo("Hello")));
    }

    @Test
    public void singleWordLineSpaceWordTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0),
            is(equalTo("Hello \t \r\t \r\r \t \n\n\n")));
    }

    @Test
    public void spacePrefixedSingleWordLineLengthTest() {
        final String originalContent = "\r  \tHello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void spacePrefixedSingleWordLineConvertTest() {
        final String originalContent = "\r \tHello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void spacePrefixedSingleWordLineWordTest() {
        final String originalContent = "\r \tHello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0), is(equalTo("Hello")));
    }

    @Test
    public void spacePrefixedSingleWordLineSpaceWordTest() {
        final String originalContent = "\r \tHello \t \r\t \r\r \t \n\n\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0),
            is(equalTo("\r \tHello \t \r\t \r\r \t \n\n\n")));
    }

    @Test
    public void emptyAppendTest() {
        final String originalContent = "Hello \t";
        final Line line = new Line(originalContent);

        line.append(StringUtility.EMPTY_STRING);
        Assert.assertThat(line.toString(), is(equalTo(originalContent)));
    }

    @Test
    public void singleAppendTest() {
        final String originalContent = " Hello \t";
        final Line line = new Line(originalContent);

        line.append("World!");

        Assert.assertThat(line.toString(),
            is(equalTo(originalContent + "World!")));
    }

    @Test
    public void multipleWordAppendTest() {
        final String originalContent = "Hello \t";
        final Line line = new Line(originalContent);

        line.append("Wor  ld  \t!");

        Assert.assertThat(line.toString(),
            is(equalTo(originalContent + "Wor  ld  \t!")));
    }

    @Test(expected = NullPointerException.class)
    public void nullReplacingTest() {
        final String originalContent = "Hello \t";
        final Line line = new Line(originalContent);

        line.replace(0, null);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void negativePosReplacingTest() {
        final String originalContent = "Hello \t";
        final Line line = new Line(originalContent);

        line.replace(-1, "World!");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void overLengthPosReplacingTest() {
        final String originalContent = "Hello \t";
        final Line line = new Line(originalContent);

        line.replace(100, "Hello!");
    }

    @Test
    public void emptyReplacingTest() {
        final String originalContent = "Hello \t";
        final Line line = new Line(originalContent);

        line.replace(0, StringUtility.EMPTY_STRING);
        Assert.assertThat(line.toString(), is(equalTo("Hello \t")));
    }

    @Test
    public void singleWordReplacingTest() {
        final String originalContent = "Hello \tWorld!";
        final Line line = new Line(originalContent);

        // index 7 = start point of 'World!'
        line.replace(7, "Universe!");
        Assert.assertThat(line.toString(), is(equalTo("Hello \tUniverse!")));
    }

    @Test
    public void multipleWordReplacingTest() {
        final String originalContent = "Hello \tWorld!";
        final Line line = new Line(originalContent);

        // index 5 = start point of ' '
        line.replace(5, "!, this is a pencil!");
        Assert.assertThat(line.toString(), is(equalTo("Hello!, this is a pencil!")));
    }

    @Test
    public void spaceReplacingTest() {
        final String originalContent = "Hello,\tWorld!";
        final Line line = new Line(originalContent);

        // index 6 = start point of '\t'
        line.replace(6, " ");
        Assert.assertThat(line.toString(), is(equalTo("Hello, World!")));
    }

    @Test
    public void spaceToWordReplacedTest() {
        final String originalContent = "\tABCD   EFGH\n";
        final Line line = new Line(originalContent);

        line.replace(6, "X");

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(3)));
        Assert.assertThat(words.get(0), is(equalTo("ABCD")));
        Assert.assertThat(words.get(1), is(equalTo("X")));
        Assert.assertThat(words.get(2), is(equalTo("EFGH")));
    }

    @Test
    public void emptyInsertionTest() {
        final String originalContent = "Hello, World";
        final Line line = new Line(originalContent);

        line.insert(0, StringUtility.EMPTY_STRING);
        Assert.assertThat(line.toString(), is(equalTo(originalContent)));
    }

    @Test
    public void wordInsertionTest() {
        final String originalContent = "Hello, World";
        final Line line = new Line(originalContent);

        // index 6 = start point of ' '
        line.insert(6, " this is");
        Assert.assertThat(line.toString(),
            is(equalTo("Hello, this is World")));
    }

    @Test(expected = NullPointerException.class)
    public void nullInsertionTest() {
        final String originalContent = "Hello, World";
        final Line line = new Line(originalContent);

        line.insert(0, null);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void negativePosInsertionTest() {
        final String originalContent = "Hello, World";
        final Line line = new Line(originalContent);

        line.insert(-1, "Hello!");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void overLengthPosInsertionTest() {
        final String originalContent = "Hello, World";
        final Line line = new Line(originalContent);

        line.insert(15, "Hello!");
    }
}
