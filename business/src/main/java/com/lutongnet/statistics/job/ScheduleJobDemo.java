package com.lutongnet.statistics.job;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.util.ThreadUtils;
import com.lutongnet.base.util.parameter.ParameterFactory;
import com.lutongnet.statistics.service.ScheduleJobService;
import com.lutongnet.statistics.top.impl.CommonScheduleJob;

public class ScheduleJobDemo extends CommonScheduleJob {
	
	private static Logger log = LoggerFactory.getLogger(ScheduleJobDemo.class);
	
	@Resource(name = "scheduleJobService")
	private ScheduleJobService	scheduleJobService;


	public ScheduleJobDemo() {
		super();

		// 设置本任务需要的参数
		getParameters().add(ParameterFactory.DATE_START);
		getParameters().add(ParameterFactory.DATETIME_START);
		getParameters().add(ParameterFactory.TIME_START);

	}

	@Override
	public boolean verify() {
		return true;
	}

	@Override
	public boolean execute() {
		// 获取参数
		LocalDate startDate = getValueHelper().getLocalDate(ParameterFactory.DATE_START.getEngName());
		log.debug("ScheduleJobDemo execute start...");
		ThreadUtils.sleep(10 * 1000);
		log.debug("ScheduleJobDemo execute end...");
		return true;
	}

	@Override
	public boolean cleanData() {
		return true;
//		throw new RuntimeException("only for test!");
	}
}
