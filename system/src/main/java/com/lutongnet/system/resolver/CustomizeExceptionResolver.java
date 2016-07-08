package com.lutongnet.system.resolver;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.lutongnet.base.functions.actionlog.constants.ThreadLocalConstants;
import com.lutongnet.base.functions.actionlog2.config.UalBusinessHelper;
import com.lutongnet.base.message.ActionResult;
import com.lutongnet.base.thread.ThreadLocalMaps;
import com.lutongnet.base.util.HttpRequestUtils;
import com.lutongnet.base.util.HttpResponseUtils;
import com.lutongnet.system.annotation.Log;
import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.model.entity.ActionLog;
import com.lutongnet.system.model.entity.ActionLogDetail;
import com.lutongnet.system.model.vo.UserInfo;
import com.lutongnet.system.service.LogService;

/**
 * @author 凌剑东
 */
public class CustomizeExceptionResolver extends SimpleMappingExceptionResolver {

	private Logger		log	= LoggerFactory.getLogger(CustomizeExceptionResolver.class);

	@Resource(name = "logService")
	private LogService	logService;

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String content = "";
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));

		if (handler instanceof HandlerMethod) {
			HandlerMethod h = (HandlerMethod) handler;
			Log logAnnotation = h.getMethodAnnotation(Log.class);
			if (logAnnotation != null) {
				content = logAnnotation.value();

				UserInfo user = (UserInfo) request.getSession().getAttribute(AppConstant.USER_INFO);
				String actor = user == null ? "system" : user.getUserid();
				String uri = request.getRequestURI();
				String paramInfo = "";
				Map<String, String[]> params = request.getParameterMap();
				for (Map.Entry<String, String[]> entry : params.entrySet()) {
					String key = entry.getKey() + ":";
					String[] values = entry.getValue();
					for (int i = 0; i < values.length; ++i) {
						key += values[i] + ",";
					}
					key = key.substring(0, key.length() - 1);
					key += "<br/>";
					paramInfo += key;
				}

				ActionLog actionLog = new ActionLog();
				actionLog.setContent(content);
				actionLog.setActor(actor);
				actionLog.setType(ActionResult.Type.ERROR.name());
				actionLog.setParam(paramInfo);
				actionLog.setUri(uri);
				actionLog.setAddDate(DateTime.now());
				ActionLogDetail detail = new ActionLogDetail();
				detail.setContent(sw.toString());
				actionLog.setDetail(detail);
				logService.add(actionLog);

			}
		}
		
		ThreadLocalMaps.set(ThreadLocalConstants.EXCEPTION_INFO, sw.toString());
		UalBusinessHelper.setException(ex);

		log.error(content + " 操作异常：", ex);

		boolean isAjaxRequest = HttpRequestUtils.isAjaxRequest(request);
		if (isAjaxRequest) {
			try {
				HttpResponseUtils.writeStatus(response, "系统异常");
			} catch (IOException e) {
				log.error("", e);
			}
			return null;
		}
		return super.doResolveException(request, response, handler, ex);
	}

}
