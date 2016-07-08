package com.lutongnet.base.util.parameter;


public class ParameterValue implements Parameter {

	protected String	chiName;
	protected String	engName;
	protected Type		type;
	protected String	desc;
	protected Object	defaultValue;

	public ParameterValue(String chiName, String engName, Type type) {
		this.chiName = chiName;
		this.engName = engName;
		this.type = type;
		this.desc = this.type.getDesc();
	}
	
	public ParameterValue(String chiName, String engName, Type type, Object defaultValue) {
		this(chiName, engName, type);
		this.defaultValue = defaultValue;
	}


	@Override
	public String getEngName() {
		return engName;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String getChiName() {
		return chiName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "JobParameterValue [chiName=" + chiName + ", engName=" + engName + ", type=" + type + ", desc=" + desc
				+ "]";
	}
	

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public Object getDefaultValue() {
		return this.defaultValue ;
	}
}
