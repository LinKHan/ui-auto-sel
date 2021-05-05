package utilAPI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatAPI {

    private static LocalDateTime localDateTime;

    public String getTimeDateMonthYear(){
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy_MMMM_dd_hh_mm_ss");
        localDateTime = LocalDateTime.now();
        String timeDateMonthYear =  FORMATTER.format(localDateTime);
        System.out.println(timeDateMonthYear);
        return  timeDateMonthYear;
    }

}
