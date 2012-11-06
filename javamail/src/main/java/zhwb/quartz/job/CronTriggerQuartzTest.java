/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.quartz.job;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerQuartzTest
{
    public static void main(String[] args)
    {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler;
        try
        {
            scheduler = sf.getScheduler();

            // Start up the scheduler  
            scheduler.start();

            //当前主线程睡眠2秒  
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(30 * 1000);

            // shut down the scheduler  
            scheduler.shutdown(true);
        }
        catch (SchedulerException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
