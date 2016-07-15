package woo.study.web.system.datacenter;

import java.util.Map;

import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.functions.datacenter.DataContainer;
import woo.study.web.common.functions.datacenter.DataManagementHelper;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.model.entity.Menu;

public class SystemDataManagementHelper {
	
	private static Logger log	= LoggerFactory.getLogger(SystemDataManagementHelper.class);
	private static SystemDataManagementHelper DATA_MANAGEMENT_HELPER = new SystemDataManagementHelper();
	protected SystemDataManagementHelper() {
	}

	public static SystemDataManagementHelper getInstance(){
		return DATA_MANAGEMENT_HELPER;
	}

	public Map<String, String> getCityMap(){
		Map<String, String> cityMap = getDataContainer().get(AppConstant.MemDataKeys.CITY_MAP, Map.class);
		return cityMap;
	}
	
	public Map<String, Menu> getMenuTree(){
		Map<String, Menu> map = getDataContainer().get(AppConstant.MemDataKeys.MENU_TREE, Map.class);
		return map;
	}
	
	
	public Map<String, Map<String, String>> getParameterMap(){
		Map<String, Map<String, String>> map = getDataContainer().get(AppConstant.MemDataKeys.PARAMETER_MAP, Map.class);
		return map;
	}
	
	public Set<String> getPrivilegeUris(){
		Set<String> map = getDataContainer().get(AppConstant.MemDataKeys.PRIVILEGE_URI_LIST, Set.class);
		return map;
	}
	
	public Map<String, String> getProvinceMap(){
		Map<String, String> map = getDataContainer().get(AppConstant.MemDataKeys.PROVINCE_MAP, Map.class);
		return map;
	}
	
	public Map<String, String> getSystemParamterMap(){
		Map<String, String> map = getDataContainer().get(AppConstant.MemDataKeys.SYSTEM_PARAMTER_MAP, Map.class);
		return map;
	}
	
	public Properties getConfigProperties(){
		Properties properties = getDataContainer().get(AppConstant.MemDataKeys.CONFIG_PROPES, Properties.class);
		return properties;
	}
	
	public DataContainer getDataContainer() {
		return DataManagementHelper.getDataContainer();
	}
	
}
