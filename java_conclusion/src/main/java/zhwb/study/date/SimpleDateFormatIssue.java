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

public class SimpleDateFormatIssue
{
    public static void main(final String[] args)
        throws ParseException
    {
        //        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String newtime = "Tue Mar 08 09:27:28 +0000 2011";
        DateFormat df = new SimpleDateFormat("EEE MMM dd H:mm:ss Z yyyy", Locale.US);
        Date sdate = df.parse(newtime);
        System.out.println(sdate);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        System.out.println(formatter.format(sdate));
    }
}
