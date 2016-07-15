package woo.study.web.common.dao.parameter;


import javax.persistence.Query;

public class EmptySqlParameter implements SqlParameter {

	private static final SqlParameter	SQL_PARAMETER	= new EmptySqlParameter();

	private EmptySqlParameter() {
	}

	public static SqlParameter getInstance() {
		return SQL_PARAMETER;
	}

	public void setParamter(Query query) {

	}
}
