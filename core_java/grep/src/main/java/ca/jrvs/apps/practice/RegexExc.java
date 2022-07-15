package grep.src.main.java.ca.jrvs.apps.practice;


import java.util.regex.Pattern;

public interface RegexExc {
    /**
     * return true if filename extension is jpg or jpeg (case insensitive)
     * @param filename
     * @return
     */
    public static boolean matchJpeg(String filename){
        return true;
    };




    /**
     * return true if ip is valid
     * to simplify the problem, IP address range is from 0.0.0.0 to 999.999.999.999
     * @param ip
     * @return
     */
    public static boolean matchIp(String ip) {
        return true;
    };

    /**
     * return true if line is empty (e.g. empty, whitespace, tabs, etc...)
     * @param line
     * @return
     */
    public static boolean isEmptyLine(String line) {
        return true;
    };
}
