package woo.study.web.common.functions.actionlog.datacenter.loader;


import java.util.HashMap;
import java.util.Map;

import woo.study.web.common.functions.actionlog.model.vo.LogConfigValue;
import woo.study.web.common.functions.datacenter.loader.CommonDataLoader;

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
