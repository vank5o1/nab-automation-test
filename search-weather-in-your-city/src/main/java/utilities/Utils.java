package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    /**
     * Get current time
     *
     * @param formatPattern type of date time want to displayed.
     */
    public static String getTimeNow(String formatPattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatPattern);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
