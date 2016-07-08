package com.lutongnet.statistics.top;

import java.util.List;

/**
 * 任务组
 * 
 * 包含批量任务和一个执行时间
 * 
 * @author tianjp
 *
 */
public class ScheduleJobGroup {
	private List<ScheduleJob>	jobs;
	private String				cron;

	public List<ScheduleJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<ScheduleJob> jobs) {
		this.jobs = jobs;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

}
