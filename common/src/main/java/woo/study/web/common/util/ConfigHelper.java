package woo.study.web.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.IOSystem;
import woo.study.web.common.util.value.ValueGettable;

public class ConfigHelper implements ValueGettable{

	private static Logger			log			= LoggerFactory.getLogger(ConfigHelper.class);
	private static final Properties	PROPERTIES	= new Properties();

	public static ConfigHelper getInstance() {
		return Init.configHelper;
	}
	
	public String get(String key){
		return PROPERTIES.getProperty(key);
	}

	private static class Init {
		private static ConfigHelper	configHelper	= new ConfigHelper();

		static {
			String path = IOSystem.getClasspath() + "config.properties";

			InputStream in = IOSystem.getInputStream(path);

			try {
				PROPERTIES.load(in);
			} catch (IOException e) {
				String error = "Load config error! path=" + path;
				log.error(error, e);
				throw new RuntimeException(error, e);
			}

		}

	}
}
