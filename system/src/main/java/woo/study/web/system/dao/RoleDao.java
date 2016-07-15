package woo.study.web.system.dao;


import java.util.HashMap;



import java.util.Map;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.system.model.entity.Role;
import woo.study.web.system.model.request.RoleReq;
import woo.study.web.system.service.RoleService;


@Component("roleDao")
public class RoleDao extends EntityDaoSupport<Role>{
	
	private static Logger log	= LoggerFactory.getLogger(RoleService.class);
	
	/**
	 * 分页查询角色
	 */
	public PaginationBean<Role> list(RoleReq req) {
		StringBuilder condition = new StringBuilder();
		
		Map<String, Object> params = new HashMap<String, Object>();
		String name = req.getName();
		if (AssertHelper.notEmpty(name)) {
			condition.append(" where name like:name");
			params.put("name", "%" + name + "%");
		}
		
		condition.append(" order by id desc");
		return findByPaging(condition.toString(), params, req.getCurrent(), req.getPageSize());
	}


	/**
	 * 获取角色
	 */
	private static final String QUERY_ROLE_AND_PRIVIEGE =  "from Role r left join fetch r.privileges where r.id= ? ";
	public Role getById ( Integer id ) {
		return queryFirst(QUERY_ROLE_AND_PRIVIEGE, id);
	}
	
}
