package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getTomorrowDateString(){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return tomorrow.format(formatter);
    }
}
