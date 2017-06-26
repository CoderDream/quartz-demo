package com.coderdream.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class HelloJob implements Job {

	private String message;
	private Float floatJobValue;
	private Double doubleTriggerValue;

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
		System.out.println("message is:" + message);
		System.out.println("floatJobValue is:" + floatJobValue);
		System.out.println("doubleTriggerValue is:" + doubleTriggerValue);

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Float getFloatJobValue() {
		return floatJobValue;
	}

	public void setFloatJobValue(Float floatJobValue) {
		this.floatJobValue = floatJobValue;
	}

	public Double getDoubleTriggerValue() {
		return doubleTriggerValue;
	}

	public void setDoubleTriggerValue(Double doubleTriggerValue) {
		this.doubleTriggerValue = doubleTriggerValue;
	}

}