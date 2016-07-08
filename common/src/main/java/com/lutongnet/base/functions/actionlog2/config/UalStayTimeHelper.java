package com.lutongnet.base.functions.actionlog2.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lutongnet.base.cookies.CookieStore;
import com.lutongnet.base.util.AssertHelper;

public class UalStayTimeHelper {

	private static final String		HEADER_NAME	= "X-UAL-Log";

	private static final String		HTTP_STATUS	= "x-hs";
	private static final String		LOG_ID		= "x-li";
	private static final String		USER_ID		= "x-ui";
	private static final String		ACTION_CODE	= "x-ac";
	private static final String		START_TIME	= "x-st";

	private static final String[]	KEYS		= { HTTP_STATUS, LOG_ID, USER_ID, ACTION_CODE, START_TIME };

	private HttpServletRequest		request;
	private Map<String, String>		dataMap;
	private boolean hasLogData;

	public UalStayTimeHelper(HttpServletRequest request) {
		super();
		this.request = request;
		this.hasLogData = true;

		dataMap = new HashMap<String, String>();
		parse();
	}
	
	public boolean hasLogData(){
		return hasLogData;
	}

	public String getHttpStatus() {
		return dataMap.get(HTTP_STATUS);
	}

	public String getLogId() {
		return dataMap.get(LOG_ID);
	}

	public String getUserId() {
		return dataMap.get(USER_ID);
	}

	public String getActionCode() {
		return dataMap.get(ACTION_CODE);
	}

	public String getStartTime() {
		return dataMap.get(START_TIME);
	}

	private void parse() {

		Cookie[] cookies = request.getCookies();
		
		if(cookies != null){
			for (Cookie cookie : cookies) {
				dataMap.put(cookie.getName(), cookie.getValue());
			}
		}

		if (dataMap.get(LOG_ID) == null) {
			parseHead(request.getHeader(HEADER_NAME));
		}
		
		if (dataMap.get(LOG_ID) == null) {
			for (String key : KEYS) {
				String value = request.getParameter(key);
				dataMap.put(key, value);
			}
		}
		
		if (dataMap.get(LOG_ID) == null) {
			hasLogData = false;
		}
	}

	private void parseHead(String data) {

		String headerContent = data;

		if (AssertHelper.notEmpty(headerContent)) {
			String[] keyValues = headerContent.split(";");
			
			if(keyValues == null){
				return;
			}
			
			for (String keyValue : keyValues) {
				String[] oneKeyValue = keyValue.split("=");
				
				if(oneKeyValue != null && oneKeyValue.length > 1){
					String key = oneKeyValue[0];
					String value = oneKeyValue[1];
					if(AssertHelper.notEmpty(key) && AssertHelper.notEmpty(value)){
						dataMap.put(key.trim(), value);
					}
				}
			}
		}
	}

	public static void setEmptyToResponse(HttpServletRequest request, HttpServletResponse response){
		
		CookieStore cookieStore = new CookieStore(request, response);
		for(String key : KEYS){
			addCookie(cookieStore, key, null);
		}
	}
	
	public static void setToResponse(HttpServletRequest request, HttpServletResponse response, String httpStatus, String logId, String startTime,
			String userId, String actionCode) {

		{ // Set to Cookies
			
			CookieStore cookieStore = new CookieStore(request, response);
			addCookie(cookieStore, HTTP_STATUS, httpStatus);
			addCookie(cookieStore, LOG_ID, logId);
			addCookie(cookieStore, USER_ID, userId);
			addCookie(cookieStore, ACTION_CODE, actionCode);
			addCookie(cookieStore, START_TIME, startTime);
		}

		setToHeader(response, httpStatus, logId, startTime, userId, actionCode);

	}

	private static void addCookie(CookieStore cookieStore, String key, String value) {
		if (AssertHelper.notEmpty(value)) {
			cookieStore.set(key, value);
		}else{
			cookieStore.set(key, "", 0);
		}
	}

	private static void setToHeader(HttpServletResponse response, 
			String httpStatus, String logId, String startTime, String userId, String actionCode) {
		StringBuilder data = new StringBuilder();

		buildData(data, HTTP_STATUS, httpStatus);
		buildData(data, LOG_ID, logId);
		buildData(data, USER_ID, userId);
		buildData(data, ACTION_CODE, actionCode);
		buildData(data, START_TIME, startTime);

		response.addHeader(HEADER_NAME, data.toString());
	}

	private static void buildData(StringBuilder data, String key, String value) {
		if (value != null) {
			data.append(" ").append(key).append("=").append(value).append(";");
		}
	}
	
	@Override
	public String toString() {
		return "UalStayTimeHelper [dataMap=" + dataMap + ", hasLogData=" + hasLogData + "]";
	}
}
