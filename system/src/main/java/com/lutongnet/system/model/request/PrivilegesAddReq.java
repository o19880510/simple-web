package com.lutongnet.system.model.request;

import java.util.ArrayList;
import java.util.List;

import com.lutongnet.system.model.entity.Privilege;

public class PrivilegesAddReq {

	private String			group;
	private String			oldGroup;
	private List<Privilege>	privileges;

	public PrivilegesAddReq() {
		privileges = new ArrayList<Privilege>();
		privileges.add(new Privilege());
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	public String getOldGroup() {
		return oldGroup;
	}

	public void setOldGroup(String oldGroup) {
		this.oldGroup = oldGroup;
	}

	@Override
	public String toString() {
		return "PrivilegesAddReq [group=" + group + ", privileges=" + privileges + "]";
	}

}
