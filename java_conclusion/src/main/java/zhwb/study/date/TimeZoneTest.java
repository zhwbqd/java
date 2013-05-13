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
import java.util.TimeZone;

public class TimeZoneTest
{

    /**
     * Description goes here.
     *
     * @param args
     * @throws ParseException 
     */
    public static void main(final String[] args)
        throws ParseException
    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date time = sdf.parse("2013-05-08 00:00:00");
        System.out.println(time);
        System.out.println(sdf.format(time));
    }

}
