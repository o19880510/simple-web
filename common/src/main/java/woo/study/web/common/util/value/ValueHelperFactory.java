package woo.study.web.common.util.value;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

public class ValueHelperFactory {
	public static enum Type {
		PARAMETER, ATTRIBUTE, COOKIES
	}

	public static ValueHelper empty() {
		return new ValueHelperImpl(new EmptyGettable());
	}
	
	public static ValueHelper of(ValueGettable valueGettable) {
		Objects.nonNull(valueGettable);

		return new ValueHelperImpl(valueGettable);
	}

	public static ValueHelper of(Map<String, Object> map) {
		Objects.nonNull(map);
		return new ValueHelperImpl(new MapValueGettable(map));
	}

	public static ValueHelper of(HttpServletRequest request, Type type) {
		Objects.nonNull(request);
		Objects.nonNull(type);

		switch (type) {
		case PARAMETER: {
			return new ValueHelperImpl(new RequestParamValueGettable(request));
		}
		case ATTRIBUTE: {
			return new ValueHelperImpl(new RequestAttriValueGettable(request));
		}
		case COOKIES: {
			return new ValueHelperImpl(new CookiesValueGettable(request));
		}
		default:
			return empty();
		}
	}
}
