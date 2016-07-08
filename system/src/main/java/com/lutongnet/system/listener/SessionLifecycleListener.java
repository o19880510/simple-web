package com.lutongnet.system.listener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.model.entity.User;
import com.lutongnet.system.model.vo.UserInfo;
import com.lutongnet.system.service.UserService;


public class SessionLifecycleListener implements HttpSessionListener {

	private Logger	log	= LoggerFactory.getLogger ( SessionLifecycleListener.class );

	public void sessionCreated ( HttpSessionEvent event ) {
		HttpSession session = event.getSession ( );
		log.debug ( "session create:" + session.getId ( ) );
	}

	@SuppressWarnings ( "unchecked" )
	public void sessionDestroyed ( HttpSessionEvent event ) {
		HttpSession session = event.getSession ( );
		log.debug ( "session destroy:" + session.getId ( ) );
		ServletContext ctx = session.getServletContext ( );
		Map<String, UserInfo> userHolder = ( Map<String, UserInfo> ) ctx.getAttribute ( AppConstant.USER_HOLDER );
		userHolder.remove ( session.getId ( ) );

		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext ( ctx );
		UserService userService = wctx.getBean ( "userService" , UserService.class );
		Collection<UserInfo> userList = userHolder.values ( );
		Set<String> useridSet = new HashSet<String> ( );
		for ( UserInfo ui : userList ){
			useridSet.add ( ui.getUserid ( ) );
		}

		UserInfo user = ( UserInfo ) session.getAttribute ( AppConstant.USER_INFO );
		if ( user != null ){
			if ( !useridSet.contains ( user.getUserid ( ) ) ){
				userService.updateLogoutInfo ( user.getUserid ( ) , User.Status.OFFLINE.name() );
			}
		}
	}

}
