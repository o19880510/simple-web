package com.lutongnet.system.schedule;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("demoJobDailySchedule")
public class DemoJobDailySchedule extends ScheduleSupport implements Job, JobLevel1{

	private static Logger log	= LoggerFactory.getLogger(DemoJobDailySchedule.class);
	
	protected DemoJobDailySchedule() {
		super();
		setJob(this);
	}
	
	@Scheduled(cron="0 43 11 * * ?")
	// @Scheduled(fixedDelay=10000)
	public void myschedule(){

		log.debug("DemoJobDailySchedule schedule start.");
		super.schedule();
		log.debug("DemoJobDailySchedule schedule end.");
	}
	
	public boolean checkStatus() {
		log.debug("DemoJobDailySchedule checkStatus start.");
		log.debug("DemoJobDailySchedule checkStatus end.");
		return true;
	}

	public boolean run() {
		log.debug("DemoJobDailySchedule run start.");
		log.debug("DemoJobDailySchedule run end.");
		return true;
	}

	public boolean runOver() {
		log.debug("DemoJobDailySchedule runOver start.");
		log.debug("DemoJobDailySchedule runOver end.");
		return true;
	}

	public String getName() {
		return "demoJobDailySchedule";
	}

	@Override
	protected void setRelationship() {
		addChild(com.lutongnet.system.schedule.Demo1JobDailySchedule.class);
	}

}
