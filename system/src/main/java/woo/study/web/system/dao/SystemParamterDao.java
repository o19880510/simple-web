package woo.study.web.system.dao;

import java.util.HashMap;


import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.util.StringUtils;
import woo.study.web.system.model.entity.ActionLog;
import woo.study.web.system.model.entity.SystemParamter;
import woo.study.web.system.model.request.LogReq;

@Component("systemParamterDao")
public class SystemParamterDao extends EntityDaoSupport<SystemParamter> {
	
}
