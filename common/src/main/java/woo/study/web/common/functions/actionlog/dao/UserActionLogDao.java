package woo.study.web.common.functions.actionlog.dao;

import woo.study.web.common.functions.actionlog.model.entity.UserActionLog;

public interface UserActionLogDao {

	public void save(UserActionLog userActionLog);

	public UserActionLog get(String logId);

	public void update(UserActionLog userActionLog);
}
