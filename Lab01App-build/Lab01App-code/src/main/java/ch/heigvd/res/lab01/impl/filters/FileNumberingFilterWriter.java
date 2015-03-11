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
    private boolean firstChar = true;
    private int counter = 1;
    private int prevChar = '0';
    private boolean firstLine = true;
    private String osNewLine = "\n"; // By default the Linux newline method is used

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    public void write(String str, int off, int len) throws IOException {
        str = str.substring(off, off+len);

        // If needed, change newline method
        if (str.contains("\r\n")) {
            str = str.replaceAll("\r\n", "\n");
            osNewLine = "\r\n";
        }
        if (str.contains("\r")) {
            str = str.replaceAll("\r", "\n");
            osNewLine = "\r";
        }
        

        String output = "";
        int newlineCounter = str.replaceAll("[^\n]", "").length(); // Count the number of newline
        if (newlineCounter > 1) {
            int i = 0;
            int j = str.indexOf('\n', 0);
            while (newlineCounter-- != 0) {
                if (firstLine) {
                    output += counter++ + "\t" + str.substring(i,j+1);
                    firstLine = false;
                } else {
                    output += counter++ + "\t" + str.substring(i+1,j+1);
                }
                
                i = j;
                j = str.indexOf('\n', j+1);
            }

            if (!str.endsWith("\n")) {
                output += counter++ + "\t" + str.substring(i+1, str.length());
            }
            
        } else if (newlineCounter == 1) {
            if (firstLine) {
                output += counter++ + "\t" + str + counter++ + "\t";
                firstLine = false;
            } else {
                output += str + counter++ + "\t";
            }
        } else {
            if (firstLine) {
                output += counter++ + "\t" + str;
                firstLine = false;
            } else {
                output += str;
            }
        }

        // Set the correct newline method
        if (osNewLine != "\n") {
            output = output.replaceAll("\n", osNewLine);
        }

        // System.out.println("HERE :> "+output); // Debug display

        out.write(output);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(new String(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        if (c != '\n' && prevChar == '\r') { // For Mac OS
            firstChar = true;
        }
        
        if (firstChar) {
            firstChar = false;
            out.write(new String(counter + "\t" + (char)c));
            counter++;
        } else {
            out.write(c);
        }
        
        if (c == '\n') { // For Linux and Windows
            firstChar = true;
        }
        
        prevChar = c;
    }

}
