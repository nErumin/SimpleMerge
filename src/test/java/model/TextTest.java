package model;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
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

        Assert.assertThat(words.size(), is(equalTo(2)));
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
    public void getLineTest(){
        final Text text = new Text("Hello, World!\n\nHello, Universe!");
        text.getLine(2);
    }

    @Test
    public void positionToLineIndexText(){
        final Text text = new Text("Hello, World!\n\nHello, Universe!\n\ntest");
        text.positionToLineIndex(20);
    }

    @Test
    public void replaceLengthZeroTest(){
        final Text text = new Text("");
        text.replace(0,"fine");
    }
    @Test
    public void positionToLineIndexExceptionText(){
        final Text text = new Text("Hello, World!\n\nHello, Universe!");
        text.positionToLineIndex(-1);
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

    @Test(expected = IllegalArgumentException.class)
    public void negativeLengthDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(1, -1);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void overLengthPosDeletionTest() {
        final String originalContent = "Hello, World";
        final Text text = new Text(originalContent);

        text.delete(15, 10);
    }

    @Test
    public void indexOfExceptionTest(){
        final Text text = new Text("Hello, World!\n\nHello, Universe!");
        text.indexOf("Hello",100);
    }
    @Test
    public void indexOfStartFromLineTest(){
        final Text text = new Text("Hello, World!\n\nHello, Universe!");
        text.indexOfStartFromLine("Hello",15);
    }

    @Test
    public void indexOfStartFromLineZeroTest(){
        final Text text = new Text("Hello, World!\n\nHello, Universe!");
        text.indexOfStartFromLine("Hello",0);
    }

    @Test
    public void emptyEqualTest() {
        final Text text = new Text(StringUtility.EMPTY_STRING);
        final Text anotherText = new Text(StringUtility.EMPTY_STRING);

        Assert.assertThat(text.equals(anotherText),
            is(equalTo(true)));
        Assert.assertThat(text.hashCode(),
            is(equalTo(anotherText.hashCode())));
    }

    @Test
    public void nullEqualTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.equals(null),
            is(equalTo(false)));
    }

    @Test
    public void textEqualTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");
        final Text anotherText = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.equals(anotherText),
            is(equalTo(true)));
        Assert.assertThat(text.hashCode(),
            is(equalTo(anotherText.hashCode())));
    }

    @Test
    public void textNotEqualTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");
        final Text anotherText = new Text("Hello. World!\n\nHello. Universe!");

        Assert.assertThat(text.equals(anotherText),
            is(equalTo(false)));
        Assert.assertThat(text.hashCode(),
            is(not(equalTo(anotherText.hashCode()))));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void negativePosSubTextTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        text.subText(-1, 1000);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void overLengthPosSubTextTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        text.subText(100, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeLengthSubTextTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        text.subText(3, -1);
    }

    @Test
    public void emptySubTextTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.subText(7, 0).toString(),
            is(equalTo(StringUtility.EMPTY_STRING)));
    }

    @Test
    public void lineSubTextTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.subText(7, 14).toString(),
            is(equalTo("World!\n\nHello,")));
    }

    @Test
    public void overLengthSubTextTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.subText(7, 100).toString(),
            is(equalTo("World!\n\nHello, Universe!")));
    }

    @Test
    public void allSubTextTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.subText(0, 100).toString(),
            is(equalTo("Hello, World!\n\nHello, Universe!")));

    }

    @Test
    public void emptyTextDecomposeTest() {
        final Text text = new Text();

        final List<String> lines = new ArrayList<>();
        text.lines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(0)));
    }

    @Test
    public void onlySpaceTextDecomposeTest() {
        final Text text = new Text(" \t \n \r\t \n \t");

        final List<String> lines = new ArrayList<>();
        text.lines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(3)));
        Assert.assertThat(lines.get(0), is(equalTo(" \t ")));
        Assert.assertThat(lines.get(1), is(equalTo(" \r\t ")));
        Assert.assertThat(lines.get(2), is(equalTo(" \t")));
    }

    @Test
    public void onlySpaceTextSpaceDecomposeTest() {
        final Text text = new Text(" \t \n \r\t \n \t");

        final List<String> lines = new ArrayList<>();
        text.newLineIncludingLines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(3)));
        Assert.assertThat(lines.get(0), is(equalTo(" \t \n")));
        Assert.assertThat(lines.get(1), is(equalTo(" \r\t \n")));
        Assert.assertThat(lines.get(2), is(equalTo(" \t")));
    }

    @Test
    public void singleLineTextDecomposeTest() {
        final Text text = new Text("Hello, World!\n");

        final List<String> lines = new ArrayList<>();
        text.lines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(1)));
        Assert.assertThat(lines.get(0), is(equalTo("Hello, World!")));
    }

    @Test
    public void singleLineTextSpaceDecomposeTest() {
        final Text text = new Text("Hello, World!\n");

        final List<String> lines = new ArrayList<>();
        text.newLineIncludingLines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(1)));
        Assert.assertThat(lines.get(0), is(equalTo("Hello, World!\n")));
    }

    @Test
    public void spacePrefixedSingleLineTextDecomposeTest() {
        final Text text = new Text("\n\n  Hello, World!\n");

        final List<String> lines = new ArrayList<>();
        text.lines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(3)));
        Assert.assertThat(lines.get(0),
            is(equalTo(StringUtility.EMPTY_STRING)));
        Assert.assertThat(lines.get(1),
            is(equalTo(StringUtility.EMPTY_STRING)));
        Assert.assertThat(lines.get(2),
            is(equalTo("  Hello, World!")));
    }

    @Test
    public void spacePrefixedSingleLineTextSpaceDecomposeTest() {
        final Text text = new Text("\n\n  Hello, World!\n");

        final List<String> lines = new ArrayList<>();
        text.newLineIncludingLines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(1)));
        Assert.assertThat(lines.get(0),
            is(equalTo("\n\n  Hello, World!\n")));
    }

    @Test
    public void multiLineTextDecomposeTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        final List<String> lines = new ArrayList<>();
        text.lines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(3)));
        Assert.assertThat(lines.get(0), is(equalTo("Hello, World!")));
        Assert.assertThat(lines.get(1), is(equalTo(StringUtility.EMPTY_STRING)));
        Assert.assertThat(lines.get(2), is(equalTo("Hello, Universe!")));
    }

    @Test
    public void multiLineTextSpaceDecomposeTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!\n\n ");

        final List<String> lines = new ArrayList<>();
        text.newLineIncludingLines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(3)));
        Assert.assertThat(lines.get(0), is(equalTo("Hello, World!\n\n")));
        Assert.assertThat(lines.get(1), is(equalTo("Hello, Universe!\n\n")));
        Assert.assertThat(lines.get(2), is(equalTo(" ")));
    }

    @Test
    public void spacePrefixedMultiLineTextDecomposeTest() {
        final Text text = new Text("\n\n Hello, World!\n\nHello, Universe!");

        final List<String> lines = new ArrayList<>();
        text.lines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(5)));
        Assert.assertThat(lines.get(0),
            is(equalTo(StringUtility.EMPTY_STRING)));
        Assert.assertThat(lines.get(1),
            is(equalTo(StringUtility.EMPTY_STRING)));
        Assert.assertThat(lines.get(2),
            is(equalTo(" Hello, World!")));
        Assert.assertThat(lines.get(3),
            is(equalTo(StringUtility.EMPTY_STRING)));
        Assert.assertThat(lines.get(4),
            is(equalTo("Hello, Universe!")));

    }

    @Test
    public void spacePrefixedMultiLineTextSpaceDecomposeTest() {
        final Text text = new Text("\n\n Hello, World!\n\nHello, Universe!\n\n ");

        final List<String> lines = new ArrayList<>();
        text.newLineIncludingLines().forEach(lines::add);

        Assert.assertThat(lines.size(), is(equalTo(3)));
        Assert.assertThat(lines.get(0),
            is(equalTo("\n\n Hello, World!\n\n")));
        Assert.assertThat(lines.get(1),
            is(equalTo("Hello, Universe!\n\n")));
        Assert.assertThat(lines.get(2),
            is(equalTo(" ")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativePosNearestLineTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        text.findNearestLine(-4);
    }

    @Test
    public void nearestLineTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.findNearestLine(0),
            is(equalTo("Hello, World!\n\n")));

        Assert.assertThat(text.findNearestLine(5),
            is(equalTo("Hello, World!\n\n")));

        Assert.assertThat(text.findNearestLine(14),
            is(equalTo("Hello, World!\n\n")));

        Assert.assertThat(text.findNearestLine(15),
            is(equalTo("Hello, Universe!")));

        Assert.assertThat(text.findNearestLine(100),
            is(equalTo("Hello, Universe!")));
    }

    @Test
    public void nearestWordTest() {
        final Text text = new Text("Hello, World!\n\nHello, Universe!");

        Assert.assertThat(text.findNearestWord(0),
            is(equalTo("Hello, ")));

        Assert.assertThat(text.findNearestWord(7),
            is(equalTo("World!\n\n")));

        Assert.assertThat(text.findNearestWord(10),
            is(equalTo("World!\n\n")));

        Assert.assertThat(text.findNearestWord(15),
            is(equalTo("Hello, ")));

        Assert.assertThat(text.findNearestWord(21),
            is(equalTo("Hello, ")));

        Assert.assertThat(text.findNearestWord(22),
            is(equalTo("Universe!")));
    }

    @Test
    public void sentenceIndexFindingTest() {
        final Text text = new Text("Hello, World!\nHello, Universe!");

        Assert.assertThat(text.indexOf(text.findNearestLine(20)),
            is(equalTo(14)));
    }

    @Test
    public void wordIndexFindingTest() {
        final Text text = new Text("Hello, World!\nHello, Universe!");

        Assert.assertThat(text.indexOf("World!"),
            is(equalTo(7)));
    }

    @Test
    public void notExistIndexFindingTest() {
        final Text text = new Text("Hello, World!\nHello, Universe!");

        Assert.assertThat(text.indexOf("WORLD"),
            is(equalTo(-1)));
    }
}
