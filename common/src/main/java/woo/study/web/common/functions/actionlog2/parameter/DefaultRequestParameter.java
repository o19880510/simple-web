package woo.study.web.common.functions.actionlog2.parameter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRequestParameter extends CommonRequestParameter {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultRequestParameter.class);
	
	public DefaultRequestParameter(HttpServletRequest request) {
		super(request);
	}

	public DefaultRequestParameter(HttpServletRequest request, Map<String, String> configParamValues,
			Map<String, String> pathParamValues) {
		super(request, configParamValues, pathParamValues);
	}

	public DefaultRequestParameter(HttpServletRequest request, Map<String, String> pathParamValues) {
		super(request, pathParamValues);
	}

	public DefaultRequestParameter(Map<String, String> configParamValues, HttpServletRequest request) {
		super(configParamValues, request);
	}
}
