package com.lutongnet.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware{
	
	private static ApplicationContext APP_CONTEXT;
	
	public static ApplicationContext getApplicationContext(){
		return APP_CONTEXT;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		APP_CONTEXT = appContext;
	}
	
}
