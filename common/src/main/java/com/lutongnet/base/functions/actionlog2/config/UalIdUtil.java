package com.lutongnet.base.functions.actionlog2.config;

import com.lutongnet.base.functions.actionlog2.service.UalBaseService;
import com.lutongnet.base.util.SpringUtils;

/**
 * UAL系统序列号工具类<br>
 * 
 * @author tianjp
 * @version 2.0.0
 * @since 1.0.4
 * 
 */
public class UalIdUtil {

	public static String getUalId() {
		return DelayInit.UAL_ID_HELPER.getUalId();
	}

	private static class DelayInit {
		private static final UalIdHelper	UAL_ID_HELPER;

		static {
			String id = UalConfig.getConfigId();
			int maxSeq = UalConfig.getSeqMaxNumber();
			UalBaseService ualBaseService = 
					SpringUtils.getApplicationContext().getBean("ualBaseService", UalBaseService.class);
			
			int initSeq = ualBaseService.getInitMaxSeq();
			UAL_ID_HELPER = new UalIdHelper(id, initSeq, maxSeq);
		}
	}
}
