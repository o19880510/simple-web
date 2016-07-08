package com.lutongnet.base.util.filesync;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.util.ConfigHelper;
import com.lutongnet.base.util.value.ValueHelper;
import com.lutongnet.base.util.value.ValueHelperContainer;

public class FileSyncRmiServerConfigStartup {
	private static Logger		log			= LoggerFactory.getLogger(FileSyncRmiServerConfigStartup.class);

	private static final String	SERVER_NAME	= "file.sync.server.name";
	private static final String	IP			= "file.sync.local.server.ip";
	private static final String	PORT		= "file.sync.local.server.port";
	private static final String	PORT_DATA	= "file.sync.local.server.port.data";
	private static final String	WORK_DIR	= "file.sync.local.server.work.dir";
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		ValueHelper valueHelper = new ValueHelperContainer(ConfigHelper.getInstance());
		
		FileSyncRmiServerStartup fileSyncRmiServerStartup = new FileSyncRmiServerStartup();
		
		fileSyncRmiServerStartup.setServerName(valueHelper.getString(SERVER_NAME));
		fileSyncRmiServerStartup.setIp(valueHelper.getString(IP));
		fileSyncRmiServerStartup.setPort(valueHelper.getInt(PORT));
		fileSyncRmiServerStartup.setDataPort(valueHelper.getInt(PORT_DATA));
		fileSyncRmiServerStartup.setRoot(valueHelper.getString(WORK_DIR));
		
		fileSyncRmiServerStartup.startup();
	}
	
}
