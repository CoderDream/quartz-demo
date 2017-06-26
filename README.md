# Java定时任务调度工具详解中的Quartz篇

简介：本课程是系列课程Java定时任务调度工具详解中的Quartz篇，本系列课程旨在通过详细讲述Java定时调度工具的基本概念、工具，和这些工具里面包含的各个组件之间的关系，以及如何使用这些工具来实现定时调度功能，让学生能够对Java定时调度工具有一个清晰而准确的认识。然后结合一些经典的使用场景通过手把手的命令行操作进行教学，使同学们得心用手地使用这些定时调度工具来实现自己想要的功能。

 第1章 课程介绍 
 1-1 初识Quartz (12:01)


# 第2章 Quartz详解 # 

## 2-1 第一个Quartz程序 (18:33) ##


com.coderdream.quartz.HelloScheduler


    Current excut is:2017-06-25 21:08:33
	Current excut is:2017-06-25 21:08:33
	Hello World!
	Current excut is:2017-06-25 21:08:35
	Hello World!`




## 2-2 浅谈Job&JobDetail (09:03) ##

	System.out.println("jobDetail's name:" + jobDetail.getKey().getName());
	System.out.println("jobDetail's group:" + jobDetail.getKey().getGroup());
	System.out.println("jobDetail's jobClass:" + jobDetail.getJobClass().getName());

运行结果：

	jobDetail's name:myJob
	jobDetail's group:group1
	jobDetail's jobClass:com.coderdream.quartz.HelloJob
	Current excut is:2017-06-25 21:40:55
	Current excut is:2017-06-25 21:40:55
	Hello World!
	Current excut is:2017-06-25 21:40:56
	Hello World!

## 2-3 浅谈JobExecutionContext&JobDataMap(上) (13:01) ##

	Current excut is:2017-06-26 08:59:01
	Current excut is:2017-06-26 08:59:01
	My Job name and group are:myJob:group1
	My Trigger name and group are:myTrigger:group1
	JobMsg is:hello myJob1
	JobFloatValue is:3.14
	TriggerMsg is:hello myTrigger1
	TriggerDoubleValue is:2.0
	Current excut is:2017-06-26 08:59:03
	My Job name and group are:myJob:group1
	My Trigger name and group are:myTrigger:group1
	JobMsg is:hello myJob1
	JobFloatValue is:3.14
	TriggerMsg is:hello myTrigger1
	TriggerDoubleValue is:2.0

## 2-4 浅谈JobExecutionContext&JobDataMap(下) ##

使用getMergedJobDataMap，如果key值相同，会优先取trigger里面的值。

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
		JobDataMap jobDataMap = context.getMergedJobDataMap();
		String jobMsg = jobDataMap.getString("message");
		Float jobFloatValue = jobDataMap.getFloat("jobFloatValue");
		Double triggerDoubleValue = jobDataMap.getDouble("triggerDoubleValue");
		System.out.println("JobMsg is:" + jobMsg);
		System.out.println("JobFloatValue is:" + jobFloatValue);
		System.out.println("TriggerDoubleValue is:" + triggerDoubleValue);
	}


执行结果：

	Current excut is:2017-06-26 09:04:43
	Current excut is:2017-06-26 09:04:43
	My Job name and group are:myJob:group1
	My Trigger name and group are:myTrigger:group1
	JobMsg is:hello myTrigger1
	JobFloatValue is:3.14
	TriggerDoubleValue is:2.0
	Current excut is:2017-06-26 09:04:45
	My Job name and group are:myJob:group1
	My Trigger name and group are:myTrigger:group1
	JobMsg is:hello myTrigger1
	JobFloatValue is:3.14
	TriggerDoubleValue is:2.0

使用成员变量的get、set方法：

com.coderdream.quartz.HelloJob

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


执行结果：

	Current excut is:2017-06-26 09:12:22
	Current excut is:2017-06-26 09:12:22
	My Job name and group are:myJob:group1
	My Trigger name and group are:myTrigger:group1
	message is:hello myTrigger1
	floatJobValue is:3.14
	doubleTriggerValue is:2.0
	Current excut is:2017-06-26 09:12:23
	My Job name and group are:myJob:group1
	My Trigger name and group are:myTrigger:group1
	message is:hello myTrigger1
	floatJobValue is:3.14
	doubleTriggerValue is:2


## 2-5 浅谈Trigger (10:22) ##

设置startAt()和endAt()

	// 打印当前的执行时间，格式为2017-01-01 00:00:00
	Date date = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	System.out.println("Current excut is:" + sf.format(date));

	// 获取距离当前时间3秒后的时间
	date.setTime(date.getTime() + 3000);

	// 获取距离当前时间6秒后的时间
	Date endDate = new Date();
	endDate.setTime(endDate.getTime() + 6000);

	// 创建一个Trigger实例，定义该Job立即执行，并且每个两秒钟重复一次，直到永远
	Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").startAt(date).endAt(endDate)
			.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();


执行结果（由于开始时间与结束时间只间隔3秒，所以执行两次后，Job停止执行）：


	Current excut is:2017-06-26 09:28:12
	Current excut is:2017-06-26 09:28:15
	Start Time is:2017-06-26 09:28:15
	End Time is:2017-06-26 09:28:18
	JobKey info--jobName:myJob jobGroup: DEFAULT
	Current excut is:2017-06-26 09:28:17
	Start Time is:2017-06-26 09:28:15
	End Time is:2017-06-26 09:28:18
	JobKey info--jobName:myJob jobGroup: DEFAULT


## 2-6 SimpleTrigger (13:08) ##

示例1：设置SimpleTrigger的开始时间后，执行且只执行一次任务

	// 获取距离当前时间4秒钟之后，执行且只执行一次任务
	date.setTime(date.getTime() + 4000);

	// 创建一个Trigger实例，定义该Job立即执行，并且每个两秒钟重复一次，直到永远
	SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
			.withIdentity("myTrigger", "group1").startAt(date).build();

执行结果：

	Current excut is:2017-06-26 10:12:10
	Current excut is:2017-06-26 10:12:14
	Hello World!


示例2：4秒钟之后首次执行任务，执行3次

	// 获取距离当前时间4秒钟之后首次执行任务，之后每隔两秒钟重复执行一次任务，执行3次
	date.setTime(date.getTime() + 4000);

	// 创建一个Trigger实例，定义该Job立即执行，并且每个两秒钟重复一次
	// SimpleTrigger.REPEAT_INDEFINITELY 无数次 直到永远
	SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
			.withIdentity("myTrigger", "group1").startAt(date)
			.withSchedule(SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInSeconds(2).withRepeatCount(3))
			.build();


运行结果，执行3次后，Job停止：

	Current excut is:2017-06-26 10:29:22
	Current excut is:2017-06-26 10:29:26
	Hello World!
	Current excut is:2017-06-26 10:29:28
	Hello World!
	Current excut is:2017-06-26 10:29:30
	Hello World!
	Current excut is:2017-06-26 10:29:32
	Hello World!

示例3：一旦被指定了endTime参数，那么它会覆盖重复次数参数的效果

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

执行结果：

	Current execute is:2017-06-26 10:38:21
	Current execute is:2017-06-26 10:38:25
	Hello World!
	Current execute is:2017-06-26 10:38:27
	Hello World!

## 2-7 CronTrigger (24:09) ##

示例1：

	// 1.2017年内每天10点55分触发一次
	// 0 55 10 ? * * 2017

执行结果：

	Current execute is:2017-06-26 10:54:53
	Current execute is:2017-06-26 10:55:00
	Hello World!


示例2：

	// 2.每天的11点整至11点59分55秒，以及18点整至18点59分55秒，每5秒钟触发一次
	// 0/5 * 11,18 * * ?

执行结果：

	Current execute is:2017-06-26 11:15:17
	Current execute is:2017-06-26 11:15:20
	Hello World!
	Current execute is:2017-06-26 11:15:25
	Hello World!
	Current execute is:2017-06-26 11:15:30
	Hello World!

Cron表达式生成器

[http://www.pdtools.net/tools/becron.jsp](http://www.pdtools.net/tools/becron.jsp)


## 2-8 浅谈Scheduler (15:02) ##

Date scheduleJob(JobDetail jobDetail, Trigger trigger)

	Date executeDate = scheduler.scheduleJob(jobDetail, trigger);
	System.out.println("scheduled time is: " + sf.format(executeDate));

执行结果：

	Current execute is:2017-06-26 11:26:01
	scheduled time is: 2017-06-26 11:26:05
	Current execute is:2017-06-26 11:26:05
	Hello World!
	Current execute is:2017-06-26 11:26:10
	Hello World!

示例1：standby后再start

频率设定为每秒执行一次

	// scheduler执行后2秒后挂起
	Thread.sleep(2000L);
	scheduler.standby();

	// scheduler执行后3秒后重新启动执行
	Thread.sleep(3000L);
	scheduler.start();

执行结果（27秒开始执行，执行2秒后，29秒开始挂起，然后休眠3秒到32，然后32秒后开始执行，而且是一次性执行3次）：

	Current execute is:2017-06-26 11:32:27
	scheduled time is: 2017-06-26 11:32:27
	Current execute is:2017-06-26 11:32:27
	Hello World!
	Current execute is:2017-06-26 11:32:28
	Hello World!
	Current execute is:2017-06-26 11:32:29
	Hello World!
	Current execute is:2017-06-26 11:32:32
	Hello World!
	Current execute is:2017-06-26 11:32:32
	Hello World!
	Current execute is:2017-06-26 11:32:32
	Hello World!
	Current execute is:2017-06-26 11:32:33
	Hello World!
	Current execute is:2017-06-26 11:32:34
	Hello World!
	Current execute is:2017-06-26 11:32:35
	Hello World!

示例2：shutdown后再start

	// scheduler执行后2秒后挂起
	Thread.sleep(2000L);
	//scheduler.standby();
	scheduler.shutdown();
	// shutdown(true)表示等待所有正在执行的job执行完毕之后，再关闭scheduler
	// shutdown(false)表示立即关闭scheduler

	// scheduler执行后3秒后重新启动执行
	Thread.sleep(3000L);
	scheduler.start();

执行结果（43秒开始执行，执行2秒后，45秒开始关闭，然后再重新启动会报错）：

	Current execute is:2017-06-26 11:40:43
	scheduled time is: 2017-06-26 11:40:43
	Current execute is:2017-06-26 11:40:43
	Hello World!
	Current execute is:2017-06-26 11:40:44
	Hello World!
	Current execute is:2017-06-26 11:40:45
	Hello World!
	Exception in thread "main" org.quartz.SchedulerException: The Scheduler cannot be restarted after shutdown() has been called.
		at org.quartz.core.QuartzScheduler.start(QuartzScheduler.java:529)
		at org.quartz.impl.StdScheduler.start(StdScheduler.java:142)
		at com.coderdream.quartz.HelloScheduler.main(HelloScheduler.java:58)

shutdown(true) 表示等待所有正在执行的job执行完毕之后，再关闭scheduler

	Current execute is:2017-06-26 11:46:21
	scheduled time is: 2017-06-26 11:46:21
	Current execute is:2017-06-26 11:46:26
	Hello World!
	Current execute is:2017-06-26 11:46:27
	Hello World!
	Current execute is:2017-06-26 11:46:28
	Hello World!
	scheduler is shut down? true

shutdown(false) 表示立即关闭scheduler

	Current execute is:2017-06-26 11:48:12
	scheduled time is: 2017-06-26 11:48:12
	scheduler is shut down? true
	Current execute is:2017-06-26 11:48:17
	Hello World!
	Current execute is:2017-06-26 11:48:18
	Hello World!
	Current execute is:2017-06-26 11:48:19
	Hello World!
