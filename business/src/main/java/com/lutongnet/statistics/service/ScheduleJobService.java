package com.lutongnet.statistics.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.util.value.ValueHelper;
import com.lutongnet.statistics.top.ScheduleJob;
import com.lutongnet.statistics.top.ScheduleJobFactory;

@Service("scheduleJobService")
@Transactional
public class ScheduleJobService {
	private static Logger		log	= LoggerFactory.getLogger(ScheduleJobService.class);

	@Resource(name = "scheduleJobFactory")
	private ScheduleJobFactory	scheduleJobFactory;

	public void getJobList() {
		scheduleJobFactory.getJobs();
	}
	
	@Async
	public void invokeJob(String jobClass, ValueHelper valueHelper) {
		scheduleJobFactory.invokeJob(jobClass, valueHelper);
	}

	public void list(HttpServletRequest request) {
		List<ScheduleJob> scheduleJobList = scheduleJobFactory.getJobs();
		request.setAttribute("scheduleJobList", scheduleJobList);
	}
}
