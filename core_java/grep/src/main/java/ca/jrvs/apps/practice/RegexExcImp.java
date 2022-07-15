package grep.src.main.java.ca.jrvs.apps.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class RegexExcImp implements RegexExc{

    public static boolean matchJpeg(String filename) {
        Pattern p = Pattern.compile("(^.*\\.jpe?g$)", Pattern.CASE_INSENSITIVE);
        return patternMatch(filename, p);
    }


    public static boolean matchIp(String ip) {
        Pattern p = Pattern.compile("(^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\.[0-9]{3})$");
        return patternMatch(ip, p);
    }




    public static boolean isEmptyLine(String line) {
        Pattern p = Pattern.compile("^(\\p{Space}*+)$");
        return patternMatch(line, p);
    }


    private static boolean patternMatch(String s, Pattern p) {
        Matcher m = p.matcher(s);
        boolean matchFound = m.find();
        if (matchFound) {
            System.out.println("match found " + s);
            return true;
        }
        else {
            System.out.println("no match found");
            return false;
        }
    }
}
