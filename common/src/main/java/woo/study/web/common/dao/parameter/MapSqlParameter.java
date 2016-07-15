package woo.study.web.common.dao.parameter;

import java.util.Map;

import javax.persistence.Query;

public class MapSqlParameter implements SqlParameter{
	
	private Map<String, Object> params;
	public MapSqlParameter(Map<String, Object> params){
		this.params = params;
	}
	
	public void setParamter(Query query) {
		
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

}
