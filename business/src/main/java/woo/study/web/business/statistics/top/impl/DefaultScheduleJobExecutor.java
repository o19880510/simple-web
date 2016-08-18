package woo.study.web.business.statistics.top.impl;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import woo.study.web.business.statistics.entity.ScheduleLogNew2;
import woo.study.web.business.statistics.service.ScheduleLogStoreService;
import woo.study.web.business.statistics.top.ScheduleJob;
import woo.study.web.business.statistics.top.ScheduleJobExecutor;
import woo.study.web.business.statistics.top.ScheduleJob.Status;
import woo.study.web.common.util.ExceptionUtils;
import woo.study.web.common.util.value.EmptyGettable;
import woo.study.web.common.util.value.ValueHelperFactory;

/**
 * 
 * 任务调用类
 * 
 * 1、调用任务
 * 2、记录任务执行的状态并持久化
 * 
 * @author tianjp
 *
 */
@Component("scheduleJobExecutor")
public class DefaultScheduleJobExecutor implements ScheduleJobExecutor {

	private static Logger		log	= LoggerFactory.getLogger(DefaultScheduleJobExecutor.class);

	@Resource(name = "scheduleLogStoreService")
	private ScheduleLogStoreService	scheduleLogStoreService;

	@Override
	public boolean invoke(ScheduleJob job) {
		ScheduleLogNew2 scheduleLogStore = new ScheduleLogNew2();
		scheduleLogStore.setJobClass(job.getClass().getName());
		scheduleLogStore.setRunDate(DateTime.now());

		Status jobStatus = null;
		String errorMsg = null;

		try {

			jobStatus = Status.VERIFY_FAILURE;
			boolean verifySuccess = invokeVerify(job);

			if (verifySuccess) {

				jobStatus = Status.CLEAN_DATA_FAILURE;
				boolean cleanSuccess = invokeCleanData(job);

				if (cleanSuccess) {
					boolean success = invokeExecute(job);
					jobStatus = success ? Status.SUCCESS : Status.FAILURE;
				}
			}
		} catch (Exception e) {
			log.error("invokeJob error, job is : " + job.getClass(), e);
			jobStatus = Status.ERROR;
			errorMsg = ExceptionUtils.getErrorMsg(e, 400);// 数据库中error msg长度为400
		}

		scheduleLogStore.setStatus(jobStatus.name());
		scheduleLogStore.setErrorMsg(errorMsg);

		scheduleLogStoreService.save(scheduleLogStore);
		job.setValueHelper(ValueHelperFactory.empty());
		
		return Status.SUCCESS == jobStatus;
	}

	@Override
	public void invoke(ScheduleJob... jobs) {
		for (ScheduleJob tempJob : jobs) {
			invoke(tempJob);
		}

	}

	@Override
	public void invoke(List<ScheduleJob> jobs) {
		for (ScheduleJob tempJob : jobs) {
			invoke(tempJob);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private boolean invokeVerify(ScheduleJob job){
		return job.verify();
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private boolean invokeCleanData(ScheduleJob job){
		return job.cleanData();
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private boolean invokeExecute(ScheduleJob job){
		return job.execute();
	}

}
