package com.coderdream.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {

	public static void main(String[] args) throws SchedulerException {
		// 创建一个JobDetail实例，将该实例与HelloJob Class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob").build();

		// 打印当前的执行时间，格式为2017-01-01 00:00:00
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current execute is:" + sf.format(date));

		// 获取距离当前时间4秒钟之后首次执行任务，之后每隔两秒钟重复执行一次任务，执行3次
		date.setTime(date.getTime() + 4000);

		// 获取距离当前时间6秒后的时间
		Date endDate = new Date();
		endDate.setTime(endDate.getTime() + 6000);

		// 创建一个Trigger实例，定义该Job立即执行，并且每个两秒钟重复一次
		// SimpleTrigger.REPEAT_INDEFINITELY 无数次 直到永远
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1").startAt(date)
				.endAt(endDate)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(2).withRepeatCount(3))
				.build();

		// 创建Scheduler实例
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();

		scheduler.scheduleJob(jobDetail, trigger);
	}
}
