package woo.study.web.system.interceptor;

import java.util.Map;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import woo.study.web.common.message.ActionResult;
import woo.study.web.system.annotation.Log;
import woo.study.web.system.annotation.MarkRequest;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.model.entity.ActionLog;
import woo.study.web.system.model.vo.UserInfo;
import woo.study.web.system.service.LogService;
import woo.study.web.system.util.HttpUtils;

public class LogAnnotationHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log	= LoggerFactory.getLogger(LogAnnotationHandlerInterceptor.class);
	
	@Resource ( name = "logService" )
	private LogService	logService;
	
	@SuppressWarnings ( "unchecked" )
	@Override
	public void postHandle ( HttpServletRequest request , HttpServletResponse response , Object handler , ModelAndView modelAndView ) throws Exception {
		
		if ( handler instanceof HandlerMethod ){
			HandlerMethod h = ( HandlerMethod ) handler;

			Log logAnnotation = h.getMethodAnnotation ( Log.class );
			if ( logAnnotation != null ){
				String content = logAnnotation.value ( );
				String uri = request.getRequestURI ( );
				UserInfo user = ( UserInfo ) request.getSession ( ).getAttribute ( AppConstant.USER_INFO );
				String actor = user == null ? "system" : user.getUserid ( );
				String paramInfo = "";
				Map<String, String [ ]> params = request.getParameterMap ( );
				for ( Map.Entry<String, String [ ]> entry : params.entrySet ( ) ){
					String key = entry.getKey ( ) + ":";
					String [ ] values = entry.getValue ( );
					for ( int i = 0 ; i < values.length ; ++i ){
						key += values [ i ] + ",";
					}
					key = key.substring ( 0 , key.length ( ) - 1 );
					key += "<br/>";
					paramInfo += key;
				}

				ActionLog actionLog = new ActionLog ( );
				actionLog.setContent ( content );
				actionLog.setActor ( actor );
				actionLog.setType ( ActionResult.Type.SUCCESS.name() );
				actionLog.setParam ( paramInfo );
				actionLog.setUri ( uri );
				actionLog.setAddDate ( DateTime.now ( ) );
				logService.add ( actionLog );
			}
		}
		
		super.postHandle ( request , response , handler , modelAndView );
	}
}
