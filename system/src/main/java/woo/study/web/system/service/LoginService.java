package woo.study.web.system.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import woo.study.web.common.functions.actionlog.constants.ThreadLocalConstants;
import woo.study.web.common.functions.datacenter.DataManagementHelper;
import woo.study.web.common.message.ActionResult;
import woo.study.web.common.thread.ThreadLocalMaps;
import woo.study.web.common.util.JSON2Helper;
import woo.study.web.system.constant.AppConstant;
import woo.study.web.system.constant.PageConstants;
import woo.study.web.system.dao.ActionLogDao;
import woo.study.web.system.dao.UserDao;
import woo.study.web.system.datacenter.SystemDataManagementHelper;
import woo.study.web.system.model.entity.ActionLog;
import woo.study.web.system.model.entity.Menu;
import woo.study.web.system.model.entity.Privilege;
import woo.study.web.system.model.entity.Role;
import woo.study.web.system.model.entity.User;
import woo.study.web.system.model.request.LoginReq;
import woo.study.web.system.model.vo.UserInfo;


@Transactional
@Service("loginService")
public class LoginService {
	
	private static Logger log	= LoggerFactory.getLogger(LoginService.class);
	
	@Resource(name ="userDao")
	private UserDao userDao;
	
	@Resource(name ="actionLogDao")
	private ActionLogDao actionLogDao;
	
	/**
	   * 　REQUIRED:业务方法需要在一个容器里运行。
	 *  如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void login( LoginReq loginReq, BindingResult result, HttpServletRequest request){

		String userid = loginReq.getUserid();
		String password = loginReq.getPassword();
		String verifyCode = loginReq.getVerifyCode();
		String verifyCode2 = (String) request.getSession().getAttribute(AppConstant.VERIFY_CODE);
		if (!verifyCode.equals(verifyCode2)) {
			// 验证码错误
			log.info("验证码错误");
			result.rejectValue("verifyCode", "login.verifyCode.mismatch");
			return ;
		}

		Map<String, UserInfo> userHolder = (Map<String, UserInfo>) request.getSession().getServletContext().getAttribute(AppConstant.USER_HOLDER);
		if (!loginReq.isForceLogin()) {
			// 非强行登录
			for (UserInfo u : userHolder.values()) {
				if (u.getUserid().equals(userid)) {
					// 账号已经在别处登录
					result.reject("login.status.logined");
					request.setAttribute("userid", userid);
					request.setAttribute("password", password);
					return ;
				}
			}
		}

		User user = userDao.getUser(userid, password);
		if (user == null) {
			result.reject("login.info.mismatch"); // , "账号或密码错误"
			return ;
		} else {
			if (User.Status.DISABLE.name().equals(user.getStatus())) {
				result.reject("login.status.disable"); // 账号已禁用
				return ;
			}
		}

		log.info("login do business");
		// 更新登录时间
		userDao.updateLoginInfo(userid, User.Status.ONLINE.name());

		UserInfo userInfo = new UserInfo();
		userInfo.setUserid(user.getUserid());
		userInfo.setNickname(user.getNickname());
		userInfo.setProvince(user.getProvince());
		userInfo.setCity(user.getCity());
		userInfo.setStatus(user.getStatus());
		userInfo.setAllowUriSet(getAllowUriSet(user));

		// 创建新session
		request.getSession().invalidate();
		HttpSession session = request.getSession(true);
		String sessionid = session.getId();
		userHolder.put(sessionid, userInfo);
		Map<String, String> sessionidHolder = (Map<String, String>) session.getServletContext().getAttribute(AppConstant.SESSIONID_HOLDER);
		// 替换旧的用户sessionid
		String oldSessionid = sessionidHolder.put(userid, sessionid);
		userHolder.remove(oldSessionid);
		
		Menu tree = (Menu) SystemDataManagementHelper.getInstance().getMenuTree().get(AppConstant.MemDataKeys.SYSTEM_MENU_TREE);
		
		log.debug("SYSTEM_MENU_TREE:"+tree.toString());
		log.debug("userInfo.getAllowUriList():"+userInfo.getAllowUriSet());
		
		// 复制一棵树
		String treeJSON = JSON2Helper.toJson(tree);
		Menu userTree = JSON2Helper.toObject(treeJSON, Menu.class);

		// 根据用户能访问的地址，对比菜单的链接地址，生成最终的菜单
		generateTree(userTree, userInfo.getAllowUriSet(), session);
		request.getSession().setAttribute(AppConstant.USER_MENU_TREE, userTree);
		request.getSession().setAttribute(AppConstant.USER_INFO, userInfo);
		request.getSession().setAttribute(AppConstant.USER_ID, userInfo.getUserid());
		
		request.getSession().setAttribute(ThreadLocalConstants.USER_ID, userInfo.getUserid());
		
		log.debug("USER_MENU_TREE:" + userTree);
		
		saveActionLog(userid);
		int mid1 = 0;
		for (Menu node : userTree.getChildren()) {
			if (node.isEnable()) {
				mid1 = node.getId();
				break;
			}
		}
		session.setAttribute("mid1", String.valueOf(mid1));
		log.info("login do business over");
	}
	
	
	private void saveActionLog(String userId){
		ActionLog actionLog = new ActionLog();
		actionLog.setContent("登录系统");
		actionLog.setActor(userId);
		actionLog.setType(ActionResult.Type.SUCCESS.name());
		actionLog.setParam("userid:" + userId + "<br/>" + "password:***");
		actionLog.setUri(PageConstants.Login.LOGIN);
		actionLog.setAddDate(DateTime.now());
		actionLogDao.save(actionLog);
		
	}
	/**
	 * @return
	 */
	private void generateTree(Menu tree, Set<String> allowUriSet, HttpSession session) {
		Menu parent = tree.getParent();
		String uri = tree.getUri();
		if (StringUtils.hasText(uri)) {
			if (uri.toLowerCase().startsWith("http://") || uri.toLowerCase().startsWith("https://")) {
				// 外链都显示
				tree.setEnable(true);
			} else {
				// 内链
				for (String allowUri : allowUriSet) {
					if (uri.startsWith(allowUri)) {
						tree.setEnable(true);
					}
				}
				tree.setUri(uri);
			}
		}

		for (Menu child : tree.getChildren()) {
			generateTree(child, allowUriSet, session);
		}

		if (tree.isEnable()) {
			if (parent != null) {
				parent.setEnable(true);
			}
		}
	}
	
	/**
	 * 获取用户可以访问的URI列表
	 */
	private Set<String> getAllowUriSet(User user) {
		
		Set<String> allowUriSet = new HashSet<String>();
		
		for (Role role : user.getRoles()) {
			for (Privilege privilege : role.getPrivileges()) {
				allowUriSet.add(privilege.getUri());
			}
		}
		return allowUriSet;
	}
}
