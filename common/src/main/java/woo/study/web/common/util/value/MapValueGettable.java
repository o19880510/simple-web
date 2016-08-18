package woo.study.web.common.util.value;

import java.util.Map;

public class MapValueGettable implements ValueGettable {

	private Map<String, Object> map;

	public MapValueGettable(Map<String, Object> map) {
		super();
		this.map = map;
	}

	@Override
	public Object get(String key) {
		return map.get(key);
	}

}
