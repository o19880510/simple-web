package com.lutongnet.base.functions.actionlog2.parameter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mock.web.MockHttpSession;

import com.lutongnet.base.util.AssertHelper;

public abstract class CommonRequestParameter implements RequestParameter{
	
	private Map<String, String> configParamValues;
	private Map<String, String> requestParamValues;
	private Map<String, String> pathParamValues;
	private Cookie[] cookies;
	private Map<String, String> headParamValues;
	private HttpSession session;
	
	
	public CommonRequestParameter(HttpServletRequest request) {
		super();
		
		configParamValues = Collections.EMPTY_MAP;
		requestParamValues = setRequestParams(request);
		pathParamValues = Collections.EMPTY_MAP;
		headParamValues = getHeadParamValues();
		session = request.getSession(false);
		
		cookies = request.getCookies();
		
		if(session == null){
			session  = new MockHttpSession();
		}
		
		if(cookies == null){
			cookies = new Cookie[]{};
		}
	}


	public CommonRequestParameter(Map<String, String> configParamValues, HttpServletRequest request) {
		this(request);
		this.configParamValues = configParamValues;
	}
	
	public CommonRequestParameter(HttpServletRequest request, Map<String, String> pathParamValues) {
		this(request);
		this.pathParamValues = pathParamValues;
	}
	
	public CommonRequestParameter(HttpServletRequest request, Map<String, String> configParamValues, 
			Map<String, String> pathParamValues) {
		this(request);
		this.configParamValues = configParamValues;
		this.pathParamValues = pathParamValues;
	}


	@Override
	public String getParameter(String name) {
		
		if(AssertHelper.isEmpty(name)){
			return null;
		}
		
		String value = getFromConfig(name);
		if(value != null){
			return value;
		}
		
		value = getFromRequestBodyAndQueryString(name);
		if(value != null){
			return value;
		}
		
		value = getFromPath(name);
		if(value != null){
			return value;
		}
		
		value = getFromCookies(name);
		if(value != null){
			return value;
		}

		value = getFromHead(name);
		if(value != null){
			return value;
		}
		
		value = getFromSession(name);
		if(value != null){
			return value;
		}
		
		return null;
	}
	
	protected String getFromConfig(String name){
		if(configParamValues != null){
			return configParamValues.get(name);
		}
		return null;
	}
	
	protected String getFromRequestBodyAndQueryString(String name){
		return requestParamValues.get(name);
	}
	
	protected String getFromPath(String name){
		if(pathParamValues != null){
			return pathParamValues.get(name);
		}
		return null;
	}
	
	protected String getFromCookies(String name){
		for(Cookie cookie : cookies){
			if(name.equals(cookie.getName())){
				return cookie.getValue();
			}
		}
		return null;
	}
	protected String getFromHead(String name){
		if(headParamValues != null){
			return headParamValues.get(name);
		}
		return null;
	}
	
	protected String getFromSession(String name){
		Object valueObj = session.getAttribute(name);
		if(valueObj != null){
			return valueObj.toString();
		}
		return null;
	}
	
	
	protected Map<String, String> getHeadParamValues(){
		return Collections.EMPTY_MAP;
	}
	
	protected Map<String, String> setRequestParams(HttpServletRequest request){
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

	@Override
	public String toString() {
		String sessionAttriNames = Arrays.toString(Collections.list(session.getAttributeNames()).toArray());
		
		return "CommonRequestParameter [configParamValues=" + configParamValues + ", request=" + requestParamValues
				+ ", pathParamValues=" + pathParamValues + ", cookies=" + Arrays.toString(cookies)
				+ ", headParamValues=" + headParamValues + ", session=" + sessionAttriNames + "]";
	}
	
}
