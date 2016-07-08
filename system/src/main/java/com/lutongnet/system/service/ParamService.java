package com.lutongnet.system.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.base.functions.datacenter.DataLoadable;
import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.system.constant.AppConstant;
import com.lutongnet.system.dao.ParamDao;
import com.lutongnet.system.model.entity.Param;
import com.lutongnet.system.model.entity.ParamItem;
import com.lutongnet.system.model.request.ParamReq;

@Transactional
@Service("paramService")
public class ParamService {

	@Resource(name = "paramDao")
	private ParamDao				paramDao;

	@Resource(name = "dataLoadManagement")
	private DataLoadable dataReloadable;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void list(ParamReq paramReq, Model model) {
		PaginationBean<Param> pb = paramDao.list(paramReq);
		model.addAttribute("pb", pb);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void get(Integer id, Model model) {
		Param param = paramDao.get(id);
		model.addAttribute("PARAM", param);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Param param, BindingResult result, Model model, HttpServletRequest request) {

		if (checkParams(param, result)) {
			return;
		}

		if (paramDao.existsByName(param.getName(), param.getId())) {
			result.rejectValue("name", "param.name.exists");
			return;
		}

		paramDao.add(param);

		dataReloadable.load(AppConstant.MemDataKeys.PARAMETER_MAP);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Param param, BindingResult result, Model model, HttpServletRequest request) {

		if (checkParams(param, result)) {
			return;
		}

		if (paramDao.existsByName(param.getName(), param.getId())) {
			result.rejectValue("name", "param.name.exists");
			return;
		}

		paramDao.update(param);

		dataReloadable.load(AppConstant.MemDataKeys.PARAMETER_MAP);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Integer id, HttpServletRequest request) {
		paramDao.remove(id);
		dataReloadable.load(AppConstant.MemDataKeys.PARAMETER_MAP);
	}

	private boolean checkParams(Param param, BindingResult result) {

		int i = 0;
		for (ParamItem item : param.getItems()) {
			if (AssertHelper.isEmpty(item.getName())) {
				result.rejectValue("items[" + i + "].name", "param.itemname.required");
				return true;
			}
			if (AssertHelper.isEmpty(item.getValue())) {
				result.rejectValue("items[" + i + "].value", "param.itemvalue.required");
				return true;
			}
			++i;
		}

		return false;
	}

}
