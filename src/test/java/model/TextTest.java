package model;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;
import org.junit.Test;
import utility.StringUtility;

import java.util.ArrayList;
import java.util.List;

public class TextTest {
    @Test(expected = NullPointerException.class)
    public void textCreationWithNullTest() {
        final Text text = new Text(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void textCreationWithMultiSentenceTest() {
        final Text text = new Text("Hello. \n Hello. ");
    }

    @Test
    public void emptyTextLengthTest() {
        final String originalContent = StringUtility.EMPTY_STRING;
        final Text text = new Text();

        Assert.assertThat(text.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void emptyTextConvertTest() {
        final String originalContent = StringUtility.EMPTY_STRING;
        final Text text = new Text();
        final String textContent = text.toString();

        Assert.assertThat(textContent, is(equalTo(originalContent)));
    }

    @Test
    public void emptyTextWordTest() {
        final Text text = new Text();

        final List<String> words = new ArrayList<>();
        text.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void emptyTextSpaceWordTest() {
        final Text text = new Text();

        final List<String> words = new ArrayList<>();
        text.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void onlySpaceTextLengthTest() {
        final String originalContent = "    \t    ";
        final Text text = new Text(originalContent);

        Assert.assertThat(text.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void onlySpaceTextConvertTest() {
        final String originalContent = "    \t    ";
        final Text text = new Text(originalContent);
        final String textContent = text.toString();

        Assert.assertThat(textContent, is(equalTo(originalContent)));
    }

    @Test
    public void onlySpaceTextWordTest() {
        final String originalContent = "    \t    ";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void onlySpaceTextSpaceWordTest() {
        final String originalContent = "    \t    ";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(0)));
    }

    @Test
    public void multiWordsTextLengthTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);

        Assert.assertThat(text.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void multiWordsTextConvertTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);
        final String textContent = text.toString();

        Assert.assertThat(textContent, is(equalTo(originalContent)));
    }

    @Test
    public void multiWordsTextWordTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(3)));
        Assert.assertThat(words.get(0), is(equalTo("Hello,")));
        Assert.assertThat(words.get(1), is(equalTo("World!")));
    }

    @Test
    public void multiWordsTextSpaceWordTest() {
        final String originalContent = "Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0), is(equalTo("Hello, \t ")));
        Assert.assertThat(words.get(1),
            is(equalTo("World!\r\t \t\r \n\n\n\n")));
    }


    @Test
    public void spacePrefixedMultiWordsTextLengthTest() {
        final String originalContent =
            " \t Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);

        Assert.assertThat(text.length(),
            is(equalTo(originalContent.length())));
    }

    @Test
    public void spacePrefixedMultiWordsTextConvertTest() {
        final String originalContent =
            " \r Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);
        final String textContent = text.toString();

        Assert.assertThat(textContent, is(equalTo(originalContent)));
    }

    @Test
    public void spacePrefixedMultiWordsTextWordTest() {
        final String originalContent =
            " \r\t Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0), is(equalTo("Hello,")));
        Assert.assertThat(words.get(1), is(equalTo("World!")));
    }

    @Test
    public void spacePrefixedMultiWordsTextSpaceWordTest() {
        final String originalContent =
            " \r\t Hello, \t World!\r\t \t\r \n\n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0),
            is(equalTo(" \r\t Hello, \t ")));
        Assert.assertThat(words.get(1),
            is(equalTo("World!\r\t \t\r \n\n\n\n")));
    }

    @Test
    public void singleWordTextLengthTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);

        Assert.assertThat(text.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void singleWordTextConvertTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);
        final String textContent = text.toString();

        Assert.assertThat(textContent, is(equalTo(originalContent)));
    }

    @Test
    public void singleWordTextWordTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0), is(equalTo("Hello")));
    }

    @Test
    public void singleWordTextSpaceWordTest() {
        final String originalContent = "Hello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0),
            is(equalTo("Hello \t \r\t \r\r \t \n\n\n")));
    }

    @Test
    public void spacePrefixedSingleWordTextLengthTest() {
        final String originalContent = "\r  \tHello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);

        Assert.assertThat(text.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void spacePrefixedSingleWordTextConvertTest() {
        final String originalContent = "\r \tHello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);
        final String textContent = text.toString();

        Assert.assertThat(textContent, is(equalTo(originalContent)));
    }

    @Test
    public void spacePrefixedSingleWordTextWordTest() {
        final String originalContent = "\r \tHello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0), is(equalTo("Hello")));
    }

    @Test
    public void spacePrefixedSingleWordTextSpaceWordTest() {
        final String originalContent = "\r \tHello \t \r\t \r\r \t \n\n\n";
        final Text text = new Text(originalContent);

        final List<String> words = new ArrayList<>();
        text.spaceIncludingWords().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0),
            is(equalTo("\r \tHello \t \r\t \r\r \t \n\n\n")));
    }

    @Test(expected = NullPointerException.class)
    public void nullAppendTest() {
        final Text text = new Text();
        text.append(null);
    }

    @Test
    public void emptyAppendTest() {
        final String originalContent = "Hello \t";
        final Text text = new Text(originalContent);

        text.append(StringUtility.EMPTY_STRING);
        Assert.assertThat(text.toString(), is(equalTo(originalContent)));
    }

    @Test
    public void singleAppendTest() {
        final String originalContent = " Hello \t";
        final Text text = new Text(originalContent);

        text.append("World!");

        Assert.assertThat(text.toString(),
            is(equalTo(originalContent + "World!")));
    }

    @Test
    public void multipleWordAppendTest() {
        final String originalContent = "Hello \t";
        final Text text = new Text(originalContent);

        text.append("Wor  ld  \t!");

        Assert.assertThat(text.toString(),
            is(equalTo(originalContent + "Wor  ld  \t!")));
    }

    @Test(expected = NullPointerException.class)
    public void nullReplacingTest() {
        final String originalContent = "Hello \t";
        final Text text = new Text(originalContent);

        text.replace(0, null);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void negativePosReplacingTest() {
        final String originalContent = "Hello \t";
        final Text text = new Text(originalContent);

        text.replace(-1, "World!");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void overLengthPosReplacingTest() {
        final String originalContent = "Hello \t";
        final Text text = new Text(originalContent);

        text.replace(100, "Hello!");
    }

    @Test
    public void emptyReplacingTest() {
        final String originalContent = "Hello \t";
        final Text text = new Text(originalContent);

        text.replace(0, StringUtility.EMPTY_STRING);
        Assert.assertThat(text.toString(), is(equalTo("Hello \t")));
    }

    @Test
    public void singleWordReplacingTest() {
        final String originalContent = "Hello \tWorld!";
        final Text text = new Text(originalContent);

        // index 7 = start point of 'World!'
        text.replace(7, "Universe!");
        Assert.assertThat(text.toString(), is(equalTo("Hello \tUniverse!")));
    }

    @Test
    public void multipleWordReplacingTest() {
        final String originalContent = "Hello \tWorld!";
        final Text text = new Text(originalContent);

        // index 5 = start point of ' '
        text.replace(5, "!, this is a pencil!");
        Assert.assertThat(text.toString(), is(equalTo("Hello!, this is a pencil!")));
    }

    @Test
    public void spaceReplacingTest() {
        final String originalContent = "Hello,\tWorld!";
        final Text text = new Text(originalContent);

        // index 6 = start point of '\t'
        text.replace(6, " ");
        Assert.assertThat(text.toString(), is(equalTo("Hello, World!")));
    }

    @Test
    public void spaceToWordReplacedTest() {
        final String originalContent = "\tABCD   EFGH\n";
        final Text text = new Text(originalContent);

        text.replace(6, "X");

        final List<String> words = new ArrayList<>();
        text.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(3)));
        Assert.assertThat(words.get(0), is(equalTo("ABCD")));
        Assert.assertThat(words.get(1), is(equalTo("X")));
        Assert.assertThat(words.get(2), is(equalTo("EFGH")));
    }

    @Test
    public void emptyInsertionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.insert(0, StringUtility.EMPTY_STRING);
        Assert.assertThat(text.toString(), is(equalTo(originalContent)));
    }

    @Test
    public void wordInsertionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        // index 6 = start point of ' '
        text.insert(6, " this is");
        Assert.assertThat(text.toString(),
            is(equalTo("Hello, this is World")));
    }

    @Test(expected = NullPointerException.class)
    public void nullInsertionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.insert(0, null);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void negativePosInsertionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.insert(-1, "Hello!");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void overLengthPosInsertionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.insert(15, "Hello!");
    }

    @Test
    public void emptyDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(0, 0);
        Assert.assertThat(text.toString(), is(equalTo(originalContent)));
    }

    @Test
    public void singleCharDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(5, 1);
        Assert.assertThat(text.toString(), is(equalTo("Hello World")));
    }

    @Test
    public void wordDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(5, 7);
        Assert.assertThat(text.toString(), is(equalTo("Hello")));
    }

    @Test
    public void overLengthDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(5, 100);
        Assert.assertThat(text.toString(), is(equalTo("Hello")));
    }

    @Test
    public void deleteAllTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(0, 100);
        Assert.assertThat(text.toString(),
            is(equalTo(StringUtility.EMPTY_STRING)));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void negativePosDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(-1, 10);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void overLengthPosDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(15, 10);
    }


}
