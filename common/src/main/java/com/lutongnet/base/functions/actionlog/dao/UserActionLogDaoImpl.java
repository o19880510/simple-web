package com.lutongnet.base.functions.actionlog.dao;


import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.functions.actionlog.model.entity.UserActionLogHibernate;
import com.lutongnet.base.functions.actionlog.model.entity.UserActionLog;


@Component("userActionLogDao" )
public class UserActionLogDaoImpl extends EntityDaoSupport<UserActionLogHibernate> implements UserActionLogDao{

	@Override
	public void save(UserActionLog userActionLog) {
		super.save(userActionLog);
	}

	@Override
	public UserActionLog get(String logId) {
		return super.get(logId);
	}

	@Override
	public void update(UserActionLog userActionLog) {
		super.update(userActionLog);
	}


}
