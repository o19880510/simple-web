package com.lutongnet.base.functions.actionlog2.config;

import java.util.concurrent.atomic.AtomicLong;
import org.joda.time.DateTime;

/**
 * UAL系统序列号生成类<br>
 * 
 * 序号规则：UAL+01(日志ID-配置文件读取)+yyMMdd(150328)+0000001(x位序号)<br>
 * 
 * @author tianjp
 * @version 2.0.0
 * @since 1.0.4
 * 
 */
public class UalIdHelper {
	private AtomicLong	UAL_ID	= new AtomicLong();

	private String		id;
	private int			initSeq;
	private int			maxSeq;
	private int			maxSeqLength;

	public UalIdHelper(String id, int initSeq, int maxSeq) {
		super();
		this.id = id;
		this.maxSeq = maxSeq;

		this.initSeq = initSeq;

		this.maxSeqLength = String.valueOf(this.maxSeq).length();

		UAL_ID.set(this.initSeq);
	}

	public String getUalId() {
		return "UAL" + id + DateTime.now().toString("yyMMdd") + getId();
	}

	private String getId() {
		UAL_ID.compareAndSet(maxSeq, 0L);
		long seq = UAL_ID.incrementAndGet();
		return String.format("%0" + this.maxSeqLength + "d", seq);
	}
}
