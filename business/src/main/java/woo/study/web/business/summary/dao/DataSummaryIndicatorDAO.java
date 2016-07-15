
package woo.study.web.business.summary.dao;

import java.util.List;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woo.study.web.business.summary.model.entity.DataSummaryIndicator;
import woo.study.web.common.dao.EntityDaoSupport;


@Component("dataSummaryIndicatorDAO")
public class  DataSummaryIndicatorDAO extends EntityDaoSupport<DataSummaryIndicator>{
	
	private static Logger log	= LoggerFactory.getLogger(DataSummaryIndicatorDAO.class);
	
	private static final String HQL_QUERY_RECORD = "from DataSummaryIndicator where nameEng = ? ";
	public DataSummaryIndicator getByName(String nameEng){
		return queryFirst(HQL_QUERY_RECORD, nameEng);
	}
	
	private static final String HQL_QUERY_RECORDS = "from DataSummaryIndicator where indicatorType = ? order by id ";
	public List<DataSummaryIndicator> getsByType(DataSummaryIndicator.IndicatorType indicatorType){
		return queryList(HQL_QUERY_RECORDS, indicatorType);
	}
	
}


