/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author TNT
 */
public class DateHelper {

    public static java.sql.Date convertToSqlDate(String format, String dateStr) throws ParseException {
        Date jDate = new SimpleDateFormat(format).parse(dateStr);
        return new java.sql.Date(jDate.getTime());
    }

    public static Date convertToJavaDate(String format, String dateStr) throws ParseException {
        Date jDate = new SimpleDateFormat(format).parse(dateStr);
        return jDate;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static int compareIgnoreTime(Calendar cal1, Calendar cal2) {
        int yearC = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        int monthC = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
        int dateC = cal1.get(Calendar.DATE) - cal2.get(Calendar.DATE);
        if (yearC != 0) {
            return yearC;
        }
        if (monthC != 0) {
            return monthC;
        }
        return dateC;
    }
}
