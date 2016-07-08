package com.lutongnet.base.functions.actionlog2.service;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lutongnet.base.functions.actionlog2.config.PrivateUalBusinessHelper;
import com.lutongnet.base.functions.actionlog2.config.Switch;
import com.lutongnet.base.functions.actionlog2.config.UalConfig;
import com.lutongnet.base.functions.actionlog2.config.UalStayTimeHelper;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.HttpRequestUtils;
import com.lutongnet.base.wrapper.InfoLogServletResponse;

@Service("ualStayTimeFilterService")
public class UalStayTimeFilterService implements FilterService {

	private static final Logger	log	= LoggerFactory.getLogger(UalStayTimeFilterService.class);

	@Resource(name = "ualStayTimeService")
	private UalStayTimeService	ualStayTimeService;

	@Override
	public boolean beforeCheck(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("UalStayTimeFilterService beforeCheck");
		if( UalConfig.getStayTimeSwitch() == Switch.OFF){
			return false;
		}

		// 异步请求不做任何处理
		boolean ajaxRequest = HttpRequestUtils.isAjaxRequest(request);
		if (ajaxRequest) {
			return false;
		}
		
		String ualLogId = PrivateUalBusinessHelper.getLogId();
		if(AssertHelper.isEmpty(ualLogId)){
			return false;
		}

		UalStayTimeHelper ualStayTimeHelper = new UalStayTimeHelper(request);
		log.debug("UalStayTimeFilterService ualStayTimeHelper=" + ualStayTimeHelper);
		
		if (!ualStayTimeHelper.hasLogData()) {
			return false;
		}

		String inputLogStatus = ualStayTimeHelper.getHttpStatus();
		// 只有数据状态是200,才处理。302 301 300等其它状态一律不作处理
		log.debug("UalStayTimeFilterService !ualStayTimeHelper.hasLogData()=" + !String.valueOf(HttpServletResponse.SC_OK).equals(inputLogStatus));
		if (!String.valueOf(HttpServletResponse.SC_OK).equals(inputLogStatus)) {
			return false;
		}

		return true;
	}

	@Override
	public void beforeDoFilter(HttpServletRequest request, HttpServletResponse response) {
		
		UalStayTimeHelper ualStayTimeHelper = new UalStayTimeHelper(request);
		log.debug("UalStayTimeFilterService beforeDoFilter-ualStayTimeHelper=" + ualStayTimeHelper);
		
		ualStayTimeService.save(ualStayTimeHelper);
		
		UalStayTimeHelper.setEmptyToResponse(request, response);
	}

	@Override
	public boolean afterCheck(HttpServletRequest request, HttpServletResponse response) {
		log.debug("UalStayTimeFilterService afterCheck");
		
		if( UalConfig.getStayTimeSwitch() == Switch.OFF){
			return false;
		}
		
		/**
		 * 异步请求不做任何处理
		 */
		boolean ajaxRequest = HttpRequestUtils.isAjaxRequest(request);
		if (ajaxRequest) {
			return false;
		}
		
		String ualLogId = PrivateUalBusinessHelper.getLogId();
		if(AssertHelper.isEmpty(ualLogId)){
			return false;
		}

		return true;
	}

	@Override
	public void afterDoFilter(HttpServletRequest request, HttpServletResponse httpResponse) {
		
		log.debug("UalStayTimeFilterService afterDoFilter");
		
		String httpStatus = String.valueOf(HttpServletResponse.SC_OK);
		
		InfoLogServletResponse response = (InfoLogServletResponse)httpResponse;
		if( AssertHelper.notEmpty(response.getRedirectLocation()) ){
			httpStatus = String.valueOf(HttpServletResponse.SC_FOUND);
		}
		
		String ualLogId = PrivateUalBusinessHelper.getLogId();
		String userId = PrivateUalBusinessHelper.getUserId();
		String actionCode = PrivateUalBusinessHelper.getLogConfigValue().getActionCode();
		long startTime = DateTime.now().getMillis();
		
		UalStayTimeHelper.setToResponse(request, response, httpStatus, ualLogId, String.valueOf(startTime), userId, actionCode);
	}

}
