package com.lutongnet.system.model.request;


public class RoleReq extends CommonReq {

	private String	name;

	public RoleReq ( ) {
		super ( 5 );
	}

	public String getName ( ) {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}
}
