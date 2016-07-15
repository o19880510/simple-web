package woo.study.web.common.util.filesync;

import java.io.Serializable;


/**
 * 
 * 文件同步接口
 * @author tianjp
 *
 */
public interface FileSyncRmiClient extends Serializable {
	
	/**
	 * 同步文件夹
	 * 包含上传文件和删除文件两大功能
	 * @param filePath 同步文件/文件夹
	 * @return true 成功
	 */
	public boolean sync(String filePath);
	
}
