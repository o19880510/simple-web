package woo.study.web.common.util;


import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * json 帮助类 对象和JSON互相转换
 * 
 * @author tianjp
 *
 */
public class JSON2Helper {
	private static final ObjectMapper OBJECT_MAPPER	= new ObjectMapper();
	
	static{
		OBJECT_MAPPER.registerModule(new JodaModule());
		OBJECT_MAPPER.setTimeZone(DateTimeZone.getDefault().toTimeZone());
	}
	
	/**
	 * 将Object对象转为JSON字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		String json = null;
		try {
			json = OBJECT_MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException("To json error, object is "+object+";exception:"+e);
		}
		return json;
	}

	/**
	 * 将一个JSON字符串转换为Object对象
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		T o = null;
		if (json != null) {
			try {
				o = OBJECT_MAPPER.readValue(json, clazz);
			} catch (Exception e) {
				throw new RuntimeException("Json string To object error, json is "+json+";exception:"+e);
			}
		}
		return o;
	}
	
	/**
	 * 将一个JSON字符串转换为T对象
	 * @param json
	 * @param typeReference
	 * @return
	 */
	public static <T> T toObject(String json, TypeReference<T> typeReference){
		try {
			return OBJECT_MAPPER.readValue(json, typeReference);
		} catch (Exception e) {
			throw new RuntimeException("failed to convert string " + json + " to object", e);
		}
	}

}
