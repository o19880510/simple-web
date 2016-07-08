package com.lutongnet.system.datacenter.loader;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;

public class ProvinceDataLoader extends CommonDataLoader<Map<String, String>> {

	// 
	public Map<String, String> loading() {

		ObjectMapper mapper = new ObjectMapper();
		InputStream in = getFileStream();
		Map<String, List<Map<String, String>>> provinceMap = new HashMap<String, List<Map<String, String>>>();
		Map<String, String> provinceMap2 = new HashMap<String, String>();
		try {
			provinceMap = mapper.readValue(in, 
					new TypeReference<Map<String, List<Map<String, String>>>>() {
				
					});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		List<Map<String, String>> provinces = provinceMap.get("province");

		for (Map<String, String> province : provinces) {
			provinceMap2.put(province.get("code"), province.get("name"));
		}
		
		return provinceMap2;
	}

}
