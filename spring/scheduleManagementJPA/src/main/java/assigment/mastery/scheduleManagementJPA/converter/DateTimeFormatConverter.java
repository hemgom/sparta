package assigment.mastery.scheduleManagementJPA.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatConverter {
    public static String convertDateTimeFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
