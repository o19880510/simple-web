package woo.study.web.common.functions.actionlog2.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import woo.study.web.common.functions.actionlog2.config.PathMatchUtil;
import woo.study.web.common.functions.actionlog2.config.PrivateUalBusinessHelper;
import woo.study.web.common.functions.actionlog2.config.Switch;
import woo.study.web.common.functions.actionlog2.config.UalBusinessHelper;
import woo.study.web.common.functions.actionlog2.config.UalConfig;
import woo.study.web.common.functions.actionlog2.config.UalIdUtil;
import woo.study.web.common.functions.actionlog2.datacenter.ActionLogDataManagementHelper;
import woo.study.web.common.functions.actionlog2.model.vo.LogConfigParamValue;
import woo.study.web.common.functions.actionlog2.model.vo.LogConfigValue;
import woo.study.web.common.functions.actionlog2.parameter.ParameterFactory;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.value.ValueHelper;

@Service("ualBaseFilterService")
public class UalBaseFilterService implements FilterService{
	
	private static final Logger	log	= LoggerFactory.getLogger(UalBaseFilterService.class);
	
	@Resource(name = "ualBaseService")
	private UalBaseService ualBaseService;
	
	@Resource(name = "ualBusinessService")
	private UalBusinessService ualBusinessService;
	
	@Override
	public boolean beforeCheck(HttpServletRequest request, HttpServletResponse response) {
		
		if( UalConfig.getLogSwitch() == Switch.OFF){
			return false;
		}
		
		return checkUriMacth(request);
	}

	@Override
	public void beforeDoFilter(HttpServletRequest request, HttpServletResponse response) {
		String ualLogId = UalIdUtil.getUalId();
		PrivateUalBusinessHelper.setLogId(ualLogId);
		
		LogConfigValue logConfigValue = PrivateUalBusinessHelper.getLogConfigValue();
		ValueHelper valueHelper = PrivateUalBusinessHelper.getValueHelper();
		
		
		String recordFlag = logConfigValue.getRecordFlag();
		if (AssertHelper.notEmpty(recordFlag)) {
			
			String requestRecordString = request.getParameter(recordFlag);
			String valueHelperRecordString = valueHelper.getString(recordFlag);
			log.debug("UalBaseFilterService beforeDoFilter ualLogId=" + ualLogId);
			log.debug("UalBaseFilterService beforeDoFilter requestRecordString=" + requestRecordString);
			log.debug("UalBaseFilterService beforeDoFilter valueHelperRecordString=" + valueHelperRecordString);
		}
		
		final String userId = PrivateUalBusinessHelper.getUserId();
		
		ualBaseService.save(ualLogId, userId, logConfigValue, valueHelper);
	}

	@Override
	public boolean afterCheck(HttpServletRequest request, HttpServletResponse response) {
		
		if( UalConfig.getLogSwitch() == Switch.OFF){
			return false;
		}
		
		LogConfigValue logConfigValue = PrivateUalBusinessHelper.getLogConfigValue();
		if(logConfigValue == null){
			return false;
		}
		
		return true;
	}

	@Override
	public void afterDoFilter(HttpServletRequest request, HttpServletResponse response) {
		
		String ualLogId = PrivateUalBusinessHelper.getLogId();
		String userId = PrivateUalBusinessHelper.getUserId();
		LogConfigValue logConfigValue = PrivateUalBusinessHelper.getLogConfigValue();
		
		Serializable businessCode = UalBusinessHelper.getBusinessCode();
		Throwable exception =  UalBusinessHelper.getException();
		
		ualBusinessService.save(ualLogId, userId, logConfigValue, businessCode, exception );
	}
	
	private boolean checkUriMacth(HttpServletRequest request){
		String requestUri = PathMatchUtil.getRequestUri(request);
		log.debug("UalBaseFilterService checkUriMacth requestUri:" + requestUri);
		
		String targetUri = "*" + requestUri;
		String method = request.getMethod().toUpperCase();
		String targetUriMethod = method.toUpperCase() + requestUri;
		log.debug("UalBaseFilterService checkUriMacth targetUri=" + targetUri);
		log.debug("UalBaseFilterService checkUriMacth targetUriMethod=" + targetUriMethod);
		
		LogConfigValue targetLogConfigValue = null;
		{
			
			Map<String, LogConfigValue> configValues = ActionLogDataManagementHelper.getInstance().getUserActionLogsFullPath();
			
			targetLogConfigValue = configValues.get(targetUri);
			
			if (targetLogConfigValue == null) {
				targetLogConfigValue = configValues.get(targetUriMethod);
			}
		}
		
		Map<String, String> pathParamValues = null;
		{
			if(targetLogConfigValue == null){
				
				List<LogConfigValue> configValues = ActionLogDataManagementHelper.getInstance().getUserActionLogsMatchPath();
				
				for(LogConfigValue configValue : configValues){
					String matchUrl = configValue.getMatchUrl();
					String matchRequestUri = "";
					
					log.debug("UalBaseFilterService checkUriMacth temp-matchUrl=" + matchUrl);
					if ( PathMatchUtil.isMatch(targetUriMethod, matchUrl) ) {
						log.debug("UalBaseFilterService checkUriMacth temp-targetUriMethod=" + targetUriMethod);
						
						targetLogConfigValue = configValue;
						matchRequestUri = targetUriMethod;
						
					}else if(PathMatchUtil.isMatch(targetUri, matchUrl)){
						log.debug("UalBaseFilterService checkUriMacth temp-targetUri=" + targetUri);
						
						targetLogConfigValue = configValue;
						matchRequestUri = targetUri;
					}
					
					
					
					if(targetLogConfigValue != null){
						
						if(PathMatchUtil.isPathWithVars(matchUrl)){
							pathParamValues = PathMatchUtil.getPathValues(matchRequestUri, matchUrl);
						}
						
						break;
					}
				}
			}
		}

		log.debug("UalBaseFilterService checkUriMacth 3-requestMethod:" + request.getMethod());
		log.debug("UalBaseFilterService checkUriMacth 3-requestUri:" + requestUri);
		log.debug("UalBaseFilterService checkUriMacth 3-targetLogConfigValue=" + targetLogConfigValue);
		
		
		if (targetLogConfigValue != null) {
			
			Map<String, String> configParamValues = targetLogConfigValue.getConfigParamValues();
			ValueHelper valueHelper = ParameterFactory.getValueHelper(request, configParamValues, pathParamValues);

			// 获取字段值，判断是否需要记录日志
			String recordFlag = targetLogConfigValue.getRecordFlag();
			if (AssertHelper.notEmpty(recordFlag)) {
				
				String requestRecordString = request.getParameter(recordFlag);
				String valueHelperRecordString = valueHelper.getString(recordFlag);
				log.debug("UalBaseFilterService checkUriMacth requestRecordString=" + requestRecordString);
				log.debug("UalBaseFilterService checkUriMacth valueHelperRecordString=" + valueHelperRecordString);
				
				if(UalBaseService.RECORD_NOT_ALLOW.equals(valueHelper.getString(recordFlag))){
					return false;
				}
			}
			
			
			String userId = null;
			{ // 设置用户ID
				HttpSession session = request.getSession(false);
				if(session != null){
					userId = (String) session.getAttribute("USER_ID");
				}
				log.debug("UalBaseFilterService checkUriMacth session userId=" + userId);
				
				LogConfigParamValue userIdParam = targetLogConfigValue.getUserIdParam();
				if(userIdParam != null){
					String requestUserId = valueHelper.getString(userIdParam.getName());
					log.debug("UalBaseFilterService checkUriMacth request requestUserId=" + requestUserId);
					
					if (AssertHelper.notEmpty(userId)) {
						userId = requestUserId;
					}
				}
			}
			
			PrivateUalBusinessHelper.setLogConfigValue(targetLogConfigValue);
			PrivateUalBusinessHelper.setValueHelper(valueHelper);
			PrivateUalBusinessHelper.setUserId(userId);
			
			return true;
		}
		
		return false;
	}
}
