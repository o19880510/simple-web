package com.lutongnet.system.datacenter.loader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.system.model.entity.Menu;
import com.lutongnet.system.service.MenuService;

public class MenuTreeDBDataLoader extends CommonDataLoader {

	@Resource(name = "menuService")
	private MenuService				menuService;
	
	// Map<String, Menu>
	@Transactional(propagation = Propagation.SUPPORTS)
	public Object loading() {
		List<Menu> allTree = menuService.getAllTree();
		Map<String, Menu> map = new HashMap<String, Menu>();
		for (Menu tree : allTree) {
			tree = menuService.getTree(tree.getId());
			map.put(tree.getName(), tree);
		}
		
		return map;
	}

}
