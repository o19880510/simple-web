package woo.study.web.common.util.filesync;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Set;

public class FileSyncRmiServerImpl extends UnicastRemoteObject implements FileSyncRmiServer {

	private static final long	serialVersionUID	= 1L;

	private FileSync		fileSyncServerImpl;

	public FileSyncRmiServerImpl(String root) throws RemoteException {
		super();
		this.fileSyncServerImpl = new FileSyncImpl(root);
	}

	public FileSyncRmiServerImpl(FileSync fileSyncServerImpl) throws RemoteException {
		super();
		this.fileSyncServerImpl = fileSyncServerImpl;
	}

	@Override
	public boolean upload(String filePath, byte[] fileBytes) throws RemoteException {
		return fileSyncServerImpl.upload(filePath, fileBytes);
	}

	@Override
	public boolean upload(String filePath, byte[] fileBytes, boolean override) throws RemoteException {
		return fileSyncServerImpl.upload(filePath, fileBytes, override);
	}

	@Override
	public boolean upload(String filePath, InputStream inputStream) throws RemoteException {
		return fileSyncServerImpl.upload(filePath, inputStream);
	}

	@Override
	public boolean upload(String filePath, InputStream inputStream, boolean override) throws RemoteException {
		return fileSyncServerImpl.upload(filePath, inputStream, override);
	}

	@Override
	public boolean remove(String filePath) throws RemoteException {
		return fileSyncServerImpl.remove(filePath);
	}

	@Override
	public Set<FileSyncInfo> checkFiles(String filePath, Map<String, FileSyncInfo> fileMap) throws RemoteException {
		return fileSyncServerImpl.checkFiles(filePath, fileMap);
	}

}
