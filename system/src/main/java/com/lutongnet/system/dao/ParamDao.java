package com.lutongnet.system.dao;


import java.util.HashMap;




import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.system.model.entity.Param;
import com.lutongnet.system.model.entity.ParamItem;
import com.lutongnet.system.model.request.ParamReq;


@Component("paramDao")
public class ParamDao extends EntityDaoSupport<Param>{
	
	/**
	 * 查询参数
	 */
	public PaginationBean<Param> list(ParamReq req) {
		
		String condition = " where 1=1 ";
		String name = req.getName();
		String description = req.getDescription();
		
		Map<String, Object> params = new HashMap<String, Object>();
		if (AssertHelper.notEmpty(name)) {
			condition += " and name like :name";
			params.put("name", "%" + name + "%");
		}
		
		if (AssertHelper.notEmpty(description)) {
			condition += " and description like :description";
			params.put("description", "%" + description + "%");
		}
		
		return findByPaging(condition + " order by id desc", params, req.getCurrent(), req.getPageSize());
	}
	
	/**
	 * 获取全部参数
	 */
	public Map<String, Map<String, String>> getAllParams() {
		
			List<Param> params = getAll();

			Map<String, Map<String, String>> paramMap = new LinkedHashMap<String, Map<String, String>>();
			for (Param param : params) {
				Map<String, String> itemMap = new LinkedHashMap<String, String>();
				for (ParamItem item : param.getItems()) {
					itemMap.put(item.getName(), item.getValue());
				}
				paramMap.put(param.getName(), itemMap);
			}
			return paramMap;
		
	}
	
	/**
	 * 添加参数
	 */
	public void add ( Param param ) {
		for ( ParamItem item : param.getItems ( ) ){
			item.setParam ( param );
		}
		save ( param );
	}

	/**
	 * 获取参数信息
	 */
	// 避免使用 left join fetch 时出现的 firstResult/maxResults specified with
	// collection fetch; applying in memory! 警告
	private static final String HQL_QUERY_ALL_PARAM = "from Param p left join fetch p.items items where p.id= ? ";
	public Param get ( Integer id ) {
		return queryFirst(HQL_QUERY_ALL_PARAM , id);
	}

	/**
	 * 修改参数
	 */
	public void update(Param param) {
		for (ParamItem item : param.getItems()) {
			item.setParam(param);
		}
		super.update(param);
	}

	
}
