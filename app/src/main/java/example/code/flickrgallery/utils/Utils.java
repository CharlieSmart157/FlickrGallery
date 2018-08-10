package example.code.flickrgallery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String parseDate(String unparsedDate) {
        //2018-08-03T09:24:01-08:00
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return LocalDate.parse(unparsedDate, formatter).toString();
    }
}
