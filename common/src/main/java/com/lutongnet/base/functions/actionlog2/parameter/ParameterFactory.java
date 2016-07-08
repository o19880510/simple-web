package com.lutongnet.base.functions.actionlog2.parameter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lutongnet.base.util.HttpRequestUtils;
import com.lutongnet.base.util.value.ValueHelper;

public class ParameterFactory {

	public static RequestParameter getRequestParameter(HttpServletRequest request) {
		return ParameterFactory.getRequestParameter(request, null, null);
	}

	public static RequestParameter getRequestParameter(HttpServletRequest request, Map<String, String> configParamValues) {
		return ParameterFactory.getRequestParameter(request, configParamValues, null);
	}

	public static RequestParameter getRequestParameter(Map<String, String> pathParamValues, HttpServletRequest request) {
		return ParameterFactory.getRequestParameter(request, null, pathParamValues);
	}

	public static RequestParameter getRequestParameter(HttpServletRequest request,
			Map<String, String> configParamValues, Map<String, String> pathParamValues) {

		if (HttpRequestUtils.isJsonContent(request)) {
			return new JSONRequestParameter(request, configParamValues, pathParamValues);
		} else if (HttpRequestUtils.isMultipart(request)) {
			return new MultipartRequestParameter(request, configParamValues, pathParamValues);
		}

		return new DefaultRequestParameter(request, configParamValues, pathParamValues);
	}

	public static ValueHelper getValueHelper(HttpServletRequest request) {
		return getValueHelper(request, null, null);
	}

	public static ValueHelper getValueHelper(HttpServletRequest request, Map<String, String> configParamValues) {
		return getValueHelper(request, configParamValues, null);
	}

	public static ValueHelper getValueHelper(Map<String, String> pathParamValues, HttpServletRequest request) {
		return getValueHelper(request, null, pathParamValues);
	}

	public static ValueHelper getValueHelper(HttpServletRequest request, Map<String, String> configParamValues,
			Map<String, String> pathParamValues) {
		RequestParameter parameter = ParameterFactory.getRequestParameter(request, configParamValues, pathParamValues);
		return new RequestParameterValueHelper(parameter);
	}
}
