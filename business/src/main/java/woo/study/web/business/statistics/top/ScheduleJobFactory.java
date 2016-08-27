package woo.study.web.business.statistics.top;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.value.EmptyGettable;
import woo.study.web.common.util.value.ValueHelper;
import woo.study.web.common.util.value.ValueHelperFactory;

/**
 * 任务工厂类
 * 
 * 功能点：
 * 1、管理所有的任务
 * 2、管理任务组调度
 * 3、执行任务
 * 
 * @author tianjp
 *
 */
public class ScheduleJobFactory {

	private static Logger				log						= LoggerFactory.getLogger(ScheduleJobFactory.class);

	private List<ScheduleJobGroup>		scheduleGroupList;
	private Map<String, ScheduleJob>	scheduleJobsMap;
	private List<ScheduleJob>			scheduleJobList;

	@Resource(name = "scheduleJobExecutor")
	private ScheduleJobExecutor			scheduleJobExecutor;

	private static final String			SCHEDULE_JOBS			= "scheduleJobs";
	private static final String			SCHEDULE_JOBS_DATAS	= "scheduleJobsDatas";
	private static final String			SCHEDULE_JOB_EXECUTOR	= "scheduleJobExecutor";

	public ScheduleJobFactory() {
		scheduleGroupList = new ArrayList<ScheduleJobGroup>();
		scheduleJobsMap = new HashMap<String, ScheduleJob>();
		scheduleJobList = new ArrayList<ScheduleJob>();
	}
	
	/**
	 * 调度所有的任务组
	 * 在系统启动时自动运行
	 */
	public void schedule() {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();

			for (ScheduleJobGroup jobGroup : scheduleGroupList) {

				JobDataMap jobDataMap = new JobDataMap();
				jobDataMap.put(SCHEDULE_JOBS, jobGroup.getJobs());
				jobDataMap.put(SCHEDULE_JOBS_DATAS, ValueHelperFactory.empty());
				jobDataMap.put(SCHEDULE_JOB_EXECUTOR, scheduleJobExecutor);

				JobDetail jobDetail = JobBuilder.newJob().ofType(TempJob.class).setJobData(jobDataMap).build();

				CronTriggerImpl cronTrigger = new CronTriggerImpl();
				cronTrigger.setName("cronTrigger" + jobGroup.hashCode()); // Trigger的名字不能重复
				cronTrigger.setCronExpression(jobGroup.getCron());

				scheduler.scheduleJob(jobDetail, cronTrigger);
			}
			
			scheduler.start();

		} catch (Exception e) {
			log.error("ScheduleJobFactory schedule init error!", e);
			throw new RuntimeException("ScheduleJobFactory schedule init error!", e);
		}

	}
	
	/**
	 * 获取所有的任务，不区分任务组
	 * @return
	 */
	public List<ScheduleJob> getJobs() {
		return scheduleJobList;
	}
	
	/**
	 * 根据任务类名调用任务
	 * @param jobClass 任务类名
	 * @return
	 */
	public boolean invokeJob(String jobClass) {
		ScheduleJob scheduleJob = findJob(jobClass);
		return scheduleJobExecutor.invoke(scheduleJob);
	}
	
	/**
	 * 根据任务类名调用任务
	 * @param jobClass 任务类名
	 * @param dataMap JobExecuteDataMap参数
	 * @return
	 */
	public boolean invokeJob(String jobClass, ValueHelper valueHelper) {
		ScheduleJob scheduleJob = findJob(jobClass);
		return invokeJob(scheduleJob, valueHelper);
	}
	
	/**
	 * 执行任务
	 * @param scheduleJob 任务对象
	 * @param dataMap JobExecuteDataMap参数
	 * @return
	 */
	private boolean invokeJob(ScheduleJob scheduleJob, ValueHelper valueHelper) {
		scheduleJob.setValueHelper(valueHelper);
		return scheduleJobExecutor.invoke(scheduleJob);
	}
	
	/**
	 * 根据任务类名查找任务
	 * @param jobClass 任务类名
	 * @return
	 */
	public ScheduleJob findJob(String jobClass) {
		return scheduleJobsMap.get(jobClass);
	}
	
	public void setScheduleGroupList(List<ScheduleJobGroup> scheduleGroups) {
		scheduleGroupList = scheduleGroups;
		
		for(ScheduleJobGroup scheduleGroup : scheduleGroups){
			for (ScheduleJob scheduleJob : scheduleGroup.getJobs()) {
				String key = scheduleJob.getClass().getName();
				if (!scheduleJobsMap.containsKey(key)) {
					scheduleJobsMap.put(key, scheduleJob);
					scheduleJobList.add(scheduleJob);
				}
			}
		}
	}


	
	/**
	 * 类适配器
	 * ScheduleJob 和  org.quartz.Job类适配器 
	 * 
	 * quartz只能调用Job对象，而ScheduleJob与Job没有办毛钱关系。
	 * 因此使用TempJob类将ScheduleJob包装成Job类
	 * 
	 * @author tianjp
	 */
	public static class TempJob implements Job {

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			List<ScheduleJob> jobList = (List<ScheduleJob>) context.getMergedJobDataMap().get(SCHEDULE_JOBS);
			ValueHelper valueHeler = (ValueHelper) context.getMergedJobDataMap().get(SCHEDULE_JOBS_DATAS);
			ScheduleJobExecutor executor = (ScheduleJobExecutor) context.getMergedJobDataMap().get(
					SCHEDULE_JOB_EXECUTOR);
			for (ScheduleJob scheduleJob : jobList) {
				scheduleJob.setValueHelper(valueHeler);
				executor.invoke(scheduleJob);
			}
		}
	}
}
