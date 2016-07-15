package woo.study.web.common.util.filesync;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.IOSystem;

public class FileSyncRmiClientSingleImpl implements FileSyncRmiClient{
	
	private static final long	serialVersionUID	= 1L;

	private static Logger log	= LoggerFactory.getLogger(FileSyncRmiClientSingleImpl.class);
	
	private FileSyncRmiServer	fileSyncServer;
	private String root;
	
	public FileSyncRmiClientSingleImpl(FileSyncRmiServer fileSyncRmiServer, String root) throws RemoteException {
		super();
		this.root = root;
		
		this.fileSyncServer = fileSyncRmiServer;
	}
	
	@Override
	public boolean sync(String filePath) {
		
		try{
			log.debug("FFSyncRMIClient sync root=" + root);
			log.debug("FFSyncRMIClient sync filePath=" + filePath);
			Map<String, FileSyncInfo> fileMap = FFSyncUtils.getFileInfoMap(root, filePath);
			log.debug("FFSyncRMIClient sync fileMap=" + fileMap);
			
			Set<FileSyncInfo> updateFileSet = fileSyncServer.checkFiles(filePath, fileMap);
			log.debug("FFSyncRMIClient sync updateFileSet=" + updateFileSet);
			
			for(FileSyncInfo fileSyncInfo : updateFileSet){
				
				switch(fileSyncInfo.getStatus()){
					case NOT_EXIST:{
						
						upload(fileSyncInfo, false);
						break;
					}
					case UPDATE:{
						
						upload(fileSyncInfo, true);
						break;
					}
					case REMOVE:{
						fileSyncServer.remove(fileSyncInfo.getRelativePath());
						break;
					}
				}
			}
		}catch(Exception e){
			log.error("FileSyncRmiClientSingleImpl sync error, path=" + filePath, e );
			return false;
		}
		
		return true;
	}
	
	private void upload(FileSyncInfo fileSyncInfo, boolean override) throws RemoteException{
		
		String path = root + fileSyncInfo.getRelativePath();
		if(new File(path).isFile()){
			fileSyncServer.upload(fileSyncInfo.getRelativePath(), IOSystem.read(path), override);
		}
	}
}
