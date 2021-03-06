package woo.study.web.system.schedule;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class Demo1JobDailySchedule extends ScheduleSupport implements Job, JobLevel2{

	private static Logger log	= LoggerFactory.getLogger(Demo1JobDailySchedule.class);
	
	protected Demo1JobDailySchedule() {
		super();
		
		setJob(this);
	}

	public boolean checkStatus() {
		log.debug("checkStatus start.");
		log.debug("checkStatus end.");
		return true;
	}

	public boolean run() {
		log.debug(" run start.");
		log.debug(" run end.");
		return true;
	}

	public boolean runOver() {
		log.debug(" runOver start.");
		log.debug(" runOver end.");
		return true;
	}

	public String getName() {
		return "demo1JobDailySchedule";
	}
	
	protected void setRelationship() {
		addChild(woo.study.web.system.schedule.Demo2JobDailySchedule.class);
	}

}
