package woo.study.web.business.summary.job;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import woo.study.web.business.statistics.top.impl.CommonScheduleJob;
import woo.study.web.business.summary.model.entity.DataSummaryIndicator.IndicatorType;
import woo.study.web.business.summary.service.SummaryService;
import woo.study.web.common.util.parameter.Parameter;
import woo.study.web.common.util.parameter.ParameterFactory;
import woo.study.web.common.util.parameter.Parameterization;

public class TotalUserOrderDailyJob extends CommonScheduleJob implements Parameterization {

	private static Logger				log						= LoggerFactory.getLogger(TotalUserOrderDailyJob.class);

	private final Parameter				DATE					= ParameterFactory.DATE_START;

	private static final String			NAME_ENG_TOTAL_USER		= "totalUsers";
	private static final String			NAME_CHI_TOTAL_USER		= "用户数";

	private static final String			NAME_ENG_TOTAL_ORDER	= "totalOrderUsers";
	private static final String			NAME_CHI_TOTAL_ORDER	= "订购数";

	private static final String			NAME_ENG_TOTAL_DEORDER	= "totalDeorderUsers";
	private static final String			NAME_CHI_TOTAL_DEORDER	= "退订数";

	private static final IndicatorType	TYPE_DAILY				= IndicatorType.D;

	@Autowired
	private SummaryService				summaryService;

	public TotalUserOrderDailyJob() {
		super();
		super.parameters.add(DATE);
		super.desc = "每天订购数统计任务";
	}

	@Override
	public boolean verify() {

		summaryService.checkIndicator(NAME_ENG_TOTAL_USER, NAME_CHI_TOTAL_USER, TYPE_DAILY);
		summaryService.checkIndicator(NAME_ENG_TOTAL_ORDER, NAME_CHI_TOTAL_ORDER, TYPE_DAILY);
		summaryService.checkIndicator(NAME_ENG_TOTAL_DEORDER, NAME_CHI_TOTAL_DEORDER, TYPE_DAILY);

		return true;
	}

	@Override
	public boolean cleanData() {

		return true;
	}

	@Override
	public boolean execute() {
		LocalDate dateTime = getDateTime();
		log.debug("TotalUserOrderDailyJob execute dateTime=" + dateTime);

		Integer orderCount = 1000;
		Integer deorderCount = 2000;
		Integer orderUserTotalCount = 3000;

		summaryService.saveData(TYPE_DAILY, NAME_ENG_TOTAL_USER, orderUserTotalCount.toString(), dateTime);
		summaryService.saveData(TYPE_DAILY, NAME_ENG_TOTAL_ORDER, orderCount.toString(), dateTime);
		summaryService.saveData(TYPE_DAILY, NAME_ENG_TOTAL_DEORDER, deorderCount.toString(), dateTime);

		log.debug("TotalUserOrderDailyJob execute orderUserTotalCount=" + orderUserTotalCount);
		log.debug("TotalUserOrderDailyJob execute orderCount=" + orderCount);
		log.debug("TotalUserOrderDailyJob execute deorderCount=" + deorderCount);
		return true;
	}

	private LocalDate getDateTime() {
		LocalDate date = getValueHelper().getLocalDate(DATE.getEngName());
		if (date == null) {
			date = DateTime.now().minusDays(1).toLocalDate(); // 获取昨天的日期
		}
		return date;
	}

}
