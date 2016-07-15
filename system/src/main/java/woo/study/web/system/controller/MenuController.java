package woo.study.web.system.controller;

import java.util.HashMap;


import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import woo.study.web.common.message.SuccessActionResult;
import woo.study.web.common.util.JSON2Helper;
import woo.study.web.system.annotation.Log;
import woo.study.web.system.annotation.MarkRequest;
import woo.study.web.system.constant.PageConstants;
import woo.study.web.system.model.entity.Menu;
import woo.study.web.system.model.request.MenuReq;
import woo.study.web.system.service.MenuService;
import woo.study.web.system.util.HttpUtils;


@Controller
@RequestMapping ( value = "/system" )
public class MenuController {

	@Resource ( name = "menuService" )
	private MenuService		menuService;

	@Autowired
	private MessageSource	messageSource;

	/**
	 * 查看所有菜单树
	 * 
	 * @return
	 */
	@RequestMapping ( value = "/menu/list" )
	@MarkRequest
	@Log ( "查看菜单树列表" )
	public String list ( @ModelAttribute ( "menu" ) MenuReq menuReq , Model model ) {
		menuService.list(menuReq, model);
		return PageConstants.Menu.LIST;
	}

	/**
	 * 初始化新增菜单树
	 * 
	 * @return
	 */
	@RequestMapping ( value = "/menu/addTree" , method = RequestMethod.GET )
	public String addForm ( @ModelAttribute ( "tree" ) Menu menu ) {
		return PageConstants.Menu.ADD;
	}

	/**
	 * 添加菜单树
	 * 
	 * @param menu
	 * @param result
	 * @param locale
	 * @return
	 */
	@RequestMapping ( value = "/menu/addTree" , method = RequestMethod.POST )
	@Log ( "添加菜单树" )
	public ResponseEntity<String> addTree ( @ModelAttribute ( "tree" ) Menu menu , BindingResult result , Locale locale ) {
		
		Boolean hasError = menuService.addTree(menu, locale);
		if (hasError){
			String info = messageSource.getMessage ( "menu.treename.exists" , null , locale );
			HttpHeaders headers = new HttpHeaders ( );
			headers.set ( "Content-Type" , "text/plain;charset=utf-8" );
			return new ResponseEntity<String> ( info , headers , HttpStatus.BAD_REQUEST );
		}else{
			return new ResponseEntity<String> ( String.valueOf ( menu.getId ( ) ) , HttpStatus.OK );
		}
	}

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 * @param locale
	 * @return
	 */
	@RequestMapping ( value = "/menu/addMenu" , method = RequestMethod.POST )
	@Log ( "添加菜单" )
	public ResponseEntity<String> addMenu ( Menu menu , Locale locale ) {
		
		boolean hasError = menuService.addMenu(menu, locale);
		if (hasError ){
			String info = messageSource.getMessage ( "menu.nodename.exists" , null , locale );
			HttpHeaders headers = new HttpHeaders ( );
			headers.set ( "Content-Type" , "text/plain;charset=utf-8" );
			return new ResponseEntity<String> ( info , headers , HttpStatus.BAD_REQUEST );
		}else{
			return new ResponseEntity<String> ( String.valueOf ( menu.getId ( ) ) , HttpStatus.OK );
		}
	}

	/**
	 * 删除菜单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping ( value = "/menu/remove" , method = RequestMethod.POST )
	@Log ( "删除菜单" )
	public ResponseEntity<String> remove ( Integer id ) {
		menuService.remove ( id );
		return new ResponseEntity<String> ( HttpStatus.OK );
	}

	/**
	 * 移动菜单
	 * 
	 * @param id
	 * @param pid
	 * @param prevId
	 * @param nextId
	 * @return
	 */
	@RequestMapping ( value = "/menu/move" , method = RequestMethod.POST )
	@Log ( "移动菜单" )
	public ResponseEntity<String> move ( Integer id , Integer pid , Integer prevId , Integer nextId ) {
		menuService.move ( id , pid , prevId , nextId );
		return new ResponseEntity<String> ( HttpStatus.OK );
	}

	/**
	 * 获取菜单信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping ( value = "/menu/get" , produces = "application/json" )
	public Map<String, Object> get ( Integer id ) {
		return menuService.get ( id );
	}

	/**
	 * 更新菜单
	 * 
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping ( value = "/menu/updateMenu" , produces = "application/json" )
	@Log ( "修改菜单" )
	public ResponseEntity<String> update ( Menu menu , Locale locale ) throws Exception {
		
		boolean hasError = menuService.update(menu);
		
		HttpHeaders headers = new HttpHeaders ( );
		if ( hasError ){
			String info = messageSource.getMessage ( "menu.nodename.exists" , null , locale );
			headers.set ( "Content-Type" , "text/plain;charset=utf-8" );
			return new ResponseEntity<String> ( info , headers , HttpStatus.BAD_REQUEST );
		}

		Map<String, Object> rsp = new HashMap<String, Object> ( );
		rsp.put ( "id" , menu.getId ( ) );
		rsp.put ( "pid" , menu.getParent ( ).getId ( ) );
		rsp.put ( "name" , menu.getName ( ) );
		rsp.put ( "uri" , menu.getUri ( ) );
		rsp.put ( "style" , menu.getStyle ( ) );
		String json = JSON2Helper.toJson(rsp);
		headers.set ( "Content-Type" , "application/json;charset=utf-8" );
		return new ResponseEntity<String> ( json , headers , HttpStatus.OK );
	}

	/**
	 * 将菜单树载入到 application 中
	 * 
	 * @param id
	 * @param session
	 */
	@RequestMapping ( value = "/menu/load/{id}" )
	public ResponseEntity<String> load ( @PathVariable Integer id , HttpSession session ) {
		Menu tree = menuService.getTree ( id );
		session.getServletContext ( ).setAttribute ( tree.getName ( ) , tree );
		return new ResponseEntity<String> ( HttpStatus.OK );
	}

	@RequestMapping ( value = "/menu/removeTree" )
	@Log ( "删除菜单树" )
	public String removeTree ( Integer id , HttpServletRequest request , HttpSession session , RedirectAttributes ra ) {
		Menu tree = menuService.getTree ( id );
		menuService.remove ( tree.getId ( ) );
		session.getServletContext ( ).removeAttribute ( tree.getName ( ) );
		ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
		return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" );
	}

	@RequestMapping ( value = "/menu/update" , method = RequestMethod.GET )
	public String updateTree ( Integer id , Model model ) {
		Menu tree = menuService.getTree ( id );
		model.addAttribute ( "tree" , tree );
		model.addAttribute ( "treeNodes" , getTreeJSON ( tree ) );
		treeJSON = "";
		return PageConstants.Menu.UPDATE;
	}

	private String	treeJSON	= "";

	public String getTreeJSON ( Menu menu ) {
		Menu parent = menu.getParent ( );
		int pid = 0;
		if ( parent != null ){
			pid = parent.getId ( );
		}
		String uri = menu.getUri ( );
		uri = uri == null ? "" : uri;
		boolean open = "open".equals ( menu.getStyle ( ) ) ? true : false;
		treeJSON += "{id:" + menu.getId ( ) + ", pId:" + pid + ", name:'" + menu.getName ( ) + "', url:'" + uri + "', open:" + open + "},";
		if ( menu.getChildren ( ).size ( ) > 0 ){
			for ( Menu child : menu.getChildren ( ) ){
				getTreeJSON ( child );
			}
		}
		return "[" + treeJSON.substring ( 0 , treeJSON.length ( ) - 1 ) + "]";
	}

	/**
	 * 更新菜单树描述
	 * 
	 * @param id
	 * @param description
	 * @return
	 */
	@RequestMapping ( value = "/menu/updateDescription" , method = RequestMethod.POST )
	public ResponseEntity<String> updateDescription ( Integer id , String description ) {
		menuService.updateDescription ( id , description );
		return new ResponseEntity<String> ( HttpStatus.OK );
	}
}
