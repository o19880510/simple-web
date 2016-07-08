package com.lutongnet.system.listener;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.model.vo.UserInfo;
import com.lutongnet.system.service.UserService;

/**
 * 系统启动时，加载数据字典
 *
 */
public class ServletContextParamsInitListener implements ServletContextListener {
	
	private static Logger log = LoggerFactory.getLogger(ServletContextParamsInitListener.class);
	
	private WebApplicationContext webApplicationContext;
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		UserService userService = webApplicationContext.getBean("userService", UserService.class);

		userService.updateUserStatus();
		
		// 当前正在使用的用户列表
		Map<String, UserInfo> userHolder = new HashMap<String, UserInfo>();
		servletContext.setAttribute(AppConstant.USER_HOLDER, userHolder);

		// 用户ID和SessionId关联
		Map<String, String> sessionidHolder = new HashMap<String, String>();
		servletContext.setAttribute(AppConstant.SESSIONID_HOLDER, sessionidHolder);
		
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}


}
