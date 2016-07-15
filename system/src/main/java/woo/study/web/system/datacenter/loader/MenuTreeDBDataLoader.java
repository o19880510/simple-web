package woo.study.web.system.datacenter.loader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import woo.study.web.common.functions.datacenter.loader.CommonDataLoader;
import woo.study.web.system.model.entity.Menu;
import woo.study.web.system.service.MenuService;

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
