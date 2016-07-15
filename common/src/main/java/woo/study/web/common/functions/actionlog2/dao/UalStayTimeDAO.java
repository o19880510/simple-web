
package woo.study.web.common.functions.actionlog2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.functions.actionlog2.model.entity.UalStayTime;


@Component("ualStayTimeDAO")
public class  UalStayTimeDAO extends EntityDaoSupport<UalStayTime>{
	
	private static Logger log	= LoggerFactory.getLogger(UalStayTimeDAO.class);
}


