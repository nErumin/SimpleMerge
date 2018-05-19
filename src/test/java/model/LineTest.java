package model;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LineTest {
    @Test(expected = NullPointerException.class)
    public void lineCreationWithNullTest() {
        final Line line = new Line(null);
    }

    @Test
    public void emptyLineConvertTest() {
        final String originalContent = "";
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
    public void multiWordsLineConvertTest() {
        final String originalContent = "Hello, \t \t World!";
        final Line line = new Line(originalContent);
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void multiWordsLineWordTest() {
        final String originalContent = "Hello, \t World!\n \r\t \n\n \t\r\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(2)));
        Assert.assertThat(words.get(0), is(equalTo("Hello,")));
        Assert.assertThat(words.get(1), is(equalTo("World!")));
    }

    @Test
    public void singleWordLineWordTest() {
        final String originalContent = "Hello \t\n \r\t \n\n\n \t\r \r\n";
        final Line line = new Line(originalContent);

        final List<String> words = new ArrayList<>();
        line.words().forEach(words::add);

        Assert.assertThat(words.size(), is(equalTo(1)));
        Assert.assertThat(words.get(0), is(equalTo("Hello")));
    }

    @Test
    public void singleWordLineConvertTest() {
        final String originalContent = "Hello \t\n \r\t \n\n\n \t\r \r\n";
        final Line line = new Line(originalContent);
        final String lineContent = line.toString();

        Assert.assertThat(lineContent, is(equalTo(originalContent)));
    }

    @Test
    public void emptyLineLengthTest() {
        final String originalContent = "";
        final Line line = new Line();

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void multiWordsLineLengthTest() {
        final String originalContent = "Hello, \t World!\n \r\t \n\n \t\r\n";
        final Line line = new Line(originalContent);

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }

    @Test
    public void singleWordLineLengthTest() {
        final String originalContent = "Hello \t\n \r\t \n\n\n \t\r \r\n";
        final Line line = new Line(originalContent);

        Assert.assertThat(line.length(), is(equalTo(originalContent.length())));
    }
}
