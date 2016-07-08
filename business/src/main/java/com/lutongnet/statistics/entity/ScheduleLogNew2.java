
package com.lutongnet.statistics.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.lutongnet.statistics.top.ScheduleJob;

@Entity
@Table ( name = "T_SCHEDULE_LOG_NEW" )
public class  ScheduleLogNew2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "ID" )
	private Integer id;
	
	@Column ( name = "JOB_CLASS" )
	private String jobClass;
	
	@Column ( name = "ERROR_MSG" )
	private String errorMsg;
	
	@Column ( name = "STATUS" )
	private String status;
	
	@Transient
	private String statusDesc;
	
	@Column ( name = "RUN_DATE" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime					runDate;
	
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}
	
	

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setRunDate(DateTime runDate){
		this.runDate = runDate;
	}

	public DateTime getRunDate(){
		return this.runDate;
	}
	
	public String getStatusDesc() {
		this.statusDesc = ScheduleJob.Status.valueOf(this.status).getDesc();
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@Override
	public String toString() {
		return "ScheduleLogStore [id=" + id + ", jobClass=" + jobClass + ", errorMsg=" + errorMsg + ", status="
				+ status + ", runDate=" + runDate + "]";
	}

}

