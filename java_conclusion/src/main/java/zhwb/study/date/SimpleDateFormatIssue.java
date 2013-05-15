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
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SimpleDateFormatIssue
{
    public static void main(final String[] args)
        throws ParseException
    {
        //        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String newtime = "Tue Mar 08 09:27:28 +0000 2011";
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date sdate = df.parse(newtime);
        System.out.println(sdate);
        df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.CHINA);
        System.out.println(df.format(sdate));

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(formatter.format(sdate));

        String format = "yyyy-MM-dd HH:mm:ss";
        df = new SimpleDateFormat(format, Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdate = df.parse("2013-04-25 21:19:42.999999999999999999999");
        System.out.println(sdate);
    }
}
