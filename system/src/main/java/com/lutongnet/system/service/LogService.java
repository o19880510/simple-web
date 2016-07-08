package com.lutongnet.system.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.system.dao.ActionLogDao;
import com.lutongnet.system.model.entity.ActionLog;
import com.lutongnet.system.model.request.LogReq;


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
