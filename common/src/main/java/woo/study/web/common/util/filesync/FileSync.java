package woo.study.web.common.util.filesync;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;


/**
 * 
 * 文件同步接口
 * @author tianjp
 *
 */
public interface FileSync extends Serializable {
	
	/**
	 * 上传文件
	 * @param filePath 文件保存地址，包含文件名
	 * @param fileBytes 文件二进制
	 * @return true 成功
	 */
	public boolean upload(String filePath, byte[] fileBytes);
	
	/**
	 * 上传文件
	 * @param filePath 文件保存地址，包含文件名
	 * @param fileBytes 文件二进制
	 * @param override 若文件存在，是否覆盖。true-覆盖
	 * @return true 成功
	 */
	public boolean upload(String filePath, byte[] fileBytes, boolean override);
	/**
	 * 上传文件
	 * @param filePath 文件保存地址，包含文件名
	 * @param fileBytes 文件二进制
	 * @return true 成功
	 */
	public boolean upload(String filePath, InputStream inputStream);
	
	/**
	 * 上传文件
	 * @param filePath 文件保存地址，包含文件名
	 * @param fileBytes 文件二进制
	 * @param override 若文件存在，是否覆盖。true-覆盖
	 * @return true 成功
	 */
	public boolean upload(String filePath, InputStream inputStream, boolean override);
	
	/**
	 * 移除文件、文件夹
	 * @param filePath 文件路径或者文件夹路径
	 * @return true 成功
	 */
	public boolean remove(String filePath);
	
	/**
	 * 校验文件夹。返回不存在、需要更新的文件列表
	 * @param fileMap
	 * @return
	 */
	public Set<FileSyncInfo> checkFiles(String filePath, Map<String, FileSyncInfo> fileMap) ;

}
