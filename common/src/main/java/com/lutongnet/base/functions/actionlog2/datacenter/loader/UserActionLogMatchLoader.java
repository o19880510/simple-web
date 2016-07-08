package com.lutongnet.base.functions.actionlog2.datacenter.loader;

import java.util.ArrayList;

import java.util.List;

import com.lutongnet.base.functions.actionlog2.config.PathMatchUtil;
import com.lutongnet.base.functions.actionlog2.model.vo.LogConfigValue;
import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.base.util.AssertHelper;

public class UserActionLogMatchLoader extends CommonDataLoader<List<LogConfigValue>>{
	
	
	public List<LogConfigValue> loading() {
		
		List<LogConfigValue> configMap = getDependData(List.class);
		
		List<LogConfigValue> configSet = new ArrayList<LogConfigValue>();
		
		for(LogConfigValue logConfigValue : configMap){
			String uri = logConfigValue.getUrl();
			if(!PathMatchUtil.isFullPath(uri)){
				
				String matchUrl = null;
				
				String method = logConfigValue.getMethod();
				if (AssertHelper.notEmpty(method)) {
					matchUrl = method.toUpperCase() + uri;
				}else{
					matchUrl = "*" + uri;
				}
				
				logConfigValue.setMatchUrl(matchUrl);
				configSet.add(logConfigValue);
			}
		}
		
		return configSet;
	}

}
