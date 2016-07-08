package com.lutongnet.statistics.top;

import com.lutongnet.base.util.parameter.Parameterization;
import com.lutongnet.base.util.value.ValueHelper;


/**
 * 任务类
 * 
 * 任务执行流程
 * 1、任务实例化
 * 2、设置JobExecuteDataMap参数。其中参数由外部传入。
 * 3、执行verify方法
 * 4、执行cleanData方法
 * 5、执行execute方法。
 * 6、设置JobExecuteDataMap参数为空。外部参数在执行完毕后清空，不管任务成功或者失败。
 * 
 * @author tianjp
 *
 */
public interface ScheduleJob extends Parameterization {
	
	public enum Status implements com.lutongnet.base.loader.EnumDisplay{
		SUCCESS("成功"), 
		FAILURE("失败"), 
		VERIFY_FAILURE("验证失败"), 
		CLEAN_DATA_FAILURE("清除数据失败"), 
		ERROR("异常");
		
		
		private String	desc;
		private Status(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return this.desc;
		}
	}
	
	/**
	 *  在任务执行之前，校验运行条件，不符合返回false，job方法就不会被执行
	 * @return false 任务不会继续执行
	 */
	public boolean verify();

	/**
	 * 执行具体的任务
	 * 
	 * @param dataMap
	 *            执行参数
	 * @return true任务执行成功
	 */
	public boolean execute();

	/**
	 * 清除数据，才能重新执行任务
	 * 
	 * @param dataMap
	 *            行参数
	 * @return true数据清除成功
	 */
	public boolean cleanData();
	
	/**
	 * 获取任务描述
	 * @return
	 */
	public String getDesc();
	
	/**
	 * 设置参数
	 * @param valueHelper
	 */
	public abstract void setValueHelper(ValueHelper valueHelper);
}
