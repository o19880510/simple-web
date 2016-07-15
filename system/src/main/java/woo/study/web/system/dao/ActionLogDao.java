package woo.study.web.system.dao;

import java.util.HashMap;



import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.system.model.entity.ActionLog;
import woo.study.web.system.model.request.LogReq;

@Component("actionLogDao")
public class ActionLogDao extends EntityDaoSupport<ActionLog> {

	/**
	 * 获取日志信息
	 */
	private static final String HQL_QUERY_ALL_ACTION_LOG = "from ActionLog a left join fetch a.detail where a.id = ? ";
	public ActionLog get(Integer id) {
		return queryFirst(HQL_QUERY_ALL_ACTION_LOG, id);
	}

	/**
	 * 分页查询日志
	 */
	public PaginationBean<ActionLog> list(LogReq req) {

		String condition = " where 1=1 ";
		Map<String, Object> params = new HashMap<String, Object>();
		String actor = req.getActor();
		String content = req.getContent();
		DateTime from = req.getFrom();
		DateTime to = req.getTo();
		String type = req.getType();

		if (AssertHelper.notEmpty(actor)) {
			condition += " and actor like :actor";
			params.put("actor", "%" + actor + "%");
		}
		if (AssertHelper.notEmpty(content)) {
			condition += " and content like :content";
			params.put("content", "%" + content + "%");
		}
		if (from != null) {
			condition += " and addDate>=:from";
			params.put("from", from);
		}
		if (to != null) {
			condition += " and addDate<:to";
			params.put("to", to.plusDays(1));
		}
		if (AssertHelper.notEmpty(type)) {
			condition += " and type=:type";
			params.put("type", type);
		}
		return findByPaging(condition + " order by id desc", params, req.getCurrent(), req.getPageSize());
	}

}
