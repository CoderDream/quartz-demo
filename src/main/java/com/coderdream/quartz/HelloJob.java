package com.coderdream.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 打印当前的执行时间，格式为2017-01-01 00:00:00
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current excut is:" + sf.format(date));
		// 编写具体的业务逻辑
		JobKey key = context.getJobDetail().getKey();
		System.out.println("My Job name and group are:" + key.getName() + ":" + key.getGroup());
		TriggerKey triggerKey = context.getTrigger().getKey();
		System.out.println("My Trigger name and group are:" + triggerKey.getName() + ":" + triggerKey.getGroup());
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
		String jobMsg = jobDataMap.getString("message");
		Float jobFloatValue = jobDataMap.getFloat("FloatJobValue");
		String triggerMsg = triggerDataMap.getString("message");
		Double triggerDoubleValue = triggerDataMap.getDouble("DoubleTriggerValue");
		System.out.println("JobMsg is:" + jobMsg);
		System.out.println("JobFloatValue is:" + jobFloatValue);
		System.out.println("TriggerMsg is:" + triggerMsg);
		System.out.println("TriggerDoubleValue is:" + triggerDoubleValue);
	}

}