package woo.study.web.system.schedule;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component("demo2JobDailySchedule")
public class Demo2JobDailySchedule extends ScheduleSupport implements Job{

	private static Logger log	= LoggerFactory.getLogger(Demo2JobDailySchedule.class);
	
	protected Demo2JobDailySchedule() {
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

}
