package com.lutongnet.base.functions.datacenter.loader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.loader.EnumDisplay;


public class EmunLoader extends CommonDataLoader<Map<String, Map<String, String>>> {

	private static Logger	log	= LoggerFactory.getLogger(EmunLoader.class);

	private List<String>	list;

	public void setEmunClass(List<String> enumClass) {
		list = enumClass;
	}

	@Override
	public Map<String, Map<String, String>> loading() {
		log.debug("EmunLoader list=" + list);
		
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		for (String enumClass : list) {
			String key = getKey(enumClass);
			map.put(key, anyClass(enumClass));
		}
		
		log.debug("EmunLoader map=" + map);
		return map;
	}
	
	private String getKey(String enumClass){
		int lastIndex = enumClass.lastIndexOf(".");
		if(lastIndex > 0){
			return enumClass.substring(lastIndex + 1);
		}else{
			return enumClass;
		}
	}
	
	private Map<String, String> anyClass(String enumClass) {
		Map<String, String> map = new LinkedHashMap<String, String>();

		Class<Enum> em = null;
		try {
			em = (Class<Enum>) Class.forName(enumClass);
		} catch (ClassNotFoundException e1) {
			log.warn("class not found, " + enumClass, e1);
			return map;
		}

		for (Enum e : em.getEnumConstants()) {
			String name = e.name();
			String desc = name;
			
			if (e instanceof EnumDisplay) {
				EnumDisplay display = (EnumDisplay) e;
				desc = display.getDesc();
			}
			
			map.put(name, desc);
		}

		return map;
	}
}
