package com.lutongnet.statistics.service;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.statistics.dao.ScheduleLogNew2DAO;
import com.lutongnet.statistics.entity.ScheduleLogNew2;
import com.lutongnet.statistics.vo.req.JobLogReq;

@Service("scheduleLogStoreService")
public class ScheduleLogStoreService {

	private static Logger	log	= LoggerFactory.getLogger(ScheduleLogStoreService.class);

	@Resource(name = "scheduleLogNew2DAO")
	private ScheduleLogNew2DAO	scheduleLogNew2DAO;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(ScheduleLogNew2 scheduleLogStore) {

		log.debug("save input ScheduleLogStore:" + scheduleLogStore);
		scheduleLogNew2DAO.save(scheduleLogStore);
	}

	public void jobLogPage(JobLogReq jobLogReq, HttpServletRequest request) {
		PaginationBean<ScheduleLogNew2> logPageBean = scheduleLogNew2DAO.getPage(jobLogReq);
		
		request.setAttribute("logPageBean", logPageBean);
	}

	public ScheduleLogNew2 jobLogDetail(Integer logId, HttpServletRequest request) {
		ScheduleLogNew2 scheduleLogStore = scheduleLogNew2DAO.get(logId);
		return scheduleLogStore;
	}
}
