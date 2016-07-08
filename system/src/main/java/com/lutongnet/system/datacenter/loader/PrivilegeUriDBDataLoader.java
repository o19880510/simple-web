package com.lutongnet.system.datacenter.loader;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.system.service.PrivilegeService;

public class PrivilegeUriDBDataLoader extends CommonDataLoader {
	
	@Resource(name = "privilegeService")
	private PrivilegeService		privilegeService;
	
	// Set<String>
	@Transactional(propagation = Propagation.SUPPORTS)
	public Object loading() {
		
		List<String> privilegeUriList = privilegeService.getPrivilegeUriList();
		
		Set<String> privilegeUriSet = new HashSet<String>();
		privilegeUriSet.addAll(privilegeUriList);
		
		return privilegeUriSet;
	}

}
