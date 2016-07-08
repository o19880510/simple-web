package com.lutongnet.system.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table ( name = "t_schedule_log" )
@Deprecated
public class ScheduleLog implements Serializable
{

    private static final long serialVersionUID = 7794512522174826358L;
    
    //编号
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer id;
    
    //任务名称
    @Column ( name = "schedule_name" )
    private String scheduleName;
    
    //状态
    @Column ( name = "schedule_status" )
    private Integer scheduleStatus;
    
    //信息
    @Column ( name = "msg" )
    private String msg;
    
    @Column ( name = "run_date" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
    private DateTime runDate;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getScheduleName()
    {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName)
    {
        this.scheduleName = scheduleName;
    }

    public Integer getScheduleStatus()
    {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus)
    {
        this.scheduleStatus = scheduleStatus;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public DateTime getRunDate() {
		return runDate;
	}

	public void setRunDate(DateTime runDate) {
		this.runDate = runDate;
	}

	@Override
    public String toString()
    {
        return "ScheduleLogValue [id=" + id + ", msg=" + msg + ", runDate="
                + runDate + ", scheduleName=" + scheduleName + ", scheduleStatus="
                + scheduleStatus + "]";
    }
    
    
}
