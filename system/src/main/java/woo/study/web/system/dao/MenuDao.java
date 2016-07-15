package woo.study.web.system.dao;

import java.util.HashMap;



import java.util.List;
import java.util.Map;





import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.system.model.entity.Menu;
import woo.study.web.system.model.request.MenuReq;

@Component("menuDao")
public class MenuDao extends EntityDaoSupport<Menu> {

	/**
	 * 查询菜单树
	 */
	public PaginationBean<Menu> list(MenuReq req) {
		String condition = " where parent is null";
		String keyword = req.getKeyword();
		Map<String, Object> params = new HashMap<String, Object>();
		if (AssertHelper.notEmpty(keyword)) {
			condition += " and (lower(name) like :name or lower(description) like :description)";
			params.put("name", "%" + keyword.toLowerCase() + "%");
			params.put("description", "%" + keyword.toLowerCase() + "%");
		}
		return findByPaging(condition, params, req.getCurrent(), req.getPageSize());
	}

	/**
	 * 添加菜单树、菜单、节点
	 */
	private static final String HQL_SELECT_POSTION = " select max(position)+1 from Menu where parent.id = ? ";
	public void add(Menu menu) {
		if (menu.getParent() != null) {
			int position = queryInt(HQL_SELECT_POSTION, menu.getParent().getId());
			menu.setPosition(position);
		}

		save(menu);
	}

	/**
	 * 判断在同一父节点下是否存在相同的节点名
	 */
	private static final String HQL_SELECT_PARENT = "from Menu where name=? and parent.id=?";
	public boolean existsInParent(Menu menu) {
		Object[] params = {menu.getName(),  menu.getParent().getId()};
		List<Menu> dataList = queryList(HQL_SELECT_PARENT, params);
		return dataList.size() > 0;
	}

	/**
	 * 更新时判断是否存在相同的节点名称
	 */
	private static final String HQL_SELECT_SAME_NAME = "from Menu where name=? and parent.id=? and id!=?";
	public boolean existsInParentWhenUpdate(Menu menu) {
		
		Object[] params = {menu.getName(),  menu.getParent().getId(),  menu.getId()};
		List<?> dataList = queryList(HQL_SELECT_SAME_NAME, params);
		return dataList.size() > 0;
	}

	

	/**
	 * 更新菜单
	 */
	private static final String HQL_UPDATE_MENU = "update Menu set name= ? , uri= ? , style= ?  where id= ? ";
	public void update(Menu menu) {
		Object[] params = {menu.getName(), menu.getUri(),  menu.getStyle(), menu.getId(),};
		update(HQL_UPDATE_MENU, params);
	}

	
	/**
	 * 获取一个节点和子节点
	 */
	private static final String HQL_QUERY_ALL_MENU = "from Menu m left join fetch m.children where m.id = ? ";
	public Menu getNode(Integer id) {
		return queryFirst(HQL_QUERY_ALL_MENU, id);
	}

	/**
	 * 根据菜单树名获取整棵树
	 */
	private static final String HQL_GET_MENU_BY_NAME = "from Menu where name= ? ";
	public Menu getTreeByName(String name) {
		return queryFirst(HQL_GET_MENU_BY_NAME, name);
	}

	/**
	 * 获取所有树根节点
	 */
	private static final String HQL_GET_ROOT_MENUS = "from Menu where parent is null";
	public List<Menu> getAllTree() {
		return queryList(HQL_GET_ROOT_MENUS);
	}

	/**
	 * 更新菜单树描述
	 */
	private static final String HQL_UPDATE_MENU_DESC = "update Menu set description= ?  where id= ? ";
	public void updateDescription(Integer id, String description) {
		update(HQL_UPDATE_MENU_DESC, description, id);
	}

}
