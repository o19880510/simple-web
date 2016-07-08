package com.lutongnet.system.schedule;

/**
 * 任务接口
 * @author tianjp
 *
 */
@Deprecated
public interface Job {
	
	/**
	 * 具体任务执行前，检查执行条件
	 * @return
	 */
	public boolean checkStatus();
	
	/**
	 * 执行具体的任务
	 * @return
	 */
	public boolean run();
	
	/**
	 * 
	 * 任务执行完之后，需要做的动作
	 * @return
	 */
	public boolean runOver();
	
}
