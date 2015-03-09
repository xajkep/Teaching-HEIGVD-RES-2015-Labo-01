package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
        // @doc https://docs.oracle.com/javase/6/docs/api/java/lang/String.html

        String[] str = {"", ""};
        
        if (lines.contains("\r\n")) {
            str[0] = lines.split("\r\n")[0] + "\r\n";
        } else if (lines.contains("\n")) {
            str[0] = lines.split("\n")[0] + "\n";
        } else if (lines.contains("\r")) {
            str[0] = lines.split("\r")[0] + "\r";
        }

        str[1] = lines.substring(str[0].length());

        return str;
  }

}
