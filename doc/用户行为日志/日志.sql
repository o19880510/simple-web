SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS T_UAL_BASE;
DROP TABLE IF EXISTS T_UAL_BUSINESS;
DROP TABLE IF EXISTS T_UAL_STAY_TIME;




/* Create Tables */

CREATE TABLE T_UAL_BASE
(
	-- UAL(3)+01(2)+150327(6)+1234567(7)
	ID varchar(18) NOT NULL COMMENT 'UAL(3)+01(2)+150327(6)+1234567(7)',
	USER_ID varchar(30),
	-- XX01S01
	ACTION_CODE varchar(10) NOT NULL COMMENT 'XX01S01',
	-- 描述功能请求
	OPERATION_DESC varchar(50) NOT NULL COMMENT '描述功能请求',
	S_COL_1 varchar(50),
	S_COL_2 varchar(50),
	S_COL_3 varchar(50),
	S_COL_4 varchar(50),
	I_COL_1 int(11),
	I_COL_2 int(11),
	I_COL_3 int(11),
	D_COL_1 datetime,
	D_COL_2 datetime,
	D_COL_3 datetime,
	INPUT_PARAMS varchar(600),
	PARAM_COMMENT varchar(300),
	TXN_DATE datetime,
	PRIMARY KEY (ID),
	UNIQUE (ID)
);


CREATE TABLE T_UAL_BUSINESS
(
	ID varchar(18) NOT NULL,
	USER_ID varchar(30),
	ACTION_CODE varchar(10) NOT NULL,
	BUSINESS_STATUS_CODE varchar(10),
	EXCEPTION_INFO varchar(6000),
	TXN_DATE datetime NOT NULL,
	PRIMARY KEY (ID)
);


CREATE TABLE T_UAL_STAY_TIME
(
	ID varchar(18) NOT NULL,
	USER_ID varchar(30),
	ACTION_CODE varchar(10),
	-- 单位：s秒
	STAY_TIME int(11) NOT NULL COMMENT '单位：s秒',
	TXN_DATE datetime NOT NULL,
	PRIMARY KEY (ID)
);



