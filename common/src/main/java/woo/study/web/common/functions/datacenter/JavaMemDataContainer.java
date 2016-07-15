package woo.study.web.common.functions.datacenter;


import java.util.HashMap;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.ObjectUtils;


public class JavaMemDataContainer implements DataContainer, DataContainerNotify, DataCloneable {
	
	private static Logger log = LoggerFactory.getLogger(JavaMemDataContainer.class);
	
	private HashMap<String, Object> datasMap;
	
	public JavaMemDataContainer(){
		datasMap = new HashMap<String, Object>();
	}
	
	@Override
	public void notify(Map<String, Object> dataMap) {
		this.datasMap.putAll(dataMap);
		
	}

	@Override
	public <T> T get(String dataName, Class<T> clazz) {
		return (T) this.datasMap.get(dataName);
	}
	
	
	public Map<String, Object> dataClone(){
		
		return (Map<String, Object>) ObjectUtils.clone(datasMap);
	}

}
