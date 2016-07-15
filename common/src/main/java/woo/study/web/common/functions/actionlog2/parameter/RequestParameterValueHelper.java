package woo.study.web.common.functions.actionlog2.parameter;

import woo.study.web.common.util.value.CommonHelper;

public class RequestParameterValueHelper extends CommonHelper{
	private RequestParameter requestParameter;

	public RequestParameterValueHelper(RequestParameter requestParameter) {
		super();
		this.requestParameter = requestParameter;
	}

	@Override
	public Object get(String key) {
		return this.requestParameter.getParameter(key);
	}
}
