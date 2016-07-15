package woo.study.web.common.functions.actionlog2.config;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

public class PathMatchUtil {

	private static final AntPathMatcher	PATH_MATCHER	= new AntPathMatcher();

	public static boolean isFullPath(String requestUri) {
		return requestUri.indexOf("*") == -1 && requestUri.indexOf("{") == -1;
	}
	
	public static boolean isPathWithVars(String requestUri) {
		return requestUri.indexOf("{") != -1;
	}

	public static boolean isMatch(String requestUri, String configUri) {
		return PATH_MATCHER.match(configUri, requestUri);
	}

	public static Map<String, String> getPathValues(String requestUri, String configUri) {
		try {
			return PATH_MATCHER.extractUriTemplateVariables(configUri, requestUri);
		} catch (Exception e) {
			return Collections.EMPTY_MAP;
		}
	}
	
	public static String getRequestUri(HttpServletRequest request){
		
		String requestUri = request.getRequestURI();
		int index = requestUri.indexOf("/", 1);
		return requestUri.substring(index);
	}
}
