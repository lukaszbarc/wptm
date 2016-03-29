package pl.ptm.data.parser;

import java.util.HashSet;
import java.util.Set;

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

    public static boolean isVehiclesList(String line){
        return isSpaceAt(11, line) && isNotSpaceAt(12, line);
    }

    public static boolean isSpaceAt(int i, String line){
        return i < line.length() && line.charAt(i) == ' ';
    }

    public static boolean isNotSpaceAt(int i, String line){
        return line.charAt(i) != ' ';
    }

    public static String parseStringAt(int i, String line){
        int spaceIndex = line.indexOf(' ', i);
        int comaIndex = line.indexOf(',', i);

        if(spaceIndex == -1){
            return line.substring(i, comaIndex);
        }
        else if(comaIndex == -1){
            return line.substring(i, spaceIndex);
        }
        else {
            int endIndex = comaIndex < spaceIndex ? comaIndex : spaceIndex;
            return line.substring(i, endIndex);
        }
    }

    public static Long parseLongAt(int i, String line){
        String value = parseStringAt(i, line);
        return Long.valueOf(value);
    }

    public static Double parseDoubleAt(int i, String line){
        String value = parseStringAt(i, line);
        return Double.valueOf(value);
    }

    public static Set<Long> parseTramsNumbers(int i, String line) {
        String[] split = prepareNumbersSplit(i, line);
        return prepareNumbersSet(split);
    }

    private static Set<Long> prepareNumbersSet(String[] split) {
        Set<Long> tramNumbers = new HashSet<>();
        for (String number : split) {
            if(isTrumNumber(number)){
                Long longNumber = Long.valueOf(
                        number.replace("^", ""));
                tramNumbers.add(longNumber);
            }
        }
        return tramNumbers;
    }

    private static String[] prepareNumbersSplit(int i, String line) {
        String numbers = line.substring(i);
        String noMultipleSpaces = numbers.trim().replaceAll(" +", " ");
        return noMultipleSpaces.split(" ");
    }

    public static boolean isTrumNumber(String number){
        if(number.length() < 3 || number.endsWith("^")){
            return true;
        } else {
            return false;
        }
    }
}
