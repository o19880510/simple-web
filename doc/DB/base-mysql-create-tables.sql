/*
Navicat MySQL Data Transfer

Source Server         : 本机数据库
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2014-04-13 17:50:17
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for t_ual_base
-- ----------------------------

CREATE TABLE `t_ual_base` (
  `ID` varchar(18) NOT NULL COMMENT 'UAL(3)+01(2)+150327(6)+1234567(7)',
  `USER_ID` varchar(30) DEFAULT NULL,
  `ACTION_CODE` varchar(10) NOT NULL COMMENT 'XX01S01',
  `OPERATION_DESC` varchar(50) NOT NULL COMMENT '描述功能请求',
  `S_COL_1` varchar(50) DEFAULT NULL,
  `S_COL_2` varchar(50) DEFAULT NULL,
  `S_COL_3` varchar(50) DEFAULT NULL,
  `S_COL_4` varchar(50) DEFAULT NULL,
  `I_COL_1` int(11) DEFAULT NULL,
  `I_COL_2` int(11) DEFAULT NULL,
  `I_COL_3` int(11) DEFAULT NULL,
  `D_COL_1` datetime DEFAULT NULL,
  `D_COL_2` datetime DEFAULT NULL,
  `D_COL_3` datetime DEFAULT NULL,
  `INPUT_PARAMS` varchar(600) DEFAULT NULL,
  `PARAM_COMMENT` varchar(300) DEFAULT NULL,
  `TXN_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ual_business
-- ----------------------------

CREATE TABLE `t_ual_business` (
  `ID` varchar(18) NOT NULL,
  `USER_ID` varchar(30) DEFAULT NULL,
  `ACTION_CODE` varchar(10) NOT NULL,
  `BUSINESS_STATUS_CODE` varchar(10) DEFAULT NULL,
  `EXCEPTION_INFO` varchar(6000) DEFAULT NULL,
  `TXN_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ual_stay_time
-- ----------------------------
CREATE TABLE `t_ual_stay_time` (
  `ID` varchar(18) NOT NULL,
  `USER_ID` varchar(30) DEFAULT NULL,
  `ACTION_CODE` varchar(10) DEFAULT NULL,
  `STAY_TIME` int(11) NOT NULL COMMENT '单位：s秒',
  `TXN_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_action_log
-- ----------------------------
CREATE TABLE `t_action_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `content` varchar(128) NOT NULL,
  `add_date` datetime NOT NULL,
  `detail_id` int(11) DEFAULT NULL,
  `actor` varchar(20) NOT NULL,
  `uri` varchar(256) DEFAULT NULL,
  `param` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_action_log_detail
-- ----------------------------
CREATE TABLE `t_action_log_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `style` varchar(10) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `uri` varchar(256) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_param
-- ----------------------------
CREATE TABLE `t_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_param_item
-- ----------------------------
CREATE TABLE `t_param_item` (
  `param_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` varchar(200) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_privilege
-- ----------------------------
CREATE TABLE `t_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `uri` varchar(256) DEFAULT NULL,
  `group_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_role_privilege
-- ----------------------------
CREATE TABLE `t_role_privilege` (
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_seq
-- ----------------------------
CREATE TABLE `t_seq` (
  `TABLE_NAME` varchar(50) NOT NULL DEFAULT '',
  `PREFIX` varchar(20) DEFAULT NULL,
  `MAX_VALUE` bigint(15) DEFAULT NULL,
  `INIT_VALUE` bigint(15) DEFAULT NULL,
  `NEXT_VALUE` bigint(15) DEFAULT NULL,
  `INCREASE` int(2) DEFAULT NULL,
  PRIMARY KEY (`TABLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_paramter
-- ----------------------------
CREATE TABLE `t_system_paramter` (
  `key` varchar(120) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_userid` (`userid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_user_action_log
-- ----------------------------
CREATE TABLE `t_user_action_log` (
  `id` varchar(18) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `action_code` varchar(7) DEFAULT NULL,
  `operation_desc` varchar(50) DEFAULT NULL,
  `s_col_1` varchar(50) DEFAULT NULL,
  `s_col_2` varchar(50) DEFAULT NULL,
  `s_col_3` varchar(50) DEFAULT NULL,
  `s_col_4` varchar(50) DEFAULT NULL,
  `i_col_1` int(11) DEFAULT NULL,
  `i_col_2` int(11) DEFAULT NULL,
  `i_col_3` int(11) DEFAULT NULL,
  `d_col_1` datetime DEFAULT NULL,
  `d_col_2` datetime DEFAULT NULL,
  `d_col_3` datetime DEFAULT NULL,
  `input_params` varchar(600) DEFAULT NULL,
  `http_resp_code` varchar(3) DEFAULT NULL,
  `http_error_msg` varchar(600) DEFAULT NULL,
  `third_resp_data` varchar(600) DEFAULT NULL,
  `resp_data` varchar(1000) DEFAULT NULL,
  `business_result_code` int(3) DEFAULT NULL,
  `exception_info` varchar(6000) DEFAULT NULL,
  `staying_time` int(11) DEFAULT NULL COMMENT '页面停留时间 秒',
  `using_time` int(11) DEFAULT NULL COMMENT '花费时间 毫秒数',
  `comment` varchar(300) DEFAULT NULL COMMENT '模糊字段所存储信息详情 开发人员使用',
  `txn_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


CREATE TABLE `t_schedule_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `schedule_name` varchar(50) DEFAULT NULL,
  `msg` varchar(500) DEFAULT NULL,
  `schedule_status` int(2) DEFAULT NULL,
  `run_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `t_schedule_log_new` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `JOB_CLASS` varchar(200) NOT NULL COMMENT '任务类名 : JOB class全名',
  `STATUS` varchar(20) NOT NULL COMMENT '运行状态 : JOB状态: VERIFY_FAILURE, SUCCESS, FAILURE, ERROR,CLEAN_DATA_FAILURE',
  `ERROR_MSG` varchar(400) DEFAULT NULL COMMENT '异常信息 : JOB 所属任务组',
  `RUN_DATE` datetime NOT NULL COMMENT '运行时间 : 运行时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务运行日志';



CREATE TABLE `t_data_summary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `NAME_ENG` varchar(30) NOT NULL COMMENT '数据名称',
  `DATA_VALUE` varchar(30) NOT NULL COMMENT '数据值',
  `INDICATOR_TYPE` varchar(1) DEFAULT NULL,
  `EFFECTIVE_DATE` date NOT NULL COMMENT '数据有效时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据汇总';

-- ----------------------------
-- Table structure for t_data_summary_date
-- ----------------------------
CREATE TABLE `t_data_summary_date` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `INDICATOR_TYPE` varchar(1) NOT NULL COMMENT '指标类型',
  `DATA_DATE` date NOT NULL COMMENT '数据时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据汇总日期';

-- ----------------------------
-- Table structure for t_data_summary_indicator
-- ----------------------------
CREATE TABLE `t_data_summary_indicator` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `NAME_ENG` varchar(30) NOT NULL COMMENT '指标名',
  `NAME_CHI` varchar(50) NOT NULL COMMENT '指标名',
  `INDICATOR_TYPE` varchar(1) NOT NULL COMMENT '指标类型 : D/W/M/Y-日/周/月/年',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_ENG` (`NAME_ENG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据统计指标';