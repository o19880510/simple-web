package woo.study.web.system.datacenter.loader;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import woo.study.web.common.functions.datacenter.loader.CommonDataLoader;

public class CityDataLoader extends CommonDataLoader {

	// Map<String, String>
	public Object loading() {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream in = getFileStream();
		Map<String, List<Map<String, String>>> cityMap = new HashMap<String, List<Map<String, String>>>();
		Map<String, String> cityMap2 = new HashMap<String, String>();
		try {
			cityMap = mapper.readValue(in, 
					new TypeReference<Map<String, List<Map<String, String>>>>() {
					});
			List<Map<String, String>> cities = cityMap.get("city");

			for (Map<String, String> city : cities) {
				cityMap2.put(city.get("code"), city.get("name"));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
		
		return cityMap2;
	}

}
