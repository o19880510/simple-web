package com.lutongnet.base.functions.datacenter.tag;


import com.lutongnet.base.functions.datacenter.DataManagementHelper;

public class MemDataFunc {
	
	public static Object get(String dataName){
		return DataManagementHelper.getDataContainer().get(dataName, Object.class);
	}
	
}
