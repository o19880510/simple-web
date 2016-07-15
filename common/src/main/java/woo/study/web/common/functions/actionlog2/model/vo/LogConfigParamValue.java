package woo.study.web.common.functions.actionlog2.model.vo;

import java.io.Serializable;


public class LogConfigParamValue implements Serializable{
	private String name;
	private String column;
	private Integer maxLength;
	private String value;
	private String type;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "LogConfigParamValue [name=" + name + ", column=" + column + ", maxLength=" + maxLength + ", value=" + value + ", type=" + type + "]";
	}
}
