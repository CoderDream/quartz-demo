package com.coderdream.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 打印当前的执行时间，格式为2017-01-01 00:00:00
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current excut is:" + sf.format(date));
		// 编写具体的业务逻辑
		Trigger currentTrigger = context.getTrigger();
		System.out.println("Start Time is:" + sf.format(currentTrigger.getStartTime()));
		System.out.println("End Time is:" + sf.format(currentTrigger.getEndTime()));
		JobKey jobKey = currentTrigger.getJobKey();
		System.out.println("JobKey info--" + "jobName:" + jobKey.getName() + " jobGroup: " + jobKey.getGroup());
	}

}