package com.lutongnet.base.util.parameter;

/**
 * 
 * 任务参数信息类
 * 枚举类
 * @author tianjp
 *
 */
public interface Parameter {
	
	public enum Type{
		OBJECT(null),
		DATETIME("日期时间类型，例：2014-11-13 16:35:18"), 
		DATE("日期类型，例：2014-11-13"), 
		TIME("时间类型，例：16:35:18"), 
		BOOLEAN("布尔类型，例：16:35:18"), 
		INT("整数类型，例：1,2,100"), 
		LONG("大整数类型，例：1000000000"), 
		DOUBLE("小数类型，例：0.02,"),
		STRING ("字符类型, 例： \"abc\",  \"abc123\"");
		
		private String desc;
		private Type(String desc){
			this.desc = desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
	}
	
	/**
	 * 获取参数名称
	 * @return 参数名称。中文字符
	 */
	public String getChiName();
	
	
	/**
	 * 获取参数名称
	 * @return 参数名称。英文字符
	 */
	public String getEngName();
	
	/**
	 * 参数类型
	 * @return JobParameter.Type
	 */
	public Type getType();
	
	/**
	 * 参数样例值描述
	 * @return "时间类型，如：2014-11-06"
	 */
	public String getDesc();
	
	/**
	 * 获取默认值
	 * @return 
	 */
	public Object getDefaultValue();
}
