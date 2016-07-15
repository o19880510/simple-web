package woo.study.web.common.functions.actionlog2.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import woo.study.web.common.functions.actionlog2.config.UalStayTimeHelper;
import woo.study.web.common.functions.actionlog2.dao.UalStayTimeDAO;
import woo.study.web.common.functions.actionlog2.model.entity.UalStayTime;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.NumberUtils;

@Service("ualStayTimeService")
public class UalStayTimeService {
	private static Logger log = LoggerFactory.getLogger(UalStayTimeService.class);
	
	@Resource(name = "ualStayTimeDAO")
	private UalStayTimeDAO ualStayTimeDAO;
	
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(UalStayTimeHelper ualStayTimeHelper){
		log.debug("UalStayTimeService save  ualStayTimeHelper=" + ualStayTimeHelper);
		
		String inputLogId = ualStayTimeHelper.getLogId();
		String startTimeString = ualStayTimeHelper.getStartTime();
		String userId = ualStayTimeHelper.getUserId();
		String actionCode = ualStayTimeHelper.getActionCode();
		
		if( AssertHelper.notEmpty(inputLogId) && AssertHelper.notEmpty(startTimeString)){
			
			long startTime = NumberUtils.parseLong(startTimeString);
			if(startTime > 0){
				
				long endTime = DateTime.now().getMillis();
				long stayTimeLong = (endTime - startTime)/1000;
				
				UalStayTime stayTime = new UalStayTime();
				
				stayTime.setId(inputLogId);
				stayTime.setUserId(userId);
				stayTime.setActionCode(actionCode);
				stayTime.setStayTime(stayTimeLong);
				
				stayTime.setTxnDate(DateTime.now());
				log.debug("UalStayTimeService save  stayTime=" + stayTime);
				
				ualStayTimeDAO.save(stayTime);
			}
		}
	}
	
}
