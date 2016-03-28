package pl.ptm.data.parser;

/**
 * Created by jbogacz on 25.03.2016.
 */
public class ZtmFormatUtils {

    public static boolean isStopsGroupSection(String line) {
        return isSpaceAt(2, line) && isNotSpaceAt(3, line);
    }

    public static boolean isStop(String line) {
        return isSpaceAt(8, line) && isNotSpaceAt(9, line);
    }

    public static boolean isSpaceAt(int i, String line){
        return line.charAt(i) == ' ';
    }

    public static boolean isNotSpaceAt(int i, String line){
        return line.charAt(i) != ' ';
    }

    public static String parseStringAt(int i, String line){
        int spaceIndex = line.indexOf(' ', i);
        int comaIndex = line.indexOf(',', i);
        int endIndex = comaIndex < spaceIndex ? comaIndex : spaceIndex;
        String word = line.substring(i, endIndex);
        return word;
    }

    public static Long parseLongAt(int i, String line){
        String value = parseStringAt(i, line);
        return Long.valueOf(value);
    }

    public static Double parseDoubleAt(int i, String line){
        String value = parseStringAt(i, line);
        return Double.valueOf(value);
    }

}
