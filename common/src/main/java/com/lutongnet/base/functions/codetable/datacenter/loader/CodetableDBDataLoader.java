package com.lutongnet.base.functions.codetable.datacenter.loader;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.functions.codetable.dao.CodetableDAO;
import com.lutongnet.base.functions.codetable.model.vo.CodeTableConfigValue;
import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;

public class CodetableDBDataLoader extends CommonDataLoader<Map<String, Map<String, String>>>{
	
	@Resource(name = "codetableDao")
	private CodetableDAO			codetableDao;
	
	// Map<String, Map<String, String>>
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String, Map<String, String>> loading() {
		
		Map<String, CodeTableConfigValue> configValues = getDependData(Map.class);
		
		Map<String, Map<String, String>> codetableValueMap = new HashMap<String, Map<String,String>>();
		
		Set<String> keySet = configValues.keySet();
		for(String key: keySet){
			CodeTableConfigValue configValue = configValues.get(key);
			Map<String, String> valuesMap = codetableDao.get(configValue);
			codetableValueMap.put(key, valuesMap);
		}
		
		return codetableValueMap;
	}

}
