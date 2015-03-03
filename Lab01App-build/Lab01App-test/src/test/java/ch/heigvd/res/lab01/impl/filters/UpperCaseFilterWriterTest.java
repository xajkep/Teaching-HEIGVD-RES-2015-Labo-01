package ch.heigvd.res.lab01.impl.filters;

import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriterTest {

  @Test
  public void itShouldWorkWhenWritingAString() throws IOException {
    String line = "hello world";
    StringWriter stringWriter = new StringWriter();
    UpperCaseFilterWriter writer = new UpperCaseFilterWriter(stringWriter);
    writer.write(line);
    assertEquals("HELLO WORLD", stringWriter.toString());
  }

  @Test
  public void itShouldWorkWhenWritingPartOfAString() throws IOException {
    String line = "hello world";
    StringWriter stringWriter = new StringWriter();
    UpperCaseFilterWriter writer = new UpperCaseFilterWriter(stringWriter);
    writer.write(line, 3, 5);
    assertEquals("LO WO", stringWriter.toString());
  }

  @Test
  public void itShouldWorkWhenWritingACharArray() throws IOException {
    String line = "hello world";
    StringWriter stringWriter = new StringWriter();
    UpperCaseFilterWriter writer = new UpperCaseFilterWriter(stringWriter);
    char[] chars = new char[line.length()];
    line.getChars(0, line.length(), chars, 0);
    writer.write(chars);
    assertEquals("HELLO WORLD", stringWriter.toString());
  }

  @Test
  public void itShouldWorkWhenWritingPartOfACharArray() throws IOException {
    String line = "hello world";
    StringWriter stringWriter = new StringWriter();
    UpperCaseFilterWriter writer = new UpperCaseFilterWriter(stringWriter);
    char[] chars = new char[line.length()];
    line.getChars(0, line.length() - 1, chars, 0);
    writer.write(chars, 3, 5);
    assertEquals("LO WO", stringWriter.toString());
  }

  @Test
  public void itShouldWorkWhenWritingASingleChar() throws IOException {
    String line = "hello world";
    StringWriter stringWriter = new StringWriter();
    UpperCaseFilterWriter writer = new UpperCaseFilterWriter(stringWriter);
    for (int i = 0; i < line.length(); i++) {
      writer.write(line.charAt(i));
    }
    assertEquals("HELLO WORLD", stringWriter.toString());
  }

}
