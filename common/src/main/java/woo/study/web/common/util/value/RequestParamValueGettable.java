package woo.study.web.common.util.value;


import java.util.Map;

import javax.servlet.ServletRequest;

import woo.study.web.common.util.HttpRequestUtils;


public class RequestParamValueGettable implements ValueGettable{

	private Map<String, String> paramMap;
	

	public RequestParamValueGettable(ServletRequest request) {
		
		paramMap = HttpRequestUtils.getParameterMap(request);
	}

	@Override
	public Object get(String key) {
		return paramMap.get(key);
	}

}
