package com.lutongnet.system.dao;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.dao.vo.PaginationBean;
import com.lutongnet.system.model.entity.Role;
import com.lutongnet.system.model.entity.User;
import com.lutongnet.system.model.request.UserReq;

@Component("userDao")
public class UserDao extends EntityDaoSupport<User> {

	private static Logger	log	= LoggerFactory.getLogger(UserDao.class);

	/**
	 * 分页查询
	 * 
	 * @param req
	 * @return
	 */
	public PaginationBean<User> list(UserReq req) {
		String name = req.getName();
		String status = req.getStatus();

		StringBuilder hql = new StringBuilder(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if (StringUtils.hasText(name)) {
			name = name.toLowerCase();
			hql.append(" and(lower(userid) like ? or lower(nickname) like ?)");
			params.add("%" + name + "%");
			params.add("%" + name + "%");
		}
		if (StringUtils.hasText(status)) {
			hql.append(" and status = ? ");
			params.add(status);
		}

		hql.append(" order by id desc");

		return findByPaging(hql.toString(), params, req.getCurrent(), req.getPageSize());
	}

	/**
	 * 新增账号
	 * 
	 * @param user
	 */
	public void add(User user) {
		List<Role> roles = new ArrayList<Role>();
		for (Role role : user.getRoles()) {
			if (role.getId() != null) {
				roles.add(get(role.getId(), Role.class));
			}
		}
		user.setRoles(roles);
		save(user);
	}

	/**
	 * 根据用户ID、密码获取用户信息
	 */

	private static final String	HQL_QUERY_USER_BY_ID_PWD	= "from User u left join fetch u.roles r where userid=? and password=?";
	private static final String	HQL_QUERY_ROLE_BY_ID		= "from Role r left join fetch r.privileges p where r.id=? ";

	public User getUser(String userid, String password) {
		User user = queryFirst(HQL_QUERY_USER_BY_ID_PWD, userid, DigestUtils.md5Hex(password));

		if (user != null) {
			for (Role role : user.getRoles()) {
				role = queryFirst(HQL_QUERY_ROLE_BY_ID, new Object[] { role.getId() }, Role.class);
			}
		}
		return user;
	}

	/**
	 * 更新登录信息
	 * 
	 * @param userid
	 */
	private static final String	HQL_UPDATE_USER_DATE_STATUS	= "update User set lastLoginDate=?, status=? where userid=?";

	public void updateLoginInfo(String userid, String status) {
		update(HQL_UPDATE_USER_DATE_STATUS, DateTime.now(), status, userid);
	}

	/**
	 * 更新退出信息
	 * 
	 * @param userid
	 * @param status
	 */
	private static final String	HQL_UPDATE_USER_STATUS	= "update User set status=? where userid=?";

	public void updateStatus(String userid, String status) {
		update(HQL_UPDATE_USER_STATUS, status, userid);
	}

	/**
	 * 更新用户状态
	 * 
	 * @param id
	 * @param status
	 */
	private static final String	HQL_UPDATE_USER_STATUS_BY_ID	= "update User set status=? where id=? ";

	public void updateStatus(Integer id, String status) {
		update(HQL_UPDATE_USER_STATUS_BY_ID, status, id);
	}

	/**
	 * 判断旧密码是否正确
	 * 
	 * @param userid
	 * @param oldPassword
	 * @return
	 */
	private static final String	HQL_QUERY_USER_BY_PWD_UID	= "from User where password=? and userid=?";

	public boolean isOldPasswordMatch(String userid, String oldPassword) {
		User userList = queryFirst(HQL_QUERY_USER_BY_PWD_UID, DigestUtils.md5Hex(oldPassword), userid);
		return userList != null;
	}

	/**
	 * 更改自己的密码
	 * 
	 * @param userid
	 * @param newPassword
	 */
	private static final String	HQL_UPDATE_PWD_BY_USER_ID	= "update User set password=? where userid=?";

	public void updateSelfPassword(String userid, String newPassword) {
		update(HQL_UPDATE_PWD_BY_USER_ID, DigestUtils.md5Hex(newPassword), userid);
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param password
	 */
	private static final String	HQL_UPDATE_PWD_BY_ID	= "update User set password=? where id=?";

	public void updatePassword(Integer id, String password) {
		update(HQL_UPDATE_PWD_BY_ID, DigestUtils.md5Hex(password), id);
	}

	/**
	 * 在系统启动后，更新用户状态
	 */
	private static final String	HQL_UPDATE_STATUS_OFFLINE	= "update User set status=? where status=?";

	public void updateStatus() {
		super.update(HQL_UPDATE_STATUS_OFFLINE, User.Status.OFFLINE.name(), User.Status.ONLINE.name());
	}

//	private static final String	TEST	= "select userid , password, nickname from t_user";
//	public void test() {
//		List<UserDTO> userDTOList = getSession().createSQLQuery(TEST)
//				.setResultTransformer(Transformers.aliasToBean(UserDTO.class)).list();
//		log.debug("~~~~~~~~~~userDTOList:" + userDTOList);
//	}
}
