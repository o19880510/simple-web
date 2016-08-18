package woo.study.web.common.util.value;


import javax.servlet.http.HttpServletRequest;


public class RequestAttriValueGettable implements ValueGettable{

	private HttpServletRequest request;

	public RequestAttriValueGettable(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Object get(String key) {
		return request.getAttribute(key);
	}

}
