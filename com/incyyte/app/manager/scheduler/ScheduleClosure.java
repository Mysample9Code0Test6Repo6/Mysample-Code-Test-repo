package com.incyyte.app.manager.scheduler;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
 
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.service.util.Logger;

public class ScheduleClosure {

    public static void main( String[] args ){
    	try {
			new ScheduleClosure(3L, 7L, Utility.verifyDateFormat("28/09/2011 19:59"), null);
		} catch (ParseException e) {
			Logger.warn("parsing problems",e);
		}
    }
    
	
	public ScheduleClosure(long incyyteId, long groupId, Date closureDate, UserDao dao) {
		try {
			closeInCyyte( incyyteId, groupId, closureDate, dao);
		} catch (Exception e) {
			Logger.error("exception with in closeInCyyte",e);
		}
	}

	
    public void closeInCyyte(long incyyteId, long groupId, Date closureDate, UserDao dao) throws Exception{
			Logger.debug("Schedule Closure");

			//Date closeDate = Utility.verifyDateFormat(closureDate);
			
	    	RunClosureTask task = new RunClosureTask();
	    	task.setGroupId(groupId);
	    	task.setIncyyteId(incyyteId);
	    	task.setUserdao(dao);
	    	
	 
	    	//specify your scheduler task details
	    	JobDetail job = new JobDetail();
	    	job.setName("runClosureJob"+incyyteId);
	    	job.setJobClass(RunClosureJob.class);
	 
	    	Map dataMap = job.getJobDataMap();
	    	dataMap.put("runClosureTask", task);
	 
	    	//configure the scheduler time
	    	SimpleTrigger trigger = new SimpleTrigger();
	    	trigger.setName("runClosureJobTesting"+incyyteId);
	    	trigger.setStartTime(closureDate);
	 
	    	//schedule it
	    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
	    	scheduler.start();
	    	scheduler.scheduleJob(job, trigger);

			Logger.debug("ScheduleClosure: Job Scheduled for: "+ closureDate);

	}


    
}
