package woo.study.web.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import woo.study.web.system.annotation.MarkRequest;
import woo.study.web.system.util.HttpUtils;

public class MarkRequestAnnotationHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod h = (HandlerMethod) handler;
			MarkRequest markRequest = h.getMethodAnnotation(MarkRequest.class);
			if (markRequest != null) {
				String name = markRequest.value();
				String scope = markRequest.scope();
				String[] excludes = markRequest.excludes();
				HttpUtils.markRequestInfo(request, scope, name, excludes);
			}
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}
