package woo.study.web.business.statistics.top.impl;

import java.util.ArrayList;
import java.util.List;

import woo.study.web.business.statistics.top.ScheduleJob;
import woo.study.web.common.util.parameter.Parameter;
import woo.study.web.common.util.value.ValueHelper;

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
