package com.lutongnet.statistics.vo.req;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.lutongnet.system.model.request.CommonReq;

public class JobLogReq extends CommonReq {

	private String		jobClass;
	private String		status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private DateTime	startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private DateTime	endDate;

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "JobLogReq [jobClass=" + jobClass + ", status=" + status + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

}
