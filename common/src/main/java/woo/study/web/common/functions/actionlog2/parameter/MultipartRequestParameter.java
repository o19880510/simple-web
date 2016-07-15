package woo.study.web.common.functions.actionlog2.parameter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultipartRequestParameter extends CommonRequestParameter {


	public MultipartRequestParameter(HttpServletRequest request) {
		super(request);
		setRequest(request);
	}

	public MultipartRequestParameter(HttpServletRequest request, Map<String, String> configParamValues,
			Map<String, String> pathParamValues) {
		super(request, configParamValues, pathParamValues);
		setRequest(request);
	}

	public MultipartRequestParameter(HttpServletRequest request, Map<String, String> pathParamValues) {
		super(request, pathParamValues);
		setRequest(request);
	}

	public MultipartRequestParameter(Map<String, String> configParamValues, HttpServletRequest request) {
		super(configParamValues, request);
		setRequest(request);
	}

	private void setRequest(HttpServletRequest request) {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setResolveLazily(true);
		HttpServletRequest mutiRequest = commonsMultipartResolver.resolveMultipart(request);
		
		setRequestParams(mutiRequest);
	}
}
