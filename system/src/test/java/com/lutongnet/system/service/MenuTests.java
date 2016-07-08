package com.lutongnet.system.service;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import test.tools.BaseTests;

import com.lutongnet.base.util.JSON2Helper;
import com.lutongnet.system.model.entity.Menu;
import com.lutongnet.system.model.entity.Privilege;
import com.lutongnet.system.model.entity.Role;
import com.lutongnet.system.model.entity.User;
import com.lutongnet.system.service.MenuService;
import com.lutongnet.system.service.UserService;


public class MenuTests extends BaseTests {

	@Autowired
	private MenuService	menuService;

	@Autowired
	private UserService	userService;

	private String			json	= "";

	private List<String> getAllowUriList ( User user ) {
		List<String> allowUriList = new ArrayList<String> ( );
		for ( Role role : user.getRoles ( ) ){
			for ( Privilege privilege : role.getPrivileges ( ) ){
				allowUriList.add ( privilege.getUri ( ) );
			}
		}
		return allowUriList;
	}

	public void get ( ) throws Exception {
		Menu tree = menuService.getTreeByName ( "SYSTEM_MENU_TREE" );
		String json =  JSON2Helper.toJson(tree);
		
		System.out.println ( json );
		
		Menu tree2 = JSON2Helper.toObject(json, Menu.class);
		for ( Menu node : tree2.getChildren ( ) ){
			System.out.println ( node.getName ( ) );
			for ( Menu node2 : node.getChildren ( ) ){
				System.out.println ( node2.getName ( ) + "," + node2.getParent ( ).getName ( ) );
			}
		}

		User user = userService.get ( "zhangfj" , "88954662" );
		List<String> allowUriList = getAllowUriList ( user );
		System.out.println ( allowUriList );
		getTree ( tree , allowUriList );

	}

	public void getTree ( Menu tree , List<String> allowUriList ) {
		String uri = tree.getUri ( );
		if ( StringUtils.hasText ( uri ) ){
			for ( String allowUri : allowUriList ){
				if ( uri.startsWith ( allowUri ) ){
					tree.setEnable ( true );
					if ( tree.getParent ( ) != null ){
						tree.getParent ( ).setEnable ( true );
					}
				}
			}
		}
		for ( Menu child : tree.getChildren ( ) ){
			getTree ( child , allowUriList );
		}
	}

	public void load ( ) {
		Menu menu = menuService.getTree ( 185 );
		String json = list ( menu );
		// System.out.println(menu.getChildren().get(0).getChildren());
		System.out.println ( json );
	}

	public String list ( Menu menu ) {
		Menu parent = menu.getParent ( );
		int pid = 0;
		if ( parent != null ){
			pid = parent.getId ( );
		}
		json += "{id:" + menu.getId ( ) + ", pId:" + pid + ", name:" + menu.getName ( ) + "},\r\n";
		if ( menu.getChildren ( ).size ( ) > 0 ){
			for ( Menu m : menu.getChildren ( ) ){
				list ( m );
			}
		}
		return "[" + json.substring ( 0 , json.length ( ) - 1 ) + "]";
	}
	
	@Test
	public void testGetTree(){
		System.out.println(menuService.getTree(202));
	}
}
