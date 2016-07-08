package com.lutongnet.system.service;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.system.dao.PrivilegeDao;
import com.lutongnet.system.dao.RoleDao;
import com.lutongnet.system.model.entity.Privilege;
import com.lutongnet.system.model.entity.Role;
import com.lutongnet.system.model.request.RoleReq;


@Transactional
@Repository
@Service("roleService")
public class RoleService {
	
	private static Logger log	= LoggerFactory.getLogger(RoleService.class);
	
	@Resource(name = "roleDao")
	private RoleDao			roleDao;

	@Resource(name = "privilegeDao")
	private PrivilegeDao	privilegeDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	public void list(RoleReq roleReq, Model model) {
		PaginationBean<Role> pb = roleDao.list(roleReq);
		
		model.addAttribute ( "pb" , pb );
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void getRoleInfos(Integer id, Model model) {
		Role role = roleDao.get(id);
		for (Privilege privilege : role.getPrivileges()) {
			role.getAssignedPrivilegeIds().add(privilege.getId());
		}
		model.addAttribute("role", role);
		model.addAttribute("groupMap", getGroupMap());

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void addForm(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		model.addAttribute("groupMap", getGroupMap());
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void updateForm(Integer id, Model model) {
		Role role = roleDao.get(id);
		for (Privilege privilege : role.getPrivileges()) {
			role.getAssignedPrivilegeIds().add(privilege.getId());
		}
		model.addAttribute("role", role);
		model.addAttribute("groupMap", getGroupMap());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Integer id) {
		roleDao.remove(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Role role) {
		Role dbRole = roleDao.get(role.getId());
		dbRole.setPrivileges(role.getPrivileges());
		dbRole.setName(role.getName());
		dbRole.setDescription(role.getDescription());
		roleDao.update(dbRole);
	}

	private Map<String, List<Privilege>> getGroupMap() {
		List<Privilege> allPrivileges = privilegeDao.getAll();
		Set<String> groups = new HashSet<String>();
		for (Privilege privilege : allPrivileges) {
			groups.add(privilege.getGroup());
		}
		Map<String, List<Privilege>> groupMap = new HashMap<String, List<Privilege>>();
		for (String group : groups) {
			groupMap.put(group, new ArrayList<Privilege>());
		}
		for (Privilege privilege : allPrivileges) {
			String group = privilege.getGroup();
			if (groups.contains(privilege.getGroup())) {
				groupMap.get(group).add(privilege);
			}
		}
		return groupMap;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Role role, BindingResult result, Model model) {
		
		if (checkPrivileges(role, result, model)) {
			
			roleDao.save(role);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Role role, BindingResult result, Model model) {

		if (checkPrivileges(role, result, model)) {
			update(role);
		}
	}

	private boolean checkPrivileges(Role role, BindingResult result, Model model) {

		List<Integer> assignedPrivilegeIds = role.getAssignedPrivilegeIds();
		List<Privilege> privileges = (List<Privilege>) privilegeDao.findByIds(assignedPrivilegeIds);
		role.setPrivileges(privileges);
		model.addAttribute("assignedPrivilegeIds", assignedPrivilegeIds);
		model.addAttribute("groupMap", getGroupMap());

		if (assignedPrivilegeIds == null) {
			result.rejectValue("assignedPrivilegeIds", "role.privileges.required");
			return false;
		}

		if (roleDao.existsByName(role.getName(), role.getId())) {
			result.rejectValue("name", "role.name.exists");
			return false;
		}

		return true;
	}
}
