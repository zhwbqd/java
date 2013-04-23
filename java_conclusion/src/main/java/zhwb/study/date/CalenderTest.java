/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.date;

import java.util.Calendar;
import java.util.Date;

public class CalenderTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {
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

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        System.out.println(cal.getTime());

    }

}
