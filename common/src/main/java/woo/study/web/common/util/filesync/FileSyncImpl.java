package woo.study.web.common.util.filesync;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.IOSystem;
import woo.study.web.common.util.filesync.path.FilePathWebRoot;

public class FileSyncImpl implements FileSync {
	
	private static final long	serialVersionUID	= 1L;
	private static Logger log	= LoggerFactory.getLogger(FileSyncImpl.class);
	
	private String root;
	
	public FileSyncImpl(String root) {
		super();
		this.root = root;
		
		if(AssertHelper.isEmpty(this.root)){
			this.root = new FilePathWebRoot().getPath();
		}
	}

	@Override
	public boolean upload(String filePath, byte[] fileBytes) {
		log.debug("upload filePath=" + filePath);
		log.debug("upload fileBytes.length=" + fileBytes.length);
		
		boolean fileExist = IOSystem.isFileExist(root + filePath);
		if(fileExist){
			return false;
		}
		
		IOSystem.writeTo( fileBytes, root + filePath );
		return true;
	}

	@Override
	public boolean upload(String filePath, byte[] fileBytes, boolean override) {
		log.debug("upload filePath=" + filePath);
		log.debug("upload fileBytes.length=" + fileBytes.length);
		log.debug("upload override=" + override);
		
		if(override){
			IOSystem.delete(root + filePath);
			IOSystem.writeTo( fileBytes, root + filePath );
			return true;
		}else{
			return upload(filePath, fileBytes);
		}
		
	}
	
	@Override
	public boolean upload(String filePath, InputStream inputStream) {
		byte[] fileBytes = IOSystem.readToBytes(inputStream);
		return upload(filePath, fileBytes);
	}

	@Override
	public boolean upload(String filePath, InputStream inputStream, boolean override) {
		byte[] fileBytes = IOSystem.readToBytes(inputStream);
		return upload(filePath, fileBytes, override);
	}

	@Override
	public boolean remove(String filePath) {
		log.debug("remove filePath=" + filePath);
		
		File file = new File(root + filePath);
		if(file.isFile()){
			IOSystem.delete(root + filePath);
		}else if( file.isDirectory()){
			List<String> fileList = IOSystem.listFiles(root, filePath);
			for(int i = fileList.size() - 1; i >= 0; i--){
				IOSystem.delete(root + fileList.get(i));
			}
			IOSystem.delete(root + filePath);
		}else{
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 */
	public Set<FileSyncInfo> checkFiles(String filePath, Map<String, FileSyncInfo> inputFileMap) {
		
		Map<String, FileSyncInfo> LocalFileMap =  FFSyncUtils.getFileInfoMap(root, filePath);
		 
		Set<FileSyncInfo> outFileSet = new HashSet<FileSyncInfo>();
		
		Map<String, FileSyncInfo> outFileMap = new HashMap<String, FileSyncInfo>();
		
		for(FileSyncInfo localFileSyncInfo : LocalFileMap.values()) {
			
			FileSyncInfo inputFileSyncInfo = inputFileMap.remove(localFileSyncInfo.getRelativePath());
			log.debug("localFileSyncInfo="+localFileSyncInfo);
			log.debug("inputFileSyncInfo="+inputFileSyncInfo);
			
			// 服务器上不存在此文件，此文件要在子项目中删除
			if( inputFileSyncInfo == null){
				localFileSyncInfo.setStatus(FileSyncInfo.Status.REMOVE);
				
			// 两个文件的hash不同(文件内容不同)，以服务器上的文件为准
			}else if( !localFileSyncInfo.equals(inputFileSyncInfo) ){
				localFileSyncInfo.setStatus(FileSyncInfo.Status.UPDATE);
			}else{
				localFileSyncInfo.setStatus(FileSyncInfo.Status.EXIST);
			}
			
			outFileSet.add(localFileSyncInfo);
		}
		
		for(FileSyncInfo fileSyncInfo:inputFileMap.values()){
			fileSyncInfo.setStatus(FileSyncInfo.Status.NOT_EXIST);
			outFileSet.add(fileSyncInfo);
		}
		
		return outFileSet;
	}
}
