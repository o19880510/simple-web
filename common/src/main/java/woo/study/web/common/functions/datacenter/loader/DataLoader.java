package woo.study.web.common.functions.datacenter.loader;


public interface DataLoader<T> {
	public T loading();
	public String getDataName();
}
