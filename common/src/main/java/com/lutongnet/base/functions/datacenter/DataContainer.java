package com.lutongnet.base.functions.datacenter;

public interface DataContainer {
	
	public <T> T get(String dataName, Class<T> clazz) ;
	
}
