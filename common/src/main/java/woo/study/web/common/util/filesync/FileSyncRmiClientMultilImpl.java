package woo.study.web.common.util.filesync;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.filesync.path.FilePathWebRoot;

public class FileSyncRmiClientMultilImpl implements FileSyncRmiClient{

	private static Logger			log	= LoggerFactory.getLogger(FileSyncRmiClientMultilImpl.class);

	private List<String>	fileSyncRmiAddressList;
	private String root;
	
	public FileSyncRmiClientMultilImpl(String workDir, String serverName , String... singleIpPorts) {
		this.root = workDir;
		
		setRemoteRmiAddress(serverName, singleIpPorts);
		
		if(AssertHelper.isEmpty(this.root)){
			this.root = new FilePathWebRoot().getPath();
		}
	}
	
	private void setRemoteRmiAddress(String serverName , String... singleIpPorts) {
		fileSyncRmiAddressList = new ArrayList<String>();
		
		for (String singleIpPort : singleIpPorts) {
			singleIpPort = singleIpPort.trim();
			String remoteRmi = "rmi://" + singleIpPort + "/" + serverName;
			fileSyncRmiAddressList.add(remoteRmi);
		}
	}
	

	private List<FileSyncRmiServer> getRemoteRmiServer() {

		List<FileSyncRmiServer> fileSyncList = new ArrayList<FileSyncRmiServer>();

		String fileSyncRmiAddress = null;
		try {
			for (int i = 0; i < fileSyncRmiAddressList.size(); i++) {
				fileSyncRmiAddress = fileSyncRmiAddressList.get(i);
				FileSyncRmiServer ffSyncRMIService = (FileSyncRmiServer) Naming.lookup(fileSyncRmiAddress);
				fileSyncList.add(ffSyncRMIService);
				log.debug("getRMIFileSync fileSyncRmiAddress=" + fileSyncRmiAddress + ",\t ffSyncRMIService="+ffSyncRMIService);
			}
		} catch (Exception e) {
			log.error("getRMIFileSync error! fileSyncRmiAddress=" + fileSyncRmiAddress, e);
		}

		return fileSyncList;
	}
	
	@Override
	public boolean sync(String filePath) {
		List<FileSyncRmiServer> fileSyncList = getRemoteRmiServer();
		log.debug("sync fileSyncList=" + fileSyncList);
		
		for (FileSyncRmiServer fileSyncRmiServer : fileSyncList) {
			try {
				new FileSyncRmiClientSingleImpl(fileSyncRmiServer, root).sync(filePath);
			} catch (RemoteException e) {
				log.error("sync file error! filePath=" + filePath + ", ffSyncRMIService=" + fileSyncRmiServer, e);
			}
		}
		return true;
	}
}
