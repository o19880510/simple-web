package com.lutongnet.base.functions.actionlog.datacenter.loader;


import java.util.HashMap;
import java.util.Map;

import com.lutongnet.base.functions.actionlog.model.vo.LogConfigValue;
import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;

public class ActionCodeDataLoader extends CommonDataLoader<Map<String, String>>{
	
	
	public Map<String, String> loading() {
		Map<String, LogConfigValue> codeTableMap = getDependData(Map.class);
		
		Map<String, String> actionCodeMap = new HashMap<String, String>();
		for(LogConfigValue logConfigValue : codeTableMap.values()){
			actionCodeMap.put(logConfigValue.getActionCode(), logConfigValue.getDesc());
		}
		
		return actionCodeMap;
	}

}
