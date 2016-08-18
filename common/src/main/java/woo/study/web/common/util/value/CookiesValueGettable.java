package woo.study.web.common.util.value;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookiesValueGettable implements ValueGettable{

	private Map<String, String> paramMap;
	

	public CookiesValueGettable(HttpServletRequest request) {
		
		paramMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			paramMap.put(cookie.getName(), cookie.getValue());
		}
	}

	@Override
	public Object get(String key) {
		return paramMap.get(key);
	}

}
