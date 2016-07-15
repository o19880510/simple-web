package woo.study.web.system.service;


import java.util.List;








import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.functions.datacenter.DataLoadable;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.dao.PrivilegeDao;
import woo.study.web.system.model.entity.Privilege;
import woo.study.web.system.model.request.PrivilegeReq;
import woo.study.web.system.model.request.PrivilegesAddReq;


@Transactional
@Service ( "privilegeService" )
public class PrivilegeService{
	
	private static Logger	log	= LoggerFactory.getLogger(PrivilegeService.class);

	@Resource(name = "privilegeDao")
	private PrivilegeDao	privilegeDao;
	
	@Resource(name = "dataLoadManagement")
	private DataLoadable dataReloadable;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(PrivilegesAddReq privilegesAddReq, BindingResult result, HttpServletRequest request ) {
		
		log.debug("PrivilegeService add PrivilegesAddReq=" + privilegesAddReq);
		
		
		String group = privilegesAddReq.getGroup();
		if(checkGroupExist(group, result)){
			return;
		}
		
		int i = 0;
		for(Privilege privilege : privilegesAddReq.getPrivileges()){
			checkIsExist(i, privilege, result);
			++i;
		}
		if(result.hasErrors()){
			return ;
		}
		
		for(Privilege privilege : privilegesAddReq.getPrivileges()){
			privilege.setGroup(group);
			privilegeDao.save(privilege);
		}

		dataReloadable.load(AppConstant.MemDataKeys.PRIVILEGE_URI_LIST);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PrivilegesAddReq privilegesAddReq, BindingResult result, HttpServletRequest request ) {
		
		String oldGroup = privilegesAddReq.getOldGroup();
		String group = privilegesAddReq.getGroup();
		
		// 权限组名可修改
		// 检查新的名称是否已经存在
		if(oldGroup != null && !oldGroup.equals(group)){
			checkGroupExist(group, result);
		}
		
		// 检查权限名。新增的
		int i = 0;
		for(Privilege privilege : privilegesAddReq.getPrivileges()){
			checkIsExist(i, privilege, result);
			++i;
		}
		if(result.hasErrors()){
			return ;
		}
		
		
		for(Privilege privilege : privilegesAddReq.getPrivileges()){
			privilege.setGroup(group);
			privilegeDao.update(privilege);
		}

		dataReloadable.load(AppConstant.MemDataKeys.PRIVILEGE_URI_LIST);
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void detail(String group, HttpServletRequest request ) {
		List<Privilege> privilegeList = privilegeDao.getPrivilegeGroup(group);
		
		request.setAttribute("privilegeList", privilegeList);
	}
	
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void list(PrivilegeReq privilege, Model model) {

		PaginationBean<Privilege> pb = privilegeDao.page(privilege);
		model.addAttribute("pb", pb);

	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(String group,HttpServletRequest request ) {
		
		List<Privilege> privilegeList = privilegeDao.getPrivilegeGroup(group);
		for(Privilege privilege : privilegeList){
			privilegeDao.remove(privilege.getId());
		}
		
		dataReloadable.load(AppConstant.MemDataKeys.PRIVILEGE_URI_LIST);
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getPrivilegeUriList(){
		return privilegeDao.getPrivilegeUriList();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void get(String group, Model model) {
		List<Privilege> privilegeList = privilegeDao.getPrivilegeGroup(group);

		PrivilegesAddReq privilegesAddReq = new PrivilegesAddReq();
		privilegesAddReq.setGroup(privilegeList.get(0).getGroup());
		privilegesAddReq.setOldGroup(privilegeList.get(0).getGroup());
		privilegesAddReq.setPrivileges(privilegeList);

		model.addAttribute("privilegesAddReq", privilegesAddReq);
	}
	
	private boolean checkIsExist(int i, Privilege privilege, BindingResult result){
		
		if (privilegeDao.existsByName(privilege.getName(), privilege.getId())) {
			result.rejectValue("privileges[" + i + "].name", "privilege.name.exists");
			return true;
		}
		return false;
	}
	
	private boolean checkGroupExist(String group, BindingResult result){
		
		List<Privilege> privilegeList = privilegeDao.getPrivilegeGroup(group);
		if(AssertHelper.notEmpty(privilegeList)){
			result.rejectValue("group", "privilege.group.exists");
			return true;
		}
		
		return false;
	}
	
}
