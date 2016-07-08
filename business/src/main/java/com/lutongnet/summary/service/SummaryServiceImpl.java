package com.lutongnet.summary.service;

import javax.annotation.Resource;












import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.summary.dao.DataSummaryDAO;
import com.lutongnet.summary.dao.DataSummaryDateDAO;
import com.lutongnet.summary.dao.DataSummaryIndicatorDAO;
import com.lutongnet.summary.model.entity.DataSummary;
import com.lutongnet.summary.model.entity.DataSummaryDate;
import com.lutongnet.summary.model.entity.DataSummaryIndicator;
import com.lutongnet.summary.model.entity.DataSummaryIndicator.IndicatorType;


@Service("summaryService")
@Transactional
public class SummaryServiceImpl implements  SummaryService{

	private static Logger			log	= LoggerFactory.getLogger(SummaryServiceImpl.class);

	@Resource(name = "dataSummaryDAO")
	private DataSummaryDAO			dataSummaryDAO;

	@Resource(name = "dataSummaryIndicatorDAO")
	private DataSummaryIndicatorDAO	dataSummaryIndicatorDAO;

	@Resource(name = "dataSummaryDateDAO")
	private DataSummaryDateDAO		dataSummaryDateDAO;

	@Override
	public void saveData(IndicatorType indicatorType, String nameEng, String dataValue, LocalDate effectiveDate) {
		removeData(nameEng, effectiveDate);
		
		DataSummary dataSummary = new DataSummary();

		dataSummary.setNameEng(nameEng);
		dataSummary.setDataValue(dataValue);
		dataSummary.setEffectiveDate(effectiveDate);
		dataSummary.setIndicatorType(indicatorType);

		dataSummaryDAO.save(dataSummary);
		
		markDate(indicatorType, effectiveDate);
	}

	@Override
	public void removeData(String nameEng, LocalDate effectiveDate) {
		dataSummaryDAO.remove(nameEng, effectiveDate);
	}
	
	@Override
	public void checkIndicator(String nameEng, String nameChi, IndicatorType indicatorType) {
		DataSummaryIndicator dataSummaryIndicator = dataSummaryIndicatorDAO.getByName(nameEng);
		if (dataSummaryIndicator == null) {
			saveIndicator(nameEng, nameChi, indicatorType);
		}
	}
	
	@Override
	public void saveIndicator(String nameEng, String nameChi, IndicatorType indicatorType) {
		DataSummaryIndicator dataSummaryIndicator = new DataSummaryIndicator();

		dataSummaryIndicator.setNameEng(nameEng);
		dataSummaryIndicator.setNameChi(nameChi);
		dataSummaryIndicator.setIndicatorType(indicatorType);

		dataSummaryIndicatorDAO.save(dataSummaryIndicator);
	}


	private void markDate(IndicatorType indicatorType, LocalDate runDate) {

		DataSummaryDate dataSummaryDate = dataSummaryDateDAO.get(indicatorType, runDate);

		if (dataSummaryDate == null) {
			DataSummaryDate newDataSummaryDate = new DataSummaryDate();
			newDataSummaryDate.setIndicatorType(indicatorType);
			newDataSummaryDate.setDataDate(runDate);
			dataSummaryDateDAO.save(newDataSummaryDate);
		}
	}

//	private void removeDate(IndicatorType indicatorType, LocalDate runDate) {
//
//		dataSummaryDateDAO.delete(indicatorType, runDate);
//	}
}
