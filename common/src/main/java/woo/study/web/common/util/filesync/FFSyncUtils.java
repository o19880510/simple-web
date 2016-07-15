package woo.study.web.common.util.filesync;

import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import woo.study.web.common.util.IOSystem;

public class FFSyncUtils {

	public static Map<String, FileSyncInfo> getFileInfoMap(String root, String filePath) {
		List<String> fileList = IOSystem.listFiles(root, filePath);

		Map<String, FileSyncInfo> fileMap = new HashMap<String, FileSyncInfo>();

		for (String path : fileList) {
			File file = new File(root + path);
			if (file.exists()) {
				String fileHash = null; 
				if(file.isFile()){
					fileHash = IOSystem.getFileSHA1(file);
				}
				fileMap.put(path, new FileSyncInfo(path, fileHash, file.lastModified()));
			}
		}

		return fileMap;
	}
}
