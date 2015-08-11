package com.incyyte.app.manager.scheduler;

import com.incyyte.app.service.util.Logger;
import org.quartz.*;

import java.util.Date;


public class ScheduleClosureTest implements Job {

    public void execute(JobExecutionContext cntxt) throws JobExecutionException {
        Logger.debug("Generating incyyte closure - " + cntxt.getJobDetail().getJobDataMap().get("type"));
        //TODO Update Database Flag
    }

    public static void main(String[] args) {
        try {
            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail jobDetail = new JobDetail("InCyyte Closure", "InCyyte REF ID", ScheduleClosure.class);
            jobDetail.getJobDataMap().put("type", "FULL");

            //Create a simple trigger which fires exactly once, 20 seconds from now
            long startTime = System.currentTimeMillis() + (20L * 1000L);
            SimpleTrigger trigger = new SimpleTrigger("mySimpleTrigger", sched.DEFAULT_GROUP, new Date(startTime), null, 0, 0L);

            sched.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
