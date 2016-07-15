package woo.study.web.system.datacenter.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import woo.study.web.common.functions.datacenter.loader.CommonDataLoader;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.dao.SystemParamterDao;
import woo.study.web.system.model.entity.SystemParamter;

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
