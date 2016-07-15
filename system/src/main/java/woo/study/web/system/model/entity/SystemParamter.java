package woo.study.web.system.model.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;



@Entity
@Table(name = "t_system_paramter")
public class SystemParamter implements Serializable {

	@Id
	@Column(name = "key")
	private String				key;

	@Column(name = "value")
	private String				value;

	@Column(name = "description")
	private String				description;
	
	@Column(name = "creation_date")
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	private DateTime			creationDate;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "SystemParamter [key=" + key + ", value=" + value + ", description=" + description + ", creationDate="
				+ creationDate + "]";
	}

}
