
package woo.study.web.business.statistics.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woo.study.web.business.statistics.entity.ScheduleLogNew2;
import woo.study.web.business.statistics.vo.req.JobLogReq;
import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.util.AssertHelper;


@Component("scheduleLogNew2DAO")
public class  ScheduleLogNew2DAO extends EntityDaoSupport<ScheduleLogNew2>{
	
	private static Logger log	= LoggerFactory.getLogger(ScheduleLogNew2DAO.class);
	
	public PaginationBean<ScheduleLogNew2> getPage(JobLogReq req){
		
		StringBuilder hql = new StringBuilder("where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		
		if (AssertHelper.notEmpty(req.getJobClass())) {
			hql.append(" and jobClass like ? ");
			paramList.add("%" + req.getJobClass() + "%");
		}
		
		if (AssertHelper.notEmpty(req.getStatus())) {
			hql.append(" and status = ? ");
			paramList.add(req.getStatus());
		}
		
		if (req.getStartDate() != null) {
			hql.append(" and runDate >= ? ");
			paramList.add(req.getStartDate());
		}
		
		if (req.getEndDate() != null) {
			hql.append(" and runDate <= ? ");
			paramList.add(req.getEndDate());
		}
		
		hql.append(" order by runDate desc ");
		
		return findByPaging(hql.toString(), paramList, req.getCurrent(), req.getPageSize());
	}
}


