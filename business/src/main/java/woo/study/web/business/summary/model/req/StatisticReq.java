package woo.study.web.business.summary.model.req;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import woo.study.web.business.summary.model.entity.DataSummaryIndicator.IndicatorType;
import woo.study.web.system.model.request.CommonReq;

public class StatisticReq extends CommonReq{
	
	private IndicatorType indicatorType;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;

	public StatisticReq() {
		super();
		indicatorType = IndicatorType.D;
	}
	
	public StatisticReq(IndicatorType indicatorType) {
		super();
		this.indicatorType = indicatorType;
	}

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "StatisticReq [indicatorType=" + indicatorType + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
	
	
}
