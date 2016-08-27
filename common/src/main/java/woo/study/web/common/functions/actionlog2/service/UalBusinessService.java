package woo.study.web.common.functions.actionlog2.service;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import woo.study.web.common.functions.actionlog2.dao.UalBusinessDAO;
import woo.study.web.common.functions.actionlog2.model.entity.UalBusiness;
import woo.study.web.common.functions.actionlog2.model.vo.LogConfigValue;
import woo.study.web.common.util.ExceptionUtils;

@Service("ualBusinessService")
public class UalBusinessService {
	private static Logger log = LoggerFactory.getLogger(UalBusinessService.class);
	
	
	@Resource(name = "ualBusinessDAO")
	private UalBusinessDAO ualBusinessDAO;
	
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(String ualLogId, String userId, LogConfigValue logConfigValue, Serializable businessCode, Throwable exception){
		
		log.debug("UalBusinessService save ualLogId=" + ualLogId);
		log.debug("UalBusinessService save logConfigValue=" + logConfigValue);
		log.debug("UalBusinessService save businessCode=" + businessCode);
		log.debug("UalBusinessService save exception=" + exception.getMessage());
		
		UalBusiness ualBusiness = new UalBusiness();
		ualBusiness.setId(ualLogId);
		ualBusiness.setUserId(userId);
		ualBusiness.setActionCode(logConfigValue.getActionCode());
		if(businessCode != null){
			ualBusiness.setBusinessStatusCode(businessCode.toString());
		}
		ualBusiness.setExceptionInfo(ExceptionUtils.getErrorMsg(exception, 6000));
		ualBusiness.setTxnDate(DateTime.now());
		
		log.debug("UalBusinessService save ualBusiness=" + ualBusiness);
		
		ualBusinessDAO.save(ualBusiness);
	}
}
