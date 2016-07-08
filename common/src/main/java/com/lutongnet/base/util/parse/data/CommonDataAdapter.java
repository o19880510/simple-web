package com.lutongnet.base.util.parse.data;

public abstract class CommonDataAdapter implements DataAdapter {

	protected Object	inData;
	protected Object	outData;
	
	
	public CommonDataAdapter() {
	}

	public CommonDataAdapter(Object inData) {
		this.setData(inData);
	}

	@Override
	public DataAdapter setData(Object obj) {
		this.inData = obj;
		transfer();
		return this;
	}

	@Override
	public Object getData() {
		return outData;
	}

	protected abstract void transfer();

	@Override
	public String toString() {
		return "CommonDataAdapter [inData=" + inData + ", outData=" + outData + "]";
	}
	
}
