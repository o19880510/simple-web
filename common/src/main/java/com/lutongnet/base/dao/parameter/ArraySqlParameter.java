package com.lutongnet.base.dao.parameter;

import javax.persistence.Query;

public class ArraySqlParameter implements SqlParameter{
	
	private Object[] params;
	public ArraySqlParameter(Object... params){
		this.params = params;
	}
	
	public void setParamter(Query query) {
		
		for(int i = 0; i < params.length; i++){
			query.setParameter(i+1, params[i]);
		}
	}

}
