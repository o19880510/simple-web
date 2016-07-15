package woo.study.web.common.functions.actionlog.model.entity;

import java.io.Serializable;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "t_seq")
public class Seq implements Serializable, Cloneable {

	private static final long	serialVersionUID	= -603867518103501502L;

	@Id
	@Column(name = "TABLE_NAME")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String				tableName;

	@Column(name = "PREFIX")
	private String				prefix;

	@Column(name = "MAX_VALUE")
	private Integer				maxValue;
	
	@Column(name = "INIT_VALUE")
	private Integer				initValue;
	
	@Column(name = "NEXT_VALUE")
	private Integer				nextValue;
	
	@Column(name = "INCREASE")
	private Integer				increase;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getInitValue() {
		return initValue;
	}

	public void setInitValue(Integer initValue) {
		this.initValue = initValue;
	}

	public Integer getNextValue() {
		return nextValue;
	}

	public void setNextValue(Integer nextValue) {
		this.nextValue = nextValue;
	}

	public Integer getIncrease() {
		return increase;
	}

	public void setIncrease(Integer increase) {
		this.increase = increase;
	}
	
	@Override
	public Seq clone() {
		try {
			return (Seq) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "Seq [tableName=" + tableName + ", prefix=" + prefix + ", maxValue=" + maxValue + ", initValue="
				+ initValue + ", nextValue=" + nextValue + ", increase=" + increase + "]";
	}
	

}
