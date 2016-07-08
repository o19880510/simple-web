package com.lutongnet.base.util.value;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lutongnet.base.util.HttpRequestUtils;


public class HttpRequestParamValueHelper extends CommonHelper {

	private Map<String, String> paramMap;
	

	public HttpRequestParamValueHelper(HttpServletRequest request) {
		
		paramMap = HttpRequestUtils.getParameterMap(request);
	}

	@Override
	public Object get(String key) {
		return paramMap.get(key);
	}

}
