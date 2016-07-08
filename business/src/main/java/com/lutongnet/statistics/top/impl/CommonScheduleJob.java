package com.lutongnet.statistics.top.impl;

import java.util.ArrayList;
import java.util.List;

import com.lutongnet.base.util.parameter.Parameter;
import com.lutongnet.base.util.value.ValueHelper;
import com.lutongnet.statistics.top.ScheduleJob;

public abstract class CommonScheduleJob implements ScheduleJob {

	protected List<Parameter>	parameters;
	protected ValueHelper	valueHelper;
	protected String	desc;
	
	
	public CommonScheduleJob() {
		parameters = new ArrayList<Parameter>();
	}

	@Override
	public void setValueHelper(ValueHelper valueHelper) {
		this.valueHelper = valueHelper;
	}

	@Override
	public List<Parameter> getParameters() {
		return parameters;
	}

	protected ValueHelper getValueHelper() {
		return valueHelper;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}
	
	
}
