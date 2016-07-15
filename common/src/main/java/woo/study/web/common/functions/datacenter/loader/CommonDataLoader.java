package woo.study.web.common.functions.datacenter.loader;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.functions.datacenter.DataContainer;
import woo.study.web.common.util.IOSystem;

public abstract class CommonDataLoader<T> implements DataLoader<T>{
	
	private static Logger log = LoggerFactory.getLogger(CommonDataLoader.class);
	
	protected String dataName;
	protected String dataDesc;
	protected String fileName;
	protected String dependDataName;
	protected DataContainer dataContainer;
	
	
	protected <K> K getDependData(Class<K> clazz) {
		return getDataContainer().get(getDependDataName(), clazz);
	}
	
	protected InputStream getFileStream() {
		return getFileStream(getFileName());
	}
	
	protected InputStream getFileStream(String fileName) {
		String path = IOSystem.getClasspath() + fileName;
		InputStream inputStream = IOSystem.getInputStream(path);
		return inputStream;
	}
	
	
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDependDataName() {
		return dependDataName;
	}

	public void setDependDataName(String dependDataName) {
		this.dependDataName = dependDataName;
	}

	public DataContainer getDataContainer() {
		return dataContainer;
	}

	public void setDataContainer(DataContainer dataContainer) {
		this.dataContainer = dataContainer;
	}

	public String getDataDesc() {
		return dataDesc;
	}

	public void setDataDesc(String dataDesc) {
		this.dataDesc = dataDesc;
	}
}
