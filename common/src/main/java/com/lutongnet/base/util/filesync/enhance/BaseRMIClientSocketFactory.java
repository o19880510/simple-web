package com.lutongnet.base.util.filesync.enhance;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMISocketFactory;

public class BaseRMIClientSocketFactory extends RMISocketFactory {
	private ServerSocket serverSocket;
	private int port;
	
	
	public BaseRMIClientSocketFactory(int port) {
		super();
		this.port = port;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		return new Socket(host, port);
	}

	@Override
	public ServerSocket createServerSocket(int tempPort) throws IOException {
		if (tempPort == 0) {
			return new ServerSocket(this.port);
		}
		return new ServerSocket(tempPort);
	}

}
