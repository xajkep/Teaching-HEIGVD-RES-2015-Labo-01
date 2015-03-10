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

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        str = str.substring(off, off+len);

        if (str.contains("\r\n")) {
            str = str.replaceAll("\r", ""); // delete all \r
            os = "Windows";
        } else if (str.contains("\r")) {
            str = str.replaceAll("\r", "\n");
            os = "Mac";
        }

        String outputString = "";
        for (String line : str.split("\n")) {
            if (firstChar) {
                outputString += counter++ + "\t" + line;
                firstChar = false;
            } else {
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
        }

        out.write(outputString);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(new String(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        if (firstChar && (prevChar != '\n' || prevChar != '\r')) {        
            out.write(counter++ + "\t" + c);
            firstChar = false;
        } else {
            out.write(c);
        }

        if (c == '\n' || c == '\r') {
            firstChar = true;
        }
        
        prevChar = c;
    }

}
