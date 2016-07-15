package woo.study.web.system.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.system.dao.ActionLogDao;
import woo.study.web.system.model.entity.ActionLog;
import woo.study.web.system.model.request.LogReq;


@Transactional
@Service("logService")
public class LogService{
	
	@Resource(name = "actionLogDao")
	private ActionLogDao actionLogDao;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(ActionLog log){
		actionLogDao.save(log);
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void list( LogReq logReq , Model model){
		PaginationBean<ActionLog> pb = actionLogDao.list(logReq);
		model.addAttribute("pb", pb);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public ActionLog get(Integer id){
		return actionLogDao.get(id);
	}
	
}
