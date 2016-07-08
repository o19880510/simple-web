package com.lutongnet.base.functions.actionlog2.datacenter.loader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lutongnet.base.functions.actionlog2.model.vo.LogConfigValue;
import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;

public class ActionCodeDataLoader extends CommonDataLoader<Map<String, String>>{
	
	
	public Map<String, String> loading() {
		List<LogConfigValue> codeTableMap = getDependData(List.class);
		
		Map<String, String> actionCodeMap = new HashMap<String, String>();
		for(LogConfigValue logConfigValue : codeTableMap){
			actionCodeMap.put(logConfigValue.getActionCode(), logConfigValue.getDesc());
		}
		
		return actionCodeMap;
	}

}
