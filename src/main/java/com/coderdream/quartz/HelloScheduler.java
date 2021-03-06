package com.coderdream.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {

	public static void main(String[] args)
			throws SchedulerException, InterruptedException {
		// 创建一个JobDetail实例，将该实例与HelloJob Class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob").build();

		// 打印当前的执行时间，格式为2017-01-01 00:00:00
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current execute is:" + sf.format(date));

		// 0.每秒执行一次
		String cronString0 = "* * * * * ?";

		// String cronString1 = "0 55 10 ? * * 2017";
		// 2.每天的11点整至11点59分55秒，以及18点整至18点59分55秒，每5秒钟触发一次
		// String cronString2 = "0/5 * 11,18 * * ?";
		// 创建一个Trigger实例，定义该Job立即执行，并且每个两秒钟重复一次
		CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule(cronString0))
				.build();

		// 创建Scheduler实例
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();

		Date executeDate = scheduler.scheduleJob(jobDetail, trigger);
		System.out.println("scheduled time is: " + sf.format(executeDate));

		// scheduler执行后2秒后挂起
		Thread.sleep(2000L);
		// scheduler.standby();
		// shutdown(true)表示等待所有正在执行的job执行完毕之后，再关闭scheduler
		// shutdown(false)表示立即关闭scheduler
		scheduler.shutdown(false);
		System.out.println("scheduler is shut down? " + scheduler.isShutdown());

		// scheduler执行后3秒后重新启动执行
		// Thread.sleep(3000L);
		// scheduler.start();
	}
}
