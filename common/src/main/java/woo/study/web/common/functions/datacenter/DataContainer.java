package woo.study.web.common.functions.datacenter;

public interface DataContainer {
	
	public <T> T get(String dataName, Class<T> clazz) ;
	
}
