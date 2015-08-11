package com.incyyte.app.manager.scheduler;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RunClosureJob implements Job{

	public void execute(JobExecutionContext context)
	throws JobExecutionException {
 
		Map dataMap = context.getJobDetail().getJobDataMap();
		RunClosureTask task = (RunClosureTask)dataMap.get("runClosureTask");
		task.closeInCyyte();
	}

}
