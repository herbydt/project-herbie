package utilities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    public static String getDateTimeToday(String format) throws Exception {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static boolean isWithinMinDifference(String time1, String time2, String timeFormat, int minutes) throws Exception {

        boolean isWithinMin = false;

        SimpleDateFormat parser = new SimpleDateFormat(timeFormat);
        Date date1 = parser.parse(time1);
        Date date2 = parser.parse(time2);

        long diffMin = ((date1.getTime() - date2.getTime())/60000);

        if (Math.abs(diffMin)<=minutes) {
            isWithinMin = true;
        }
        return isWithinMin;
    }

    public static int compareDates(String date1, String date2, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date1_converted = sdf.parse(date1);
        Date date2_converted = sdf.parse(date2);

        return date1_converted.compareTo(date2_converted);
    }

    public static Date stringToDate(String strDate, String format) throws ParseException {
        DateFormat dateformat = new SimpleDateFormat(format);
        return dateformat.parse(strDate);
    }

    public String addMonthsToDateToday(int months_to_add, String date_format) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, months_to_add);

        return new SimpleDateFormat(date_format).format(cal.getTime());
    }

    public static String getMinusCurrentDate(int numberOfDays, String format) {
        return DateTimeFormat.forPattern(format).print(DateTime.now().minusDays(numberOfDays));
    }

    public static String getPlusCurrentDate(int numberOfDays, String format) {
        return DateTimeFormat.forPattern(format).print(DateTime.now().plusDays(numberOfDays));
    }

    public static String getMinusCurrentMinute(int numberOfMinute, String format) {
        return DateTimeFormat.forPattern(format).print(DateTime.now().minusMinutes(numberOfMinute));
    }

    public static String getPlusCurrentMinute(int numberOfMinute, String format) {
        return DateTimeFormat.forPattern(format).print(DateTime.now().plusMinutes(numberOfMinute));
    }

    public static long subtractTimeInMinutes(String time1, String time2, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date time1_converted = sdf.parse(time1);
        Date time2_converted = sdf.parse(time2);

        return (time1_converted.getTime() - time2_converted.getTime())/60000;
    }
}

