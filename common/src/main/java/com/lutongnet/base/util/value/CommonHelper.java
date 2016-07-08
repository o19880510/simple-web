package com.lutongnet.base.util.value;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.DateUtils;

public abstract class CommonHelper implements ValueHelper{
	
	private static Logger log = LoggerFactory.getLogger(CommonHelper.class);
	
	@Override
	public int getInt(String key) {
		Object obj = get(key);
		if (obj == null || obj instanceof Integer) {
			return (Integer) obj;
		}
		return Integer.valueOf(obj.toString());
	}

	@Override
	public float getFloat(String key) {
		Object obj = get(key);
		if (obj == null || obj instanceof Float) {
			return (Float) obj;
		}
		return Float.valueOf(obj.toString());
	}

	@Override
	public long getLong(String key) {
		Object obj = get(key);
		if (obj == null || obj instanceof Long) {
			return (Long) obj;
		}
		return Long.valueOf(obj.toString());
	}

	@Override
	public double getDouble(String key) {
		Object obj = get(key);
		if (obj == null || obj instanceof Double) {
			return (Double) obj;
		}
		return Double.valueOf(obj.toString());
	}

	@Override
	public String getString(String key) {
		Object obj = get(key);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	@Override
	public DateTime getDateTime(String key, String format){
		Object obj = get(key);
		if (obj == null || AssertHelper.isEmpty(obj.toString()) ) {
			return null;
			
		}else if(obj instanceof DateTime){
			return (DateTime) obj;
			
		}else{
			DateTime dateTime = null;
			try{
				dateTime = DateTime.parse(obj.toString(), DateTimeFormat.forPattern(format));
			}catch(Exception e){
				log.warn("CommonHelper getDateTime parse error!", e);
			}
			return dateTime;
		}
	}
	
	@Override
	public DateTime getDateTime(String key) {
		return getDateTime(key, "yyyy-MM-dd HH:mm:ss");
	}
	
	@Override
	public LocalDate getLocalDate(String key){
		DateTime dateTime = getDateTime(key, "yyyy-MM-dd");
		return dateTime == null ? null:dateTime.toLocalDate();
	}
	
	@Override
	public LocalTime getLocalTime(String key){
		DateTime dateTime = getDateTime(key, "HH:mm:ss");
		return dateTime == null ? null:dateTime.toLocalTime();
	}

}