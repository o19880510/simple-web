INSERT INTO `t_menu` VALUES ('1', null, 'SYSTEM_MENU_TREE', 'open', '0', null, '系统功能菜单树');
INSERT INTO `t_menu` VALUES ('101', '1', '系统管理', 'open', '0', null, null);
INSERT INTO `t_menu` VALUES ('201', '101', '用户管理', 'open', '0', 'system/user/list.do', null);
INSERT INTO `t_menu` VALUES ('202', '101', '角色管理', 'open', '1', 'system/role/list.do', null);
INSERT INTO `t_menu` VALUES ('203', '101', '权限管理', 'open', '2', 'system/privilege/list.do', null);
INSERT INTO `t_menu` VALUES ('204', '101', '菜单管理', 'open', '3', 'system/menu/list.do', null);
INSERT INTO `t_menu` VALUES ('205', '101', '参数管理', 'open', '4', 'system/param/list.do', null);
INSERT INTO `t_menu` VALUES ('206', '101', '系统日志', 'open', '5', 'system/log/list.do', null);
INSERT INTO `t_menu` VALUES ('207', '101', '内存数据管理', 'open', '6', 'system/data/list.do', null);
INSERT INTO `t_menu` VALUES ('208', '101', '任务管理', 'open', '7', null, null);
INSERT INTO `t_menu` VALUES ('209', '208', '任务列表', 'open', '0', 'system/statistic/job/list.do', null);
INSERT INTO `t_menu` VALUES ('210', '208', '运行日志', 'open', '1', 'system/statistic/job/log/list.do', null);
INSERT INTO `t_menu` VALUES ('211', '101', '数据统计', 'open', '8', 'system/statistic/query.do', null);


-- ----------------------------
-- Records of t_privilege
-- ----------------------------
INSERT INTO `t_privilege` VALUES ('1', '权限列表', 'system/privilege/list.do', '权限管理');
INSERT INTO `t_privilege` VALUES ('2', '新增权限', 'system/privilege/add.do', '权限管理');
INSERT INTO `t_privilege` VALUES ('3', '修改权限', 'system/privilege/update.do', '权限管理');
INSERT INTO `t_privilege` VALUES ('4', '删除权限', 'system/privilege/remove.do', '权限管理');
INSERT INTO `t_privilege` VALUES ('5', '角色列表', 'system/role/list.do', '角色管理');
INSERT INTO `t_privilege` VALUES ('6', '新增角色', 'system/role/add.do', '角色管理');
INSERT INTO `t_privilege` VALUES ('7', '修改角色', 'system/role/update.do', '角色管理');
INSERT INTO `t_privilege` VALUES ('8', '删除角色', 'system/role/remove.do', '角色管理');
INSERT INTO `t_privilege` VALUES ('9', '参数列表', 'system/param/list.do', '参数管理');
INSERT INTO `t_privilege` VALUES ('10', '新增参数', 'system/param/add.do', '参数管理');
INSERT INTO `t_privilege` VALUES ('11', '账号列表', 'system/user/list.do', '账号管理');
INSERT INTO `t_privilege` VALUES ('12', '菜单列表', 'system/menu/list.do', '菜单管理');
INSERT INTO `t_privilege` VALUES ('13', '新增菜单树', 'system/menu/addTree.do', '菜单管理');
INSERT INTO `t_privilege` VALUES ('14', '新增菜单', 'system/menu/addMenu.do', '菜单管理');
INSERT INTO `t_privilege` VALUES ('15', '删除菜单树', 'system/menu/removeTree.do', '菜单管理');
INSERT INTO `t_privilege` VALUES ('16', '删除菜单', 'system/menu/remove.do', '菜单管理');
INSERT INTO `t_privilege` VALUES ('17', '修改参数', 'system/param/update.do', '参数管理');
INSERT INTO `t_privilege` VALUES ('18', '删除参数', 'system/param/remove.do', '参数管理');
INSERT INTO `t_privilege` VALUES ('19', '查看日志', 'system/log/list.do', '日志管理');
INSERT INTO `t_privilege` VALUES ('20', '新增账号', 'system/user/add.do', '账号管理');
INSERT INTO `t_privilege` VALUES ('21', '修改账号', 'system/user/update.do', '账号管理');
INSERT INTO `t_privilege` VALUES ('22', '删除账号', 'system/user/remove.do', '账号管理');
INSERT INTO `t_privilege` VALUES ('23', '内存数据查看', 'system/data/list.do', '内存数据管理');
INSERT INTO `t_privilege` VALUES ('24', '任务列表', 'system/statistic/job/list.do', '任务列表');
INSERT INTO `t_privilege` VALUES ('25', '任务运行', 'system/statistic/job/run.do', '任务列表');
INSERT INTO `t_privilege` VALUES ('26', '任务日志列表', 'system/statistic/job/log/list.do', '任务日志');
INSERT INTO `t_privilege` VALUES ('27', '任务日志详情', 'system/statistic/job/log/detail.do', '任务日志');
INSERT INTO `t_privilege` VALUES ('28', '数据统计查询', 'system/statistic/query.do', '数据统计');
INSERT INTO `t_privilege` VALUES ('29', '数据导出', 'system/statistic/export.do', '数据统计');


-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '系统管理员', '拥有系统的所有权限');

-- ----------------------------
-- Records of t_role_privilege
-- ----------------------------
INSERT INTO `t_role_privilege`(role_id, privilege_id) select '1', p.id from t_privilege as p order by id;

-- ----------------------------
-- Records of t_seq
-- ----------------------------
INSERT INTO `t_seq` VALUES ('T_USER_ACTION_LOG', 'UAL', '9999999', '0', '1', '1');

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '系统管理员', null, null, '2014-04-13 17:07:16', '2014-04-13 17:07:16', 'offline');

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
