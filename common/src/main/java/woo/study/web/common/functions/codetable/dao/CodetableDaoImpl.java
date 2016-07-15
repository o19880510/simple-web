package woo.study.web.common.functions.codetable.dao;

import java.util.LinkedHashMap;

import java.util.List;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woo.study.web.common.functions.codetable.model.vo.CodeTableConfigValue;
import woo.study.web.common.util.AssertHelper;

@Component("codetableDao")
public class CodetableDaoImpl implements CodetableDAO {

	private static Logger	log	= LoggerFactory.getLogger(CodetableDaoImpl.class);
	/**
	 * 注入JPA实体管理者，注意要显示设置unitName，方便以后多数据源操作
	 */
	@PersistenceContext(unitName = "default")
	protected EntityManager	entityManager;

	/* (non-Javadoc)
	 * @see woo.study.web.common.common.business.base.functions.codetable.dao.CodetableDAO#get(woo.study.web.common.common.business.base.functions.codetable.model.vo.CodeTableConfigValue)
	 */
	@Override
	public Map<String, String> get(CodeTableConfigValue configValue) {

		String inputSql = getSql(configValue);

		Query query = entityManager.createNativeQuery(inputSql);

		return getDatas(query);
	}
	
	private String getSql(CodeTableConfigValue configValue) {
		String inputSql = configValue.getSql();
		if (AssertHelper.isEmpty(inputSql)) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT ").append(configValue.getValue()).append(",").append(configValue.getDesc())
					.append(" ");
			sql.append(" FROM ").append(configValue.getTableName());
			sql.append(" ORDER BY ").append(configValue.getOrder()).append(" ").append(configValue.getOrderBy());

			log.debug("codetable gen sql:" + sql.toString());

			inputSql = sql.toString();
		}

		return inputSql;
	}

	private Map<String, String> getDatas(Query query) {

		List objecArraytList = query.getResultList();

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 0; i < objecArraytList.size(); i++) {
			Object[] obj = (Object[]) objecArraytList.get(i);
			if (obj.length == 0) {
				map.put(null, null);
			} else if (obj.length == 1) {
				String key = (obj[0] == null ? null : obj[0].toString());
				map.put(key, null);
			} else {
				String key = (obj[0] == null ? null : obj[0].toString());
				String value = (obj[1] == null ? null : obj[1].toString());
				map.put(key, value);
			}
		}
		return map;
	}
}
