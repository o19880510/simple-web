package woo.study.web.system.dao;

import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.system.model.entity.ScheduleLog;

@Component("scheduleLogDao")
@Deprecated
public class ScheduleLogDao extends EntityDaoSupport<ScheduleLog>{

}
