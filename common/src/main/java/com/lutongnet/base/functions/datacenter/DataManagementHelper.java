package com.lutongnet.base.functions.datacenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataManagementHelper {
	
	private static Logger log	= LoggerFactory.getLogger(DataManagementHelper.class);
	private static DataContainer dataContainer;
	private DataManagementHelper() {
	}
	
	static void init(DataContainer inDataContainer){
		if(dataContainer != null){
			throw new RuntimeException("DataManagementHelper init(DataContainer) method can invoke once only!");
		}
		
		dataContainer = inDataContainer;
	}
	

	public static DataContainer getDataContainer() {
		return dataContainer;
	}
	
}
