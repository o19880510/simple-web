package com.lutongnet.system.model.request;


public class PrivilegeReq extends CommonReq {

	private String	group;
	private String	name;

	public PrivilegeReq ( ) {
		super ( 10 );
	}

	public String getGroup ( ) {
		return group;
	}

	public void setGroup ( String group ) {
		this.group = group;
	}

	public String getName ( ) {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

}
