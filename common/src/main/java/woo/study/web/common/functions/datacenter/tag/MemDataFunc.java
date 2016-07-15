package woo.study.web.common.functions.datacenter.tag;


import woo.study.web.common.functions.datacenter.DataManagementHelper;

public class MemDataFunc {
	
	public static Object get(String dataName){
		return DataManagementHelper.getDataContainer().get(dataName, Object.class);
	}
	
}
