package woo.study.web.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * javax.servlet.http.HttpServletRequest 帮助类
 * 
 * 获取请求的URI地址、项目名称、项目IP和端口
 * 
 * @author tianjp
 *
 */
public class HttpRequestUtils {
	
	/**
	 * 获取请求的URI,不包含ip、端口和项目名称
	 * eg：in > http://127.0.0.1:8080/project/user/login.do
	 * 	   out > user/login.do
	 * @param request
	 * @return 
	 */
	public static String getRequestUri(HttpServletRequest request) {
		String contextPath = getContextPath(request);
		String requestUri = request.getRequestURI().substring(contextPath.length() + 1); // 去掉上下文路径和"/"
		
		return requestUri;
	}
	
	/**
	 * 获取项目的URI
	 * eg: in > http://127.0.0.1:8080/project/user/login.do
	 * 	   out > /project
	 * @param request
	 * @return
	 */
	public static String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	
	/**
	 * 获取项目的URL
	 * eg：in > http://127.0.0.1:8080/project/user/login.do
	 * 	   out > http://127.0.0.1:8080/project/
	 * @param request
	 * @return
	 */
	public static String getProjectUrl(HttpServletRequest request){
		return getProjectDomain(request) + getContextPath(request);
	}
	
	/**
	 * eg：in > http://127.0.0.1:8080/project/user/login.do
	 * 	   out > http://127.0.0.1:8080/
	 * @param request
	 * @return
	 */
	public static String getProjectDomain(HttpServletRequest request){
		String projectDomain = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
		
		return projectDomain;
		
	}
	
	/**
	 * 获取项目的绝对路径
	 * eg: D:/server/tomcat6/webapps/ROOT/
	 * @param request
	 * @return
	 */
	public static String getProjectAbsoultPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * 判断请求内容是否为JSON格式
	 * @param request
	 * @return true 表示为JSON格式
	 */
	public static boolean isJsonContent(HttpServletRequest request){
		String contentType = request.getHeader("Content-Type");
		if( contentType == null || contentType.indexOf("application/json") == -1 ){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判断请求内容是否含有文件上传
	 * @param request
	 * @return true 表示请求体含有文件
	 */
	public static boolean isMultipart(HttpServletRequest request){
		String contentType = request.getHeader("Content-Type");
		if( contentType == null || contentType.indexOf("multipart/form-data") == -1 ){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判断是否是AJAX请求
	 * @param request
	 * @return true 表示是AJAX请求
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		
		boolean isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))
								|| request.getParameter("ajax") != null;
		
		return isAjaxRequest;
	}
	
	/**
	 * 获取请求参数Map
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParameterMap(HttpServletRequest request){
		
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> requestMap = new HashMap<String, String>();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			String[] values = entry.getValue();
			StringBuilder value = new StringBuilder();
			if(values != null){
				for(String temp : values){
					value.append(temp);
				}
			}
			
			requestMap.put(entry.getKey(), value.toString());
		}
		
		return requestMap;
	}
	
}
