package com.lutongnet.system.datacenter.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.dao.SystemParamterDao;
import com.lutongnet.system.model.entity.SystemParamter;

public class SystemParameterDBDataLoader extends CommonDataLoader{
	
	@Resource(name="systemParamterDao")
	private SystemParamterDao systemParamterDao;
	
	// Map<String, String>
	@Transactional(propagation = Propagation.SUPPORTS)
	public Object loading() {
		
		List<SystemParamter> valueList = systemParamterDao.getAll();
		Map<String, String> sysParams = new HashMap<String, String>(valueList.size());
		
		for(SystemParamter value : valueList){
			sysParams.put(value.getKey(), value.getValue());
		}
		
		return sysParams;
	}

}
