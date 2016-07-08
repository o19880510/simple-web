package com.lutongnet.system.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table ( name = "t_param_item" )
public class ParamItem implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7371185049703582391L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer						id;

	@ManyToOne
	@JoinColumn ( name = "param_id" )
	private Param							param;

	@NotBlank
	private String						name;

	@NotBlank
	private String						value;

	public Integer getId ( ) {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getName ( ) {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public String getValue ( ) {
		return value;
	}

	public void setValue ( String value ) {
		this.value = value;
	}

	public Param getParam ( ) {
		return param;
	}

	public void setParam ( Param param ) {
		this.param = param;
	}

}
