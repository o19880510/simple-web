package com.lutongnet.system.schedule;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.util.ExceptionUtils;
import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.dao.ScheduleLogDao;
import com.lutongnet.system.datacenter.SystemDataManagementHelper;
import com.lutongnet.system.model.entity.ScheduleLog;

/**
 * 定时任务基础类
 * 
 * 维护任务的父任务和子任务关系，并触发子任务和校验父任务的运行状态
 * 运行任务，并记录日志
 * 
 * @author tianjp
 *
 */
@Transactional
@Deprecated
public abstract class ScheduleSupport implements ApplicationContextAware{
	
	private static final String		LOCAL_SCHEDULE_JOB_LEVEL	= "local.schedule.job.level";
	private static final String		JOB_LEVEL_1		= "JobLevel1";
	private static final String		JOB_LEVEL_2		= "JobLevel2";
	private static final String		JOB_LEVEL_3		= "JobLevel3";
	
	private static Logger log	= LoggerFactory.getLogger(ScheduleSupport.class);
	
	// 需要运行的任务
	private Job job;
	
	// 该任务的子任务列表，不能层重复
	private Set<ScheduleSupport> childrenSet;
	// 该任务的父任务列表，不能层重复
	private Set<ScheduleSupport> parentSet;
	
	// 保存父任务的运行状态
	// 当父任务运行完成后，在此列表中保存，
	// 当此列表的数目和父任务列表的数目相等时
	private Set<ScheduleSupport> runOverScheduleSet;
	
	
	private ApplicationContext applicationContext;
	
	// 提供记录日志
	@Resource(name = "scheduleLogDao")
	private ScheduleLogDao scheduleLogDao;
	
	protected ScheduleSupport() {
		childrenSet = new HashSet<ScheduleSupport>();
		parentSet = new HashSet<ScheduleSupport>();
		
		runOverScheduleSet = new HashSet<ScheduleSupport>();
	}
	
	
	/**
	 * 主方法
	 * 1、执行任务
	 * 2、记录任务运行日志
	 * 3、调用子任务
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public final void schedule(){
		
		log.debug("Job schedule start, job name:" + job.getClass().getName());
		
		boolean levelMatch = checkScheduleLevel();
		if(!levelMatch){
			return ;
		}
		
		ScheduleLog scheduleLogValue = new ScheduleLog();
		scheduleLogValue.setRunDate(DateTime.now());
		scheduleLogValue.setScheduleName(job.getClass().getName());
		
		boolean statusOk = checkParentStatus();
		if(!statusOk){
			scheduleLogValue.setScheduleStatus(AppConstant.ScheduledStatus._0_CHECK_PARENT);
			scheduleLogDao.save(scheduleLogValue);
			return ;
		}
		
		if(statusOk){
			try{
				statusOk = 	job.checkStatus();
			}catch(Exception e){
				log.error("Check status error!", e);
				
				scheduleLogValue.setScheduleStatus(AppConstant.ScheduledStatus._1_CHECK_JOB);
				scheduleLogValue.setMsg(ExceptionUtils.getErrorMsg(e, 500));
				
				scheduleLogDao.save(scheduleLogValue);
				return ;
			}
		}
		
		if(statusOk){
			try{
				statusOk = job.run();
			}catch(Exception e){
				log.error("Job run error!", e);
				
				scheduleLogValue.setScheduleStatus(AppConstant.ScheduledStatus._2_JOB_RUN);
				scheduleLogValue.setMsg(ExceptionUtils.getErrorMsg(e, 500));
				
				scheduleLogDao.save(scheduleLogValue);
				
				return ;
			}
		}
		
		if(statusOk){
			try{
				statusOk = job.runOver();
			}catch(Exception e){
				log.error("Job runOver error!", e);
				
				scheduleLogValue.setScheduleStatus(AppConstant.ScheduledStatus._3_JOB_RUN_OVER);
				scheduleLogValue.setMsg(ExceptionUtils.getErrorMsg(e, 500));
				
				scheduleLogDao.save(scheduleLogValue);
				
				return ;
			}
		}
		
		
		log.debug("Job schedule end");
		
		if(statusOk){
			runOverScheduleSet = new HashSet<ScheduleSupport>();
			
			scheduleLogValue.setScheduleStatus(AppConstant.ScheduledStatus._4_CALL_CHLIDREN);
			scheduleLogDao.save(scheduleLogValue);
			
			callChildren();
		}
	}
	
	public final void schedule(ScheduleSupport scheduleSupport){
		
		
		runOverScheduleSet.add(scheduleSupport);
		
		this.schedule();
	}
	
	/**
	 * 校验父任务的运行状态
	 * @return
	 */
	protected boolean checkParentStatus() {
		
		if(parentSet.size() == 0){
			return true;
		}
		
		return isAllParentsRunOver();
	}
	
	private void callChildren() {
		
		for(ScheduleSupport childrenSchedule :childrenSet ){
			childrenSchedule.schedule(this);
		}
		
	}
	
	protected void setJob(Job job){
		this.job = job;
	}
	
	public Job getJob(){
		
		return this.job;
	}
	

	private ScheduleSupport getBean(Class<ScheduleSupport> clazz){
		ScheduleSupport scheduleSupport = getApplicationContext().getBean(clazz);
		return scheduleSupport;
	}
	
	
	@PostConstruct
	private final void setRelationships(){
		setRelationship();
	}
	
	/**
	 * 设置任务的父任务和子任务
	 */
	protected void setRelationship(){
	}
	
	protected void addChild(Class clazz){
		ScheduleSupport scheduleSupport = getBean(clazz);
		addChildren(scheduleSupport);
	}
	
	protected void addParent(Class<ScheduleSupport> clazz){
		ScheduleSupport scheduleSupport = getBean(clazz);
		addParent(scheduleSupport);
	}
	
	private void addChildren(ScheduleSupport childSchedule) {
		if( childSchedule == this ){
			return ;
		}
		childrenSet.add(childSchedule);
		childSchedule.addParent(this);
	}
	
	private void addParent(ScheduleSupport parentSchedule) {
		
		if( parentSchedule == this ){
			return ;
		}
		
		parentSet.add(parentSchedule);
		parentSchedule.addChildren(parentSchedule);
	}
	
	private boolean isAllParentsRunOver(){
		if(parentSet.size() ==  runOverScheduleSet.size()){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断任务所属级别，如果任务级别与服务器级别匹配，任务就可以运行
	 * 服务器级别信息在config.properties文件中配置
	 * @return
	 */
	private boolean checkScheduleLevel(){
		Properties configProperties = SystemDataManagementHelper.getInstance().getConfigProperties();
		String jobLevel = configProperties.getProperty(LOCAL_SCHEDULE_JOB_LEVEL);
		
		if(JOB_LEVEL_1.equals(jobLevel)){
			return (job instanceof JobLevel1);
			
		}else if(JOB_LEVEL_2.equals(jobLevel)){
			return (job instanceof JobLevel2);
			
		}else if(JOB_LEVEL_3.equals(jobLevel)){
			return (job instanceof JobLevel3);
		}
		
		return false;
		
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
}

