package woo.study.web.system.datacenter.loader;


import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.functions.datacenter.loader.CommonDataLoader;

public class ConfigPropesDataLoader extends CommonDataLoader {
	
	private static Logger log = LoggerFactory.getLogger(ConfigPropesDataLoader.class);
	
	public Properties loading() {
		
		Properties properties = new Properties();
		
		try {
			properties.load(getFileStream());
		} catch (IOException e1) {
			throw new RuntimeException("Load file " + getFileName() + " fail!", e1);
		}
		
		return properties;
		
	}

}
