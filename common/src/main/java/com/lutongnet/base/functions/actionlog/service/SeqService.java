package com.lutongnet.base.functions.actionlog.service;


import javax.annotation.Resource;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lutongnet.base.functions.actionlog.dao.SeqDao;
import com.lutongnet.base.functions.actionlog.model.entity.Seq;
import com.lutongnet.base.util.DateUtils;
import com.lutongnet.base.util.StringUtils;

@Transactional
@Service("seqService")
public class SeqService {
	
	private static Logger log	= LoggerFactory.getLogger(SeqService.class);
	
	@Resource(name ="seqDao")
	private SeqDao seqDao;
	
	public String getSeqNo(String tableName){
		return getValueWithPrefix(tableName);
	}
	public String getSeqNoWithTime(String tableName){
		return getValueWithPrefixTime(tableName);
	}
	public String getOriginalSeqNo(String tableName){
		Seq seqValue = getSeqValue(tableName);
		return getOriginalValue(seqValue);
	}
	
	private String getValueWithPrefix(String tableName){
		Seq seqValue = getSeqValue(tableName);
		
		StringBuffer sb = new StringBuffer();
		sb.append(seqValue.getPrefix() == null ? "":seqValue.getPrefix());
		sb.append(getOriginalValue(seqValue));
		
		return sb.toString();
	}
	
	protected final String getValueWithPrefixTime(String tableName){
		Seq seqValue = getSeqValue(tableName);
		
		StringBuffer sb = new StringBuffer();
		sb.append(seqValue.getPrefix() == null ? "":seqValue.getPrefix());
		sb.append(DateUtils.getCurrentDateTime("yyyyMMdd"));
		sb.append(getOriginalValue(seqValue));
		
		return sb.toString();
	}
	
	protected final String getValueWithPrefixTime(String tableName, String timeFormat){
		Seq seqValue = getSeqValue(tableName);
		
		StringBuffer sb = new StringBuffer();
		sb.append(seqValue.getPrefix() == null ? "":seqValue.getPrefix());
		sb.append(DateUtils.getCurrentDateTime(timeFormat));
		sb.append(getOriginalValue(seqValue));
		
		return sb.toString();
	}
	
	protected final String getValueWithTime(String tableName){
		Seq seqValue = getSeqValue(tableName);
		
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtils.getCurrentDateTime("yyyyMMdd"));
		sb.append(getOriginalValue(seqValue));
		
		return sb.toString();
	}
	
	protected final String getValueWithTime(String tableName, String timeFormat){
		Seq seqValue = getSeqValue(tableName);
		
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtils.getCurrentDateTime(timeFormat));
		sb.append(getOriginalValue(seqValue));
		
		return sb.toString();
	}
	
	protected final String getOriginalValue(Seq seqValue){
		String nextValue = seqValue.getNextValue().toString();
		return StringUtils.fillZeroLeft(nextValue, seqValue.getMaxValue().toString().length());
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected final Seq getSeqValue(String tableName){
		Seq seqValue = seqDao.get(tableName);
		Seq tempSeq = seqValue.clone();
		updateNextValue(seqValue);
		return tempSeq;
	}
	
	private void updateNextValue(Seq seqValue){
		Integer nextValue = seqValue.getNextValue();
		Integer nextNewValue = nextValue + seqValue.getIncrease();
		if(nextNewValue.compareTo(seqValue.getMaxValue()) > 0 ){
			seqValue.setNextValue(1);
		}else{
			seqValue.setNextValue(nextNewValue);
		}
		seqDao.updateSeqNextValue(seqValue);

	}
	
}
