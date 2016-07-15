package woo.study.web.common.functions.actionlog2.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import woo.study.web.common.functions.actionlog2.config.PrivateUalBusinessHelper;
import woo.study.web.common.functions.actionlog2.config.UalConfig;
import woo.study.web.common.functions.actionlog2.dao.UalBaseDAO;
import woo.study.web.common.functions.actionlog2.model.entity.UalBase;
import woo.study.web.common.functions.actionlog2.model.vo.LogConfigParamValue;
import woo.study.web.common.functions.actionlog2.model.vo.LogConfigValue;
import woo.study.web.common.functions.actionlog2.parameter.ParameterFactory;
import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.DateUtils;
import woo.study.web.common.util.NumberUtils;
import woo.study.web.common.util.value.ValueHelper;

@Service("ualBaseService")
@Transactional
public class UalBaseService {

	private static Logger		log					= LoggerFactory.getLogger(UalBaseService.class);

	public static final String	RECORD_NOT_ALLOW	= "N";

	private static String		S_COL_1				= "s_col_1";
	private static String		S_COL_2				= "s_col_2";
	private static String		S_COL_3				= "s_col_3";
	private static String		S_COL_4				= "s_col_4";

	private static String		I_COL_1				= "i_col_1";
	private static String		I_COL_2				= "i_col_2";
	private static String		I_COL_3				= "i_col_3";

	private static String		D_COL_1				= "d_col_1";
	private static String		D_COL_2				= "d_col_2";
	private static String		D_COL_3				= "d_col_3";

	public static String		USER_ID				= "user_id";

	@Resource(name = "ualBaseDAO")
	private UalBaseDAO			ualBaseDAO;
	
	public int getInitMaxSeq(){
		String id = UalConfig.getConfigId();
		int maxSeq = UalConfig.getSeqMaxNumber();
		int maxLength = String.valueOf(maxSeq).length();
		
		String ualId = ualBaseDAO.getMaxId("UAL"+id);
		log.debug("getMaxSeq ualId=" + ualId);
		if(AssertHelper.isEmpty(ualId)){
			return 0;
		}
		
		String initSeq = ualId.substring(ualId.length() - maxLength );
		log.debug("getMaxSeq initSeq=" + initSeq);
		return NumberUtils.parseInt(initSeq, 0);
	}
	
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(final String ualLogId, final String userId, final LogConfigValue logConfigValue, final ValueHelper valueHelper) {

		log.debug("UalBaseService save ualLogId=" + ualLogId);
		log.debug("UalBaseService save logConfigValue=" + logConfigValue);
		
		log.debug("UalBaseService save valueHelper flag01=" + valueHelper.getString("flag01"));
		
		UalBase ualBase = new UalBase();

		ualBase.setId(ualLogId);
		ualBase.setUserId(userId);
		ualBase.setActionCode(logConfigValue.getActionCode());
		ualBase.setOperationDesc(logConfigValue.getDesc());
		ualBase.setTxnDate(DateTime.now());
		
		log.debug("UalBaseService save analysisParams-ualBase=" + ualBase);
		
		log.debug("UalBaseService save before analysisParams valueHelper flag01=" + valueHelper.getString("flag01"));
		analysisParams(valueHelper, logConfigValue, ualBase);
		log.debug("UalBaseService save after analysisParams valueHelper flag01=" + valueHelper.getString("flag01"));

		ualBaseDAO.save(ualBase);
		log.debug("UalBaseService save ualBase=" + ualBase);
	}

	private void analysisParams(final ValueHelper valueHelper, LogConfigValue logConfigValue, UalBase ualBase) {
		List<LogConfigParamValue> paramsList = logConfigValue.getParamNames();

		StringBuilder inputParams = new StringBuilder();
		StringBuilder paramComment = new StringBuilder();
		for (LogConfigParamValue configParamValue : paramsList) {

			String paramName = configParamValue.getName();
			String column = configParamValue.getColumn();
			Integer maxLength = configParamValue.getMaxLength();
			String dateType = configParamValue.getType();

			String paramValue = valueHelper.getString(paramName);
			log.debug("UalBaseService analysisParams paramName=" + paramName +";  paramValue=" + paramValue);
			
			setColumn(column, paramValue, maxLength, dateType, ualBase);

			inputParams.append(paramName).append("=").append(paramValue).append(";");

			if (AssertHelper.notEmpty(column)) {
				paramComment.append(column).append("=").append(paramName).append(";");
			}
		}

		ualBase.setInputParams(inputParams.toString());
		ualBase.setParamComment(paramComment.toString());
	}

	private void setColumn(String column, Object paramValue, Integer maxLength, String dateType, UalBase ualBase) {
		log.debug("setColumn column:" + column);

		if (column == null || paramValue == null || AssertHelper.isEmpty(column)) {
			return;
		}

		boolean isString = column.startsWith("s");
		boolean isInteger = column.startsWith("i");
		boolean isDate = column.startsWith("d");

		if (isString) {
			String value = String.valueOf(paramValue);
			if (maxLength > 0 && value.length() > maxLength) {
				value = value.substring(0, maxLength - 1);
			}

			if (S_COL_1.equals(column)) {
				ualBase.setScol1(value);
			} else if (S_COL_2.equals(column)) {
				ualBase.setScol2(value);
			} else if (S_COL_3.equals(column)) {
				ualBase.setScol3(value);
			} else if (S_COL_4.equals(column)) {
				ualBase.setScol4(value);
			} else {
				throw new RuntimeException("Unknow column:" + column + "; please check user-action-log.xml!");
			}

		} else if (isInteger) {
			Integer value = NumberUtils.parseInt(String.valueOf(paramValue));

			if (I_COL_1.equals(column)) {
				ualBase.setIcol1(value);
			} else if (I_COL_2.equals(column)) {
				ualBase.setIcol2(value);
			} else if (I_COL_3.equals(column)) {
				ualBase.setIcol3(value);
			} else {
				throw new RuntimeException("Unknow column:" + column + "; please check user-action-log.xml!");
			}

		} else if (isDate) {
			DateTimeFormatter formatter = DateUtils.FORMATTER_MAP.get(dateType);
			if (formatter == null) {
				try {
					formatter = DateTimeFormat.forPattern(dateType);
				} catch (Exception e) {
					formatter = DateUtils.FORMATER_DAFAULT;
					log.warn("date type error: " + dateType, e);
				}
			}
			if (AssertHelper.isEmpty(paramValue.toString())) {
				return;
			}

			DateTime dateTime = DateTime.parse(paramValue.toString(), formatter);

			if (D_COL_1.equals(column)) {
				ualBase.setDcol1(dateTime);
			} else if (D_COL_2.equals(column)) {
				ualBase.setDcol2(dateTime);
			} else if (D_COL_3.equals(column)) {
				ualBase.setDcol3(dateTime);
			} else {
				throw new RuntimeException("Unknow column:" + column + "; please check user-action-log.xml!");
			}

		} else {
			throw new RuntimeException("Unknow column:" + column + "; please check user-action-log.xml!");
		}
	}

}
