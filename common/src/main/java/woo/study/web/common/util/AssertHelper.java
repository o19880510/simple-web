package woo.study.web.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * 空值判断帮助类
 * 
 * 目前可以判断String、Collection、Map三种类型
 * 
 * @author tianjp
 *
 */
public class AssertHelper {
	
	public static final boolean isEmpty(String str){
		return str == null ? true : str.trim().isEmpty();
	}
	
	public static final boolean notEmpty(String str){
		return !isEmpty(str);
	}
	
	public static final boolean isEmpty(Collection collection) {
		return collection == null ? true : (collection.size() == 0 ? true : false);
	}
	public static final boolean notEmpty(Collection collection) {
		return !isEmpty(collection);
	}
	
	public static final boolean isEmpty(Map map) {
		return map == null ? true : (map.size() == 0 ? true : false);
	}
	public static final boolean notEmpty(Map map) {
		return !isEmpty(map);
	}
	
	public static final boolean isAllEmpty(String... strs){
		for(String str : strs){
			if(notEmpty(str)){
				return false;
			};
		}
		return true;
	}
}
