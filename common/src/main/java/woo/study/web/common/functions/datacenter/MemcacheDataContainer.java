package woo.study.web.common.functions.datacenter;



import java.util.Map;




import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MemcacheDataContainer implements DataContainer, DataContainerNotify {
	
	private static Logger log = LoggerFactory.getLogger(MemcacheDataContainer.class);
	
	private MemcachedClient client;
	
	public MemcacheDataContainer(MemcachedClient client) {
		this.client = client;
	}

	@Override
	public <T> T get(String dataName, Class<T> clazz) {
		try {
			log.debug("MemcacheDataContainer get dataName=" + dataName);
			return (T) client.get(dataName);
		} catch (Exception e) {
			log.error("MemcachedClient get error:", e);
			throw new RuntimeException("MemcachedClient get error:", e);
		}
	}
	
	
	@Override
	public void notify(Map<String, Object> dataMap) {
		setToMemcache(dataMap);
	}
	
	private void setToMemcache(Map<String, Object> datasMap){
		
		for(Map.Entry<String, Object> entry : datasMap.entrySet()){
			try {
				client.set(entry.getKey(), 0, entry.getValue());
			} catch (Exception e) {
				log.error("add map error.", e);
				throw new RuntimeException("add map error.", e);
			}
			
		}
	}
	
}
