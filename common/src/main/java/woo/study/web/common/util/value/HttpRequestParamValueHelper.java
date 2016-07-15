package woo.study.web.common.util.value;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import woo.study.web.common.util.HttpRequestUtils;


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
