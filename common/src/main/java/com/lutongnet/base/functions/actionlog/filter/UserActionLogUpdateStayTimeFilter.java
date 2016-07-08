package com.lutongnet.base.functions.actionlog.filter;

import java.io.IOException;






import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lutongnet.base.thread.ThreadLocalMaps;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.HttpRequestUtils;
import com.lutongnet.base.util.NumberUtils;
import com.lutongnet.base.wrapper.InfoLogServletResponse;
import com.lutongnet.base.cookies.CookieStore;
import com.lutongnet.base.functions.actionlog.constants.ThreadLocalConstants;
import com.lutongnet.base.functions.actionlog.service.UserActionLogService;

public class UserActionLogUpdateStayTimeFilter implements Filter{
	
	private static Logger log	= LoggerFactory.getLogger(UserActionLogUpdateStayTimeFilter.class);
	
	
	private WebApplicationContext webApplicationContext ;
	private ServletContext servletContext;
	private UserActionLogService	userActionLogService;
	private static String[] loginUriList = {};
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		servletContext = filterConfig.getServletContext();
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		userActionLogService = webApplicationContext.getBean("userActionLogService", UserActionLogService.class);
		
		String uri = filterConfig.getInitParameter("loginUriList");		
		if(uri != null)
		{
			loginUriList = uri.split("\\s+");
		}
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
		
		
		/**
		 * 异步请求不做任何处理
		 */
		boolean ajaxRequest = HttpRequestUtils.isAjaxRequest(httpRequest);
		if(ajaxRequest){
			filterChain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		
		boolean saveTimeLog = true;
		/**
		 * 校验URL，如果是登陆URL，不处理
		 */
		String requestUri = HttpRequestUtils.getRequestUri(httpRequest);		
		for (String skipUri : loginUriList) {
			if (requestUri.startsWith(skipUri)) {
				saveTimeLog = false;
				break;
			}
		}
		
		CookieStore cookieStore = new CookieStore(httpRequest, httpResponse);
		String inputLogStatus = cookieStore.get(ThreadLocalConstants.LOG_STATUS);
		
		/**
		 * 只有数据状态是200,才处理。302 301 300等其它状态一律不作处理
		 */
		if(saveTimeLog && String.valueOf(HttpServletResponse.SC_OK).equals(inputLogStatus)){
			
			String inputLogId = cookieStore.get(ThreadLocalConstants.LOG_ID);
			String startTimeString = cookieStore.get(ThreadLocalConstants.START_TIME);
			
			if(AssertHelper.isEmpty(inputLogId)){
				inputLogId = httpRequest.getParameter(ThreadLocalConstants.LOG_ID);
			}
			
			if( AssertHelper.isEmpty(startTimeString)){
				startTimeString = httpRequest.getParameter(ThreadLocalConstants.START_TIME);
			}
			
			log.debug("inputLogId:" + inputLogId);
			log.debug("startTimeString:" + startTimeString);
			
			
			if( AssertHelper.notEmpty(inputLogId) && AssertHelper.notEmpty(startTimeString)){
				
				long startTime = NumberUtils.parseLong(startTimeString);
				
				if(startTime > 0){
					userActionLogService.updateTime(inputLogId, startTime);
				}
			}
		}
		
		filterChain.doFilter(httpRequest, httpResponse);
		
		log.debug("OperationLogUpdateStayTimeFilter after doFilter!");
		
		//向客户端cookie保存log id和开始时间start time
		Object logId = ThreadLocalMaps.get(ThreadLocalConstants.LOG_ID);
		log.debug("OperationLogUpdateStayTimeFilter logId:" + logId);
		if(logId != null){
			cookieStore.set(ThreadLocalConstants.LOG_ID, String.valueOf(logId));
			cookieStore.set(ThreadLocalConstants.START_TIME, String.valueOf(DateTime.now().getMillis()));
			httpRequest.setAttribute(ThreadLocalConstants.START_TIME, String.valueOf(DateTime.now().getMillis()));
			
		}
		
		/**
		 * 设置数据状态：200或302
		 */
		InfoLogServletResponse response = (InfoLogServletResponse)httpResponse;
		if( AssertHelper.isEmpty(response.getRedirectLocation()) ){
			cookieStore.set(ThreadLocalConstants.LOG_STATUS, String.valueOf(HttpServletResponse.SC_OK) ); // 200
		}else{
			cookieStore.set(ThreadLocalConstants.LOG_STATUS, String.valueOf(HttpServletResponse.SC_FOUND) ); // 302
		}
		
		log.debug("OperationLogUpdateStayTimeFilter httpResponse:" + httpResponse);
	}

	@Override
	public void destroy() {
	}
	
}
