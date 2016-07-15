package woo.study.web.common.util.value;

import java.util.Map;

public class MapValueHelper extends CommonHelper {

	Map<String, Object>	dataMap;

	public MapValueHelper(Map<String, Object> map) {
		this.dataMap = map;
	}

	@Override
	public Object get(String key) {
		return dataMap.get(key);
	}

}
