package framework.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.listeners.JobListenerSupport;
import org.quartz.listeners.TriggerListenerSupport;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @author jack.zhang
 * @since 2015/4/16
 */
public class SimpleTrigger {


    public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("jobSays", "Hello World!")
                    .usingJobData("doubleVal", 3.14D)
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(4)
                            .repeatForever())
                    .build();

            //add job listener
            scheduler.getListenerManager().addJobListener(new JobListenerSupport() {
                @Override
                public String getName() {
                    return "job-listener";
                }

                @Override
                public void jobToBeExecuted(JobExecutionContext context) {
                    System.out.println("job to be exec");
                }

                @Override
                public void jobExecutionVetoed(JobExecutionContext context) {
                    System.out.println("job was deny");
                }

                @Override
                public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
                    System.out.println("job was exec");
                }
            }, KeyMatcher.keyEquals(JobKey.jobKey("job1", "group1")));

            //add trigger listener
            scheduler.getListenerManager().addTriggerListener(new TriggerListenerSupport() {
                @Override
                public String getName() {
                    return "trigger-listener";
                }

                @Override
                public void triggerFired(Trigger trigger, JobExecutionContext context) {
                    System.out.println("trigger fired");
                }

                @Override
                public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
                    System.out.println("trigger completed");
                }
            }, KeyMatcher.keyEquals(TriggerKey.triggerKey("trigger1", "group1")));

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(60000);
            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }

    public static class HelloJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            Object jobSays = jobExecutionContext.getJobDetail().getJobDataMap().get("jobSays");
            JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
            Object doubleVal = mergedJobDataMap.get("doubleVal");
            JobKey key = jobExecutionContext.getJobDetail().getKey();
            System.out.println("Instance " + key + " of DumbJob says: " + jobSays
                    + " and val is: " + doubleVal);
        }
    }
}
