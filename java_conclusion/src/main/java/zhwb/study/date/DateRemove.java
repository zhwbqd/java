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

public class DateRemove
{
    public static long formatDate(final long milli)
        throws ParseException
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(df.format(new Date(milli))).getTime();
    }

    public static long formatDateCalendar(final long milli)
    {
        Calendar cal = Calendar.getInstance();
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
        System.out.println(new Date(formatDate(System.currentTimeMillis())));
        System.out.println(new Date(formatDateCalendar(System.currentTimeMillis())));
    }
}
