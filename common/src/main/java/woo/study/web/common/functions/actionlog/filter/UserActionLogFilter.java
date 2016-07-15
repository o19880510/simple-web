package woo.study.web.common.functions.actionlog.filter;

import java.io.IOException;

import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import woo.study.web.common.functions.actionlog.datacenter.ActionLogDataManagementHelper;
import woo.study.web.common.functions.actionlog.model.vo.LogConfigValue;
import woo.study.web.common.functions.actionlog.service.UserActionLogService;
import woo.study.web.common.wrapper.InfoLogServletResponse;
import woo.study.web.common.wrapper.ResponseInfo;

public class UserActionLogFilter implements Filter{
	
	private static Logger log	= LoggerFactory.getLogger(UserActionLogFilter.class);
	
	private WebApplicationContext webApplicationContext ;
	private ServletContext servletContext;
	private UserActionLogService	userActionLogService;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		servletContext = filterConfig.getServletContext();
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		userActionLogService = webApplicationContext.getBean("userActionLogService", UserActionLogService.class);
		
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		Map<String, LogConfigValue> configValues = ActionLogDataManagementHelper.getInstance().getUserActionLogs();

		String requestUri = parseUri(request);
		LogConfigValue logConfigValue = configValues.get(requestUri);
		
		if(logConfigValue == null){
			String method = request.getMethod().toUpperCase();
			String key = method.toUpperCase()+requestUri;
			logConfigValue = configValues.get(key);
		}
		
		log.debug("requestUri:"+requestUri);
		log.debug("LOG_CONFIG_VALUE(logConfigValue):"+logConfigValue);
		
		if(logConfigValue != null){
			userActionLogService.save(request, logConfigValue);
		}
		
		
		filterChain.doFilter(request, servletResponse);
		
		
		
		ResponseInfo responseInfo = null;
		HttpServletResponse response  = (HttpServletResponse) servletResponse;
		if(servletResponse instanceof ResponseInfo){
			responseInfo = (ResponseInfo)(response);
		}else{
			responseInfo = new InfoLogServletResponse(response);
		}
		userActionLogService.update(responseInfo);
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	private String parseUri(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		int index = requestUri.indexOf("/", 1);
		return requestUri.substring(index);
	}
	
	
}



