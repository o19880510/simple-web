package com.lutongnet.system.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.tools.BaseTests;
public class PrivilegeTests extends BaseTests{

	@Test
	public void findByIds(){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(new Integer(12));
		ids.add(new Integer(13));
		List<Integer> ids2 = new ArrayList<Integer>();
		ids2.add(new Integer(14));
		ids2.add(new Integer(12));
		ids2.removeAll(ids);
		System.out.println(ids2);
		//System.out.println(privilegeService.findByIds(ids));
	}
}
