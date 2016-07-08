package com.lutongnet.system.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table ( name = "t_param" )
public class Param implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7229534992425517423L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer						id;

	@NotBlank ( message = "{param.name.required}" )
	@Pattern ( regexp = "^[a-zA-Z]+[a-zA-Z0-9_]*" , message = "{param.name.invalid}" )
	private String						name;

	@OneToMany ( cascade = CascadeType.ALL , mappedBy = "param" , orphanRemoval = true )
	@Size ( min = 1 , message = "{param.items.min}" )
	@BatchSize ( size = 5 )
	private List<ParamItem>		items							= new ArrayList<ParamItem> ( );

	private String						description;

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

	public String getDescription ( ) {
		return description;
	}

	public void setDescription ( String description ) {
		this.description = description;
	}

	public List<ParamItem> getItems ( ) {
		return items;
	}

	public void setItems ( List<ParamItem> items ) {
		this.items = items;
	}

	public void addItem ( ParamItem item ) {
		item.setParam ( this );
		items.add ( item );
	}
}
