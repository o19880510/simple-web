package woo.study.web.common.thread;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalMaps {
	
	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();
	public static void set(String key, Object value){
		Map<String, Object> map = threadLocal.get();
		map.put(key, value);
	}
	
	public static Object get(String key){
		Map<String, Object> map = threadLocal.get();
		return map.get(key);
	}
	public static Object remove(String key){
		Map<String, Object> map = threadLocal.get();
		return map.remove(key);
	}
	
	public static void clean(){
		threadLocal.remove();
	}
	
	public static boolean isInit(){
		return threadLocal.get() == null ? false : true;
	}
	
	public static void init(){
		threadLocal.set(new HashMap<String, Object>());
	}
}

