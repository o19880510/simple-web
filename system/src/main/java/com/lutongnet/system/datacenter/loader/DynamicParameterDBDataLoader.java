package com.lutongnet.system.datacenter.loader;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.system.dao.ParamDao;

public class DynamicParameterDBDataLoader extends CommonDataLoader{
	
	private static Logger log = LoggerFactory.getLogger(ConfigPropesDataLoader.class);
	
	@Resource(name = "paramDao")
	private ParamDao				paramDao;
	
	// Map<String, Map<String, String>>
	@Transactional(propagation = Propagation.SUPPORTS)
	public Object loading() {
		
		Map<String, Map<String, String>> paramMap = paramDao.getAllParams();
		log.error("DynamicParameterDBDataLoader paramMap=" + paramMap);
		
		return paramMap;
	}

}
