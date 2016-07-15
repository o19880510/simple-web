package woo.study.web.system.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.message.SuccessActionResult;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.dao.RoleDao;
import woo.study.web.system.dao.UserDao;
import woo.study.web.system.model.entity.Role;
import woo.study.web.system.model.entity.User;
import woo.study.web.system.model.request.UserReq;
import woo.study.web.system.model.vo.UserInfo;

@Transactional
@Service ( "userService" )
public class UserService {
	
	private static Logger log	= LoggerFactory.getLogger(UserService.class);
	
	@Resource(name ="userDao")
	private UserDao userDao;
	
	@Resource(name ="roleDao")
	private RoleDao roleDao;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void list(UserReq userReq, Model model, HttpServletRequest request) {
		PaginationBean<User> pb = userDao.list(userReq);
		Map<String, UserInfo> userHolder = (Map<String, UserInfo>) request.getSession().getServletContext().getAttribute(AppConstant.USER_HOLDER);
		Collection<UserInfo> userList = userHolder.values();
		Set<String> useridSet = new HashSet<String>();
		
		for (UserInfo ui : userList) {
			useridSet.add(ui.getUserid());
		}
		
		for (int i = 0; i < pb.getDataList().size(); ++i) {
			User u = (User) pb.getDataList().get(i);
			if (useridSet.contains(u.getUserid())) {
				u.setStatus(User.Status.ONLINE.name());
			}
		}
		model.addAttribute("pb", pb);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void addForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleDao.getAll());
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void updateForm(Integer id, Model model) {
		User user = userDao.get(id);
		model.addAttribute("user", user);
		model.addAttribute("roles", roleDao.getAll());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void remove(Integer id) {
		userDao.remove(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateStatus(Integer id, String status) {
		userDao.updateStatus(id, status);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void changePasswordForm(Integer id, Model model) {
		User dbUser = userDao.get(id);
		
		UserReq userReq = new UserReq();
		userReq.setUserid(dbUser.getUserid());
		userReq.setId(id);
		
		model.addAttribute("user", userReq);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void changePassword(UserReq userReq){
		Integer id = userReq.getId ( );
		String password = userReq.getNewPassword ( );
		userDao.updatePassword ( id , password );
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateLoginInfo ( String userid , String status ) {
		userDao.updateLoginInfo(userid, status);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateLogoutInfo ( String userid , String status ) {
		userDao.updateStatus(userid, status);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update ( User inUser) {
		User dbUser = userDao.get(inUser.getId());
		log.debug("XXX roles:" + inUser.getRoles());
		
		String nickname = inUser.getNickname();
		if(AssertHelper.notEmpty(inUser.getNickname())){
			dbUser.setNickname(nickname);
		}
		
		String province = inUser.getProvince();
		if(AssertHelper.notEmpty(province)){
			dbUser.setProvince(province);
		}
		
		String city = inUser.getCity();
		if(AssertHelper.notEmpty(city)){
			dbUser.setCity(city);
		}
		
		List<Role> roles = new ArrayList<Role>();
		for(Role role:inUser.getRoles()){
			Integer roleId = role.getId();
			if(roleId != null){
				roles.add(roleDao.get(roleId));
			}
		}
		dbUser.setRoles(roles);
		
		userDao.update(dbUser);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUserRoles(User user) {
		user.setStatus(User.Status.ENABLE.name());
		user.setCreateDate(DateTime.now());
		List<Role> roles = new ArrayList<Role>();
		for (Role role : user.getRoles()) {
			if (role.getId() != null) {
				roles.add( roleDao.get(role.getId()) );
			}
		}

		User oldUser = userDao.get(user.getId());
		oldUser.setRoles(roles);
		oldUser.setProvince(user.getProvince());
		oldUser.setCity(user.getCity());
		oldUser.setNickname(user.getNickname());
		
		userDao.update(oldUser);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user.setStatus(User.Status.ENABLE.name());
		user.setCreateDate(DateTime.now());
		
		userDao.add(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void changeSelfPassword(UserReq userReq , BindingResult result , HttpServletRequest request){
		String userid = userReq.getUserid ( );
		String oldPassword = userReq.getOldPassword ( );
		String newPassword = userReq.getNewPassword ( );
		if ( !userDao.isOldPasswordMatch ( userid , oldPassword ) ){
			result.rejectValue ( "oldPassword" , "user.oldpassword.mismatch" );
			return;
		}
		
		userDao.updateSelfPassword(userid, newPassword);
		request.setAttribute("actionResult", new SuccessActionResult());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public User get(String userid ,String password){
		return userDao.getUser(userid, password);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(User user , BindingResult result , Model model){
		model.addAttribute("roles", roleDao.getAll());
		
		log.debug("add() User:"+user);
		boolean emptyRole = isEmptyhasRole(user);

		if (emptyRole) {
			result.rejectValue("roles", "user.roles.min");
			return;
		}
		if (userDao.existsByProperty("userid", user.getUserid(), user.getId())) {
			result.rejectValue("userid", "user.userid.exists");
			return;
		}
		
		
		List<Role> roles = new ArrayList<Role>();
		for (Role role : user.getRoles()) {
			if (role.getId() != null) {
				roles.add(roleDao.get(role.getId()));
			}
		}
		user.setRoles(roles);
		
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user.setStatus(User.Status.ENABLE.name());
		user.setCreateDate(DateTime.now());
		userDao.save(user);
	}
	
	private boolean isEmptyhasRole(User user){
		for (Role role : user.getRoles()) {
			if (role.getId() != null) {
				return false;
			}
		}
		return true;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(User user , BindingResult result , Model model){
		model.addAttribute ( "roles" , roleDao.getAll ( ) );
		boolean emptyRole = isEmptyhasRole(user);
	
		if ( emptyRole ){
			result.rejectValue ( "roles" , "user.roles.min" );
			return ;
		}
		
		update(user);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public User getUser(String userId, String password){
		
		return userDao.getUser(userId, password);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUserStatus() {
		userDao.updateStatus();
	} 
}
