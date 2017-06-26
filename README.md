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

