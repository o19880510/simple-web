package woo.study.web.common.dao.parameter;

import java.util.List;

import javax.persistence.Query;

public class ListSqlParameter implements SqlParameter{
	
	private List<Object> params;
	public ListSqlParameter(List<Object> params){
		this.params = params;
	}
	
	public void setParamter(Query query) {
		
		for(int i = 0; i < params.size(); i++){
			query.setParameter(i+1, params.get(i));
		}
	}

}
