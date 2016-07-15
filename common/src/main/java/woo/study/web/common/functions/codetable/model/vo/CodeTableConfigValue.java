package woo.study.web.common.functions.codetable.model.vo;

import java.io.Serializable;

public class CodeTableConfigValue implements Serializable{
	
	private String name;
	private String tableName;
	private String value;
	private String desc;
	private String order;
	
	private String orderBy;
	private String sql;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@Override
	public String toString() {
		return "CodeTableConfigValue [name=" + name + ", tableName="
				+ tableName + ", value=" + value + ", desc=" + desc
				+ ", order=" + order + ", orderBy=" + orderBy + "]";
	}
}
