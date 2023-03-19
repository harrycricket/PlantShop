package se160205.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtils {

    private static final List<SimpleDateFormat> DATE = new ArrayList<>();

    static {
        DATE.add(new SimpleDateFormat("dd/MM/yyyy"));
        DATE.add(new SimpleDateFormat("yyyy-MM-dd"));
    }

    public static Date parse(String dateString) {
        Date date = null;
        for (SimpleDateFormat sdf : DATE) {
            try {
                date = sdf.parse(dateString);
            } catch (Exception ex) {
            }
        }
        return date;
    }

    public static String format(Date date) {
        String dateString = null;
        for (SimpleDateFormat day : DATE) {
            try {
                dateString = day.format(date);
            } catch (Exception ex) {
            }
        }
        return dateString;
    }
}
