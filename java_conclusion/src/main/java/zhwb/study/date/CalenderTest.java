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
import java.util.TimeZone;

public class CalenderTest
{

    /**
     * Description goes here.
     *
     * @param args
     * @throws InterruptedException 
     * @throws ParseException 
     */
    public static void main(final String[] args)
        throws InterruptedException, ParseException
    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Calendar birth = Calendar.getInstance();
        birth.set(1989, 2, 2);
        Date date = birth.getTime();
        System.out.println(date);
        Calendar now = Calendar.getInstance();
        System.out.println(now.after(birth));

        now.add(Calendar.YEAR, 1);
        //        now.add(Calendar.DAY_OF_YEAR, 2);
        now.add(Calendar.DAY_OF_YEAR, 100);
        System.out.println(now.getTime());

        /*compare date with different type */
        Date d1 = new Date();
        Thread.currentThread().sleep(300);
        Date d2 = new Date();
        System.out.println(d1.equals(d2));

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = sdf.format(d1);
        String s2 = sdf.format(d2);
        System.out.println(s1 + ", " + s2);
        System.out.println(s1.equals(s2));

        String sd1 = sdf.format(new Date());
        String sd2 = sdf.format(new Date());
        System.out.println(sdf.parse(sd1));
        System.out.println(sdf.parse(sd2));
        System.out.println(sdf.parse(sd1).after(sdf.parse(sd2)));
        System.out.println(sdf.parse(sd1).before(sdf.parse(sd2)));
        System.out.println(sdf.parse(sd1).equals(sdf.parse(sd2)));

    }

}
