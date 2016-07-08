
package com.lutongnet.base.functions.actionlog2.dao;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lutongnet.base.dao.EntityDaoSupport;
import com.lutongnet.base.functions.actionlog2.model.entity.UalBase;


@Component("ualBaseDAO")
public class  UalBaseDAO extends EntityDaoSupport<UalBase>{
	
	private static Logger log	= LoggerFactory.getLogger(UalBaseDAO.class);
	
	private static final String	SQL_QUERY_MAX_ID	= "select id  from T_UAL_BASE where id like ? order by id desc limit 0, 1 ";
	public String getMaxId(String id) {
		Query query = getEntityManager().createNativeQuery(SQL_QUERY_MAX_ID)
		.setParameter(1, id + "%");
		
		try{
			return query.getSingleResult().toString();
		}catch(Exception e){
			return null;
		}
	}
}


