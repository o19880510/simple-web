package com.lutongnet.base.functions.actionlog.dao;

import com.lutongnet.base.functions.actionlog.model.entity.UserActionLog;

public interface UserActionLogDao {

	public void save(UserActionLog userActionLog);

	public UserActionLog get(String logId);

	public void update(UserActionLog userActionLog);
}
