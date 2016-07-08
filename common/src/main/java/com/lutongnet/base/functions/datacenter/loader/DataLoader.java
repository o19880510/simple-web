package com.lutongnet.base.functions.datacenter.loader;


public interface DataLoader<T> {
	public T loading();
	public String getDataName();
}
