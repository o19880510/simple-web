
package com.lutongnet.base.functions.actionlog2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.functions.actionlog2.model.entity.UalBusiness;


@Component("ualBusinessDAO")
public class  UalBusinessDAO extends EntityDaoSupport<UalBusiness>{
	
	private static Logger log	= LoggerFactory.getLogger(UalBusinessDAO.class);
}


