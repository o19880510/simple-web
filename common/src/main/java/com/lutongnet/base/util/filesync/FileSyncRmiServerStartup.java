package com.lutongnet.base.util.filesync;

import java.rmi.Naming;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.util.filesync.enhance.BaseRMIClientSocketFactory;

public class FileSyncRmiServerStartup implements Runnable {

	private static Logger	log	= LoggerFactory.getLogger(FileSyncRmiServerStartup.class);

	private int				port;
	private int				dataPort;
	private String			ip;
	private String			serverName;
	private String			rmiServerAddr;
	private String			root;

	public FileSyncRmiServerStartup() {

	}

	public FileSyncRmiServerStartup(int port, String ip, String serverName, String workDir) {
		this.port = port;
		this.ip = ip;
		this.serverName = serverName;
		this.root = workDir;

	}

	public void startup() {
		rmiServerAddr = "rmi://" + ip + ":" + port + "/" + this.serverName;
		log.debug("rmiServerAddr = " + rmiServerAddr);

		new Thread(this).start();

		log.info("StartupFFSyncRMIService start " + rmiServerAddr);
	}

	public void run() {

		System.setProperty("java.rmi.server.hostname", ip);

		try {
			
			RMISocketFactory.setSocketFactory(new BaseRMIClientSocketFactory(this.dataPort));
			
			LocateRegistry.createRegistry(port);

			FileSyncRmiServer fileSyncRmiServer = new FileSyncRmiServerImpl(root);
			Naming.bind(rmiServerAddr, fileSyncRmiServer);

			log.debug("rmi server ffSyncRMIService = " + fileSyncRmiServer);
			log.debug("rmi server start on " + port + " .");

		} catch (Exception e) {
			log.error("RmiServer start error!rmiServerAddr=" + rmiServerAddr, e);
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public int getDataPort() {
		return dataPort;
	}

	public void setDataPort(int dataPort) {
		this.dataPort = dataPort;
	}

	@Override
	public String toString() {
		return "StartupFFSyncRMIService [port=" + port + ", ip=" + ip + ", serverName=" + serverName
				+ ", rmiServerAddr=" + rmiServerAddr + ", root=" + root + "]";
	}

}
