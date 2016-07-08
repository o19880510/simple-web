package com.lutongnet.statistics.top;

import java.util.List;

/**
 * 任务调用类
 * 
 * 可以调用一个或多个任务
 * 
 * @author tianjp
 *
 */
public interface ScheduleJobExecutor {

	public boolean invoke(ScheduleJob job);

	public void invoke(ScheduleJob... jobs);

	public void invoke(List<ScheduleJob> jobs);
}
