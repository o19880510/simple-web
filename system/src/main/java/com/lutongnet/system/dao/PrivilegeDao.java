package com.lutongnet.system.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.dao.parameter.ListSqlParameter;
import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.system.model.entity.Privilege;
import com.lutongnet.system.model.request.PrivilegeReq;


@Component("privilegeDao")
public class PrivilegeDao  extends EntityDaoSupport<Privilege>{
	
	private static final String HQL_PAGE_COUNT = "select count(distinct p.group) from Privilege p where 1=1 ";
	public PaginationBean<Privilege> page(PrivilegeReq req) {
		
		StringBuilder hqlCount = new StringBuilder(HQL_PAGE_COUNT);
		List paramsCount = setParams(hqlCount, req);
		
		int totalCount = queryInt(hqlCount.toString(), paramsCount);
		
		StringBuilder hqlPage = new StringBuilder(" from Privilege p where 1=1  ");
		List paramsPage = setParams(hqlPage, req);
		
		hqlPage.append(" group by group order by id ");
		
		PaginationBean<Privilege> pb = new PaginationBean<Privilege>(req.getCurrent(), totalCount, req.getPageSize());
		List<Privilege> dataList = getRecordQuery(hqlPage.toString(), new ListSqlParameter(paramsPage))
								.setFirstResult(pb.getStart())
								.setMaxResults(pb.getPageSize())
								.getResultList();
		pb.setDataList(dataList);
		
		return pb;
	}
	
	private List setParams(StringBuilder hql, PrivilegeReq req){
		List params = new ArrayList();
		
		String group = req.getGroup();
		if(AssertHelper.notEmpty(group)){
			hql.append(" and p.group like ? ");
			params.add(group);
		}
		
		return params;
	}

	/**
	 * 返回系统所有权限
	 */
	private static final String HQL_QUERY_ALL_PRIVILEGE = "from Privilege order by group,id";
	public List<Privilege> getAll ( ) {
		return queryList(HQL_QUERY_ALL_PRIVILEGE);
	}

	/**
	 * 根据ID查询
	 */
	private static final String HQL_QUERY_PRIVILEGE_BY_IDS = "from Privilege where id in:ids ";
	public List<Privilege> findByIds ( List<?> ids ) {
		if (ids == null) {
			return new ArrayList<Privilege>();
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ids", ids);
			return queryList(HQL_QUERY_PRIVILEGE_BY_IDS, params);
		}
	}

	/**
	 * 获取所有权限URI
	 */
	private static final String HQL_QUERY_URI = "select uri from Privilege p";
	public List<String> getPrivilegeUriList() {
		return queryList(HQL_QUERY_URI, String.class);
	}
	
	/**
	 *  查询权限组
	 */
	private static final String HQL_QUERY_PRIVILEGES_BY_GROUP = "from Privilege where group = ? order by id";
	public List<Privilege> getPrivilegeGroup (String group ) {
		return queryList(HQL_QUERY_PRIVILEGES_BY_GROUP, group);
	}
	
}
