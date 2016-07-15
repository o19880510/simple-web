package woo.study.web.common.functions.actionlog.dao;


import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.functions.actionlog.model.entity.UserActionLog;
import woo.study.web.common.functions.actionlog.model.entity.UserActionLogHibernate;


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
