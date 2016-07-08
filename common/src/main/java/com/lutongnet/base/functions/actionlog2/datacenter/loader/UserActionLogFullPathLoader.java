package com.lutongnet.base.functions.actionlog2.datacenter.loader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lutongnet.base.functions.actionlog2.config.PathMatchUtil;
import com.lutongnet.base.functions.actionlog2.model.vo.LogConfigValue;
import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.base.util.AssertHelper;

public class UserActionLogFullPathLoader extends CommonDataLoader<Map<String, LogConfigValue>>{
	
	
	public Map<String, LogConfigValue> loading() {
		
		List<LogConfigValue> configMap = getDependData(List.class);
		
		Map<String, LogConfigValue> actionMap = new HashMap<String, LogConfigValue>();
		for(LogConfigValue logConfigValue : configMap){
			String uri = logConfigValue.getUrl();
			if(PathMatchUtil.isFullPath(uri)){
				
				String key = uri;
				String method = logConfigValue.getMethod();
				if (AssertHelper.notEmpty(method)) {
					key = method.toUpperCase() + uri;
				}else{
					key = "*" + uri;
				}
				 
				actionMap.put(key, logConfigValue);
			}
		}
		
		return actionMap;
	}

}
