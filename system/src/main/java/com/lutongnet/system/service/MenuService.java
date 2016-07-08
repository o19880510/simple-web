package com.lutongnet.system.service;

import java.util.HashMap;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.system.dao.MenuDao;
import com.lutongnet.system.model.entity.Menu;
import com.lutongnet.system.model.request.MenuReq;


@Transactional
@Service ( "menuService" )
public class MenuService {
	
	private static Logger log	= LoggerFactory.getLogger(MenuService.class);
	
	@Resource(name = "menuDao")
	private MenuDao menuDao;
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void list(MenuReq menuReq, Model model) {
		PaginationBean<Menu> pb = menuDao.list(menuReq);
		model.addAttribute("pb", pb);
	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addTree( Menu menu , Locale locale) {

		if (menuDao.existsByName(menu.getName(), menu.getId())) {
			return true;
		}
		
		menuDao.add(menu);
		return false;

	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addMenu(Menu menu, Locale locale) {
		if (menuDao.existsInParent(menu)) {
			return true;
		}
		
		menuDao.add(menu);
		return false;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Integer id) {
		menuDao.remove(id);
	}
	
	
	/**
	 * 移动菜单
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void move(Integer id, Integer pid, Integer prevId, Integer nextId) {
		
		if(log.isDebugEnabled()){
			log.debug(id + "," + pid + "," + prevId + "," + nextId);
		}
		
		Menu menu = menuDao.get(id);
		Menu parent = menuDao.get(pid);
		if (prevId != null && nextId != null) {
			// 放到中间
			Menu prev = menuDao.get(prevId);
			int prevPosition = 0;
			for (Menu child : parent.getChildren()) {
				child.setPosition(2 * child.getPosition());
				if (child.getId() == prev.getId()) {
					prevPosition = child.getPosition();
				}
			}
			menu.setPosition(prevPosition + 1);
			menu.setParent(parent);
			parent.getChildren().add(menu);
		} else if (prevId == null && nextId == null) {
			// 放到节点下
			int i = 0;
			for (Menu child : parent.getChildren()) {
				if (child.getId() != id) {
					child.setPosition(i++);
				}
			}
			menu.setPosition(i);
			menu.setParent(parent);
			parent.getChildren().add(menu);
		} else if (prevId == null) {
			// 放到最前面
			menu.setParent(parent);
			parent.getChildren().add(menu);
			menu.setPosition(0);
			int i = 1;
			for (Menu child : parent.getChildren()) {
				if (child.getId() != id) {
					child.setPosition(i++);
				}
			}
		} else if (nextId == null) {
			// 放到最后面
			int i = 0;
			for (Menu child : parent.getChildren()) {
				if (child.getId() != id) {
					child.setPosition(i++);
				}
			}
			menu.setPosition(i);
			menu.setParent(parent);
			parent.getChildren().add(menu);
		}
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String, Object> get(Integer id) {

		Menu menu = menuDao.get(id);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", menu.getId());
		resMap.put("pid", menu.getParent().getId());
		resMap.put("name", menu.getName());
		resMap.put("uri", menu.getUri());
		resMap.put("style", menu.getStyle());

		return resMap;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(Menu menu) {
		
		if (menuDao.existsInParentWhenUpdate(menu)) {
			return true;
		}

		menuDao.update(menu);
		return false;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateDescription(Integer id, String description) {
		menuDao.updateDescription(id, description);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Menu getTree(Integer id) {
		Menu root = menuDao.getNode(id);
		for (Menu node : root.getChildren()) {
			getTree(node.getId());
		}
		return root;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeTree(Integer id, HttpServletRequest request){
		Menu tree = getTree(id);
		menuDao.remove(tree.getId());
		request.getSession().getServletContext().removeAttribute(tree.getName());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Menu> getAllTree(){
		return menuDao.getAllTree();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Menu getTreeByName(String name){
		Menu root = menuDao.getTreeByName(name);
		
		return getTree(root.getId());
	}
	
}
