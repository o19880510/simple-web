package woo.study.web.common.util.value;


import javax.servlet.http.HttpServletRequest;


public class HttpRequestAttriValueHelper extends CommonHelper{

	private HttpServletRequest request;

	public HttpRequestAttriValueHelper(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Object get(String key) {
		return request.getAttribute(key);
	}

}
