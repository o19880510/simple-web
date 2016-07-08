package com.lutongnet.system.dao;

import java.util.HashMap;


import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.base.util.StringUtils;
import com.lutongnet.system.model.entity.ActionLog;
import com.lutongnet.system.model.entity.SystemParamter;
import com.lutongnet.system.model.request.LogReq;

@Component("systemParamterDao")
public class SystemParamterDao extends EntityDaoSupport<SystemParamter> {
	
}
