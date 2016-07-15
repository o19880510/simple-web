package woo.study.web.common.util;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
/**
 * 
 * 时间帮助类
 * 
 * 功能
 * 1、提供常用的格式化
 * 2、支持java.sql.Date、java.sql.Timestamp、org.joda.time.DateTime
 * 
 * @author tianjp
 *
 */
public class DateUtils {

	public static final String							DATETIME			= "datetime";
	public static final String							DATE				= "date";
	public static final String							TIME				= "time";

	public static final String							FORMAT_DAFAULT		= "yyyy-MM-dd HH:mm:ss";
	public static final String							FORMAT_DATE			= "yyyy-MM-dd";
	public static final String							FORMAT_TIME			= "HH:mm:ss";
	public static final String							FORMAT_yyyyMMdd		= "yyyyMMdd";

	public static final DateTimeFormatter				FORMATER_DAFAULT	= DateTimeFormat.forPattern(FORMAT_DAFAULT);
	public static final DateTimeFormatter				FORMATER_DATE		= DateTimeFormat.forPattern(FORMAT_DATE);
	public static final DateTimeFormatter				FORMATER_TIME		= DateTimeFormat.forPattern(FORMAT_TIME);
	
	public static final Map<String, DateTimeFormatter> FORMATTER_MAP = new HashMap<String, DateTimeFormatter>();
	
	static{
		FORMATTER_MAP.put(null, FORMATER_DAFAULT);
		FORMATTER_MAP.put("", FORMATER_DAFAULT);
		FORMATTER_MAP.put(DATETIME, FORMATER_DAFAULT);
		FORMATTER_MAP.put(DATE, FORMATER_DATE);
		FORMATTER_MAP.put(TIME, FORMATER_TIME);
	}
	
	/**
	 * 格式化时间 , yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return String 
	 */
	public static String format(Date date){
		return format(FORMAT_DAFAULT, date);
	}
	
	/**
	 * 格式化时间 , yyyy-MM-dd HH:mm:ss
	 * @param timestamp
	 * @return String 
	 */
	public static String format(Timestamp timestamp){
		return format(FORMAT_DAFAULT, timestamp);
	}
	
	/**
	 * 格式化时间
	 * @param formater 用户期望的时间格式
	 * @param date
	 * @return
	 */
	public static String format(String formater, Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(date);
	}
	
	/**
	 * 格式化时间
	 * @param formater 用户期望的时间格式
	 * @param timestamp
	 * @return
	 */
	public static String format(String formater, Timestamp timestamp){
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(timestamp);
	}
	
	/**
	 * 获取今天的起始时间
	 * eg：2013-05-18 00:00:00.000
	 * @return Timestamp
	 */
	public static Timestamp getTodayBeginTimestamp(){
		
		Calendar calendar  = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return new Timestamp(calendar.getTimeInMillis());
	}
	/**
	 * 获取今天的结束时间
	 * eg：2013-05-18 23:59:59.999
	 * @return Timestamp
	 */
	public static Timestamp getTodayEndTimestamp(){
		
		Calendar calendar  = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 * 获取当前时间
	 * @param formater  期望转换的格式
	 * @return String
	 */
	public static String getCurrentDateTime(String formater){
		
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前时间戳
	 * @return Timestamp
	 */
	public static Timestamp getCurrentTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}
	
}
