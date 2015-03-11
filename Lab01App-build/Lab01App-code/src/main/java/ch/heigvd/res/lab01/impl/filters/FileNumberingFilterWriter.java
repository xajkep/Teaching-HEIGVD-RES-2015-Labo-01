package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private String os = "Linux";
    private boolean firstChar = true;
    private int counter = 1;
    private int prevChar = '0';
    private boolean firstLine = true;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        str = str.substring(off, off+len);

        // If needed, change newline method
        if (str.contains("\r\n")) {
            str = str.replaceAll("\r", ""); // delete all \r
            os = "Windows";
        } else if (str.contains("\r")) {
            str = str.replaceAll("\r", "\n");
            os = "Mac";
        }

        String outputString = "";
        boolean containNewLine = str.contains("\n");
        for (String line : str.split("\n", -1)) {
            if (containNewLine && !firstLine) {
                switch(os) {
                    case "Linux":
                        outputString += "\n";
                        break;
                    case "Windows":
                        outputString += "\r\n";
                        break;
                    case "Mac":
                        outputString += "\r";
                        break;
                }
                
            }
            
            outputString += counter++ + "\t" + line;
            
            if (firstLine) firstLine = false;
        }

        if (outputString.endsWith("\n") || outputString.endsWith("\r")) {
            firstChar = true;
        }
        
        out.write(outputString);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(new String(cbuf), off, len);
    }

    @Override
    // tested: This is line 1\r\nThis is line 2\nThis is line 3
    public void write(int c) throws IOException {
        if (firstChar) {
            firstChar = false;
            out.write(new String(counter + "\t" + (char)c));
            counter++;
        } else {
            out.write(c);
        }
        
        if (c == '\n') {
            firstChar = true;
        }
        
        prevChar = c;
    }

}
