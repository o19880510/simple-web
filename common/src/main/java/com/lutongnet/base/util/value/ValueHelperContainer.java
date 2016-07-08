package com.lutongnet.base.util.value;

import com.lutongnet.base.util.value.CommonHelper;

public class ValueHelperContainer extends CommonHelper{
	private ValueGettable valueGettable ;
	
	public ValueHelperContainer(ValueGettable valueGettable) {
		super();
		this.valueGettable = valueGettable;
	}

	public Object get(String key) {
		return this.valueGettable.get(key);
	}

}
