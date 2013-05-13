/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateRemove
{

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    public static Date formatStringToDate(final String serviceCreditEndDate)
        throws ParseException
    {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        df.setTimeZone(UTC_TIME_ZONE);
        return df.parse(serviceCreditEndDate);
    }

    public static long formatDateCalendar(final long milli)
    {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        //        Calendar cal = Calendar.getInstance(); //this will add local timezone and change the final timeMilli
        cal.setTimeInMillis(milli);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    public static void main(final String[] args)
        throws ParseException
    {
        String utcDataBaseTimeStr = "2012-5-1";
        System.out.println(formatStringToDate(utcDataBaseTimeStr)); //+8 localTimeZone 
        long utcTime = formatStringToDate(utcDataBaseTimeStr).getTime();

        long formatTime = formatDateCalendar(utcTime);

        System.out.println(utcTime == formatTime);
        System.out.println(new Date(formatTime));

    }
}
