package com.lutongnet.base.util.parameter;

import java.util.List;

public interface Parameterization {

	/**
	 * 获取任务执行时需要的参数信息
	 * @return
	 */
	public abstract List<Parameter> getParameters();

}