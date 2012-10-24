/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package jtr.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

import org.junit.Test;

public class DateCastTest
{

    @Test
    public void classCast()
    {
        try
        {
            String st = "2005-12-02 12:00:00";
            Timestamp starttime = Timestamp.valueOf(st);
            System.out.println(starttime.toString());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Test
    public void fmsExcep()
    {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
        sdf.applyPattern("dd MMM yyyy HH:mm:ss z");
        java.sql.Date date = null;
        System.out.println(sdf.format(((java.util.Date)date)));
    }

}
