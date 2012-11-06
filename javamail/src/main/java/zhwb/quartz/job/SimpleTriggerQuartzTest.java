/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.quartz.job;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerQuartzTest
{
    public static void main(String[] args)
    {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler;
        try
        {
            scheduler = sf.getScheduler();

            //JobDetail Conveys the detail properties of a given Job instance. JobDetails are to be created/defined with JobBuilder.   
            //JobBuilder无构造函数，所以只能通过JobBuilder的静态方法newJob(Class<? extends Job> jobClass)生成JobBuilder实例  
            //withIdentity(String name,String group)参数用来定义jobKey，如果不设置，也会自动生成一个独一无二的jobKey用来区分不同的job  
            //build()方法 Produce the JobDetail instance defined by this JobBuilder.  
            JobDetail job = JobBuilder.newJob(SendMailJob.class).withIdentity("job1", "group1").build();

            //use TriggerBuilder to instantiate an actual Trigger  
            //withIdentity(String name,String group)参数用来定义TriggerKey，如果不设置，也会自动生成一个独一无二的TriggerKey用来区分不同的trigger  
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey("trigger1", "group1")).startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger  
            scheduler.scheduleJob(job, trigger);

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
