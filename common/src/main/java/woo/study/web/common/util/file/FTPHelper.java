package woo.study.web.common.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import woo.study.web.common.util.AssertHelper;
import woo.study.web.common.util.IOSystem;
import woo.study.web.common.util.StringUtils;

/**
 * ftp工具类，实现ftp上传、下载<br>
 * <br>
 * 代码示例：<br>
 * FTPHelper ftpHelper = new FTPHelper("172.16.4.159");<br>
 * boolean loginSuccess =  ftpHelper.login("userId", "pwd");<br><br>
 * 登陆成功之后，可以操作文件<br>
 * boolean upload = ftpHelper.uploadBinaryFile("/home/tianjp/桌面/Velocity语法.doc", "D/ftp/中文文件夹/Velocity语法.doc");<br>
 * boolean upload = ftpHelper.uploadAsciiFile("/home/tianjp/桌面/无标题文档", "D/ftptest/TXT/无标题文档");<br>
 * boolean remove = ftpHelper.removeFile("D/ftptest/TXT/无标题文档");<br>
 * boolean remove = ftpHelper.removeDir("D/ftptest/TXT");<br>
 * boolean download = ftpHelper.download("D/ftptest/E/汉字/Velocity语法.doc", "/home/tianjp/D/ftptest/copy.doc");<br>
 * 
 * @author tianjp
 *
 */
public class FTPHelper {

	private static final Log	log	= LogFactory.getLog(FTPHelper.class);

	private FTPClient			ftpClient;

	private String				url;
	private int					port;
	private String				username;
	private String				password;

	public FTPHelper() {
		port = 21;
	}

	public FTPHelper(String url) {
		this();
		this.url = url;
	}

	public FTPHelper(String url, int port) {
		this(url);
		this.port = port;
	}

	public FTPHelper(String url, int port, String username, String password) {
		this(url, port);
		this.username = username;
		this.password = password;
	}

	public boolean login() {
		return login(username, password);
	}
	
	/**
	 * 登陆ftp服务器
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public boolean login(String username, String password) {
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(url, port);
			boolean loginSucc = ftpClient.login(username, password);
			if(loginSucc){
				ftpClient.setControlEncoding("UTF-8");
				 
			}
			return loginSucc;
		} catch (Exception ex) {
			log.error("FTP server login error.", ex);
		}
		return false;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param localFileStream 本地文件流
	 * @param remoteFilePath 服务器地址。包含路径和文件名。路径不存在会自动创建
	 * @param fileType 文件类型。二进制、文本文件
	 * @return true|false 上传成功|上传失败
	 */
	public boolean uploadFile(InputStream localFileStream, String remoteFilePath, int fileType) {
		try {
			ftpClient.enterLocalPassiveMode();
			ftpClient.setBufferSize(1024);
			ftpClient.setFileType(fileType);
			
			mkdir(remoteFilePath);
			
			String _remoteFileName = getISOString(remoteFilePath);
			boolean uploadSucc = ftpClient.storeFile(_remoteFileName, localFileStream);
			log.debug("uploadSucc code:" + ftpClient.getReplyString());
			return uploadSucc;

		} catch (Exception e) {
			String msg = "FTP server uploadFile error " + ", remoteFilePath:" + remoteFilePath;
			log.error(msg, e);
		}
		return false;
	}
	
	/**
	 * 文件上传
	 * @param localFilePath 本地文件地址
	 * @param remoteFilePath 服务器地址。包含路径和文件名。路径不存在会自动创建
	 * @param fileType 文件类型。二进制、文本文件
	 * @return true|false 上传成功|上传失败
	 */
	public boolean uploadFile(String localFilePath, String remoteFilePath, int fileType) {
		return uploadFile(IOSystem.getInputStream(localFilePath), remoteFilePath, fileType);
	}
	
	/**
	 * 上传二进制文件
	 * @param localFileStream 本地文件流
	 * @param remoteFilePath 服务器地址。包含路径和文件名。路径不存在会自动创建
	 * @return true|false 上传成功|上传失败
	 */
	public boolean uploadBinaryFile(InputStream localFileStream, String remoteFilePath) {
		return uploadFile(localFileStream, remoteFilePath, FTPClient.BINARY_FILE_TYPE);
	}
	
	/**
	 * 上传二进制文件
	 * @param localFilePath 本地文件地址
	 * @param remoteFilePath 服务器地址。包含路径和文件名。路径不存在会自动创建
	 * @return true|false 上传成功|上传失败
	 */
	public boolean uploadBinaryFile(String localFilePath, String remoteFilePath) {
		return uploadFile(IOSystem.getInputStream(localFilePath), remoteFilePath, FTPClient.BINARY_FILE_TYPE);
	}
	
	/**
	 * 上传文本文件
	 * @param localFileStream 本地文件流
	 * @param remoteFilePath 服务器地址。包含路径和文件名。路径不存在会自动创建
	 * @return true|false 上传成功|上传失败
	 */
	public boolean uploadAsciiFile(InputStream localFileStream, String remoteFilePath) {
		return uploadFile(localFileStream, remoteFilePath, FTPClient.ASCII_FILE_TYPE);
	}
	
	/**
	 * 上传文本文件
	 * @param localFilePath 本地文件地址
	 * @param remoteFilePath 服务器地址。包含路径和文件名。路径不存在会自动创建
	 * @return true|false 上传成功|上传失败
	 */
	public boolean uploadAsciiFile(String localFilePath, String remoteFilePath) {
		return uploadFile(IOSystem.getInputStream(localFilePath), remoteFilePath, FTPClient.ASCII_FILE_TYPE);
	}
	
	/**
	 * 删除ftp上的文件
	 * @param remoteFilePath 服务器上文件名以及路径
	 * @return true|false 删除成功|删除失败
	 */
	public boolean removeFile(String remoteFilePath) {
		try {
			ftpClient.enterLocalPassiveMode();
			return ftpClient.deleteFile(getISOString(remoteFilePath));
		} catch (IOException e) {
			String msg = "FTP server removeFile error " + ", remoteFilePath:" + remoteFilePath;
			log.error(msg, e);
		}
		return false;
	}
	
	/**
	 * 删除ftp服务器上目录
	 * @param remoteDir 服务器上目录
	 * @return true|false 删除成功|删除失败
	 */
	public boolean removeDir(String remoteDir){
		try {
			ftpClient.enterLocalPassiveMode();
			return ftpClient.removeDirectory(getISOString(remoteDir));
		} catch (IOException e) {
			String msg = "FTP server removeDir error " + ", remoteDir:" + remoteDir;
			log.error(msg, e);
		}
		return false;
	}
	
	/**
	 * 在服务器中创建目录
	 * @param remote 服务器地址。包含路径和文件名
	 * @return
	 */
	private void mkdir(String remote) {
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		String isoDir = getISOString(directory);
		System.out.println("directory:" + directory);
		System.out.println("isoDir:" + isoDir);
		
		if("/".equals(isoDir) || AssertHelper.isEmpty(isoDir)){
			return;
		}
		
		try {
			String currentPath = "";
			
			String[] directoies = getDirectories(remote);
			log.debug("mkdir directoies=" + Arrays.toString(directoies));
			for(String dir : directoies){
				
				if(AssertHelper.notEmpty(dir)){
					ftpClient.changeWorkingDirectory(currentPath);
					log.debug("mkdir before currentPath=" + currentPath);
					currentPath += "/"+dir;
					log.debug("mkdir after currentPath=" + currentPath);
					FTPFile[]  ftpFiles  = ftpClient.listDirectories(currentPath);
					
					log.debug("mkdir after ftpFiles=" + Arrays.toString(ftpFiles));
					if(ftpFiles == null || ftpFiles.length == 0){
						ftpClient.makeDirectory(currentPath);
					}
				}
			}
			
			ftpClient.changeWorkingDirectory("/");
		} catch (IOException e) {
			log.error("mkdir remote:" + remote + ", reply msg:" + ftpClient.getReplyString(), e);
		}
	}

	/**
	 * 从FTP服务器下载文件
	 * 
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param remoteFilePath
	 *            要下载的文件名
	 * @param localFilePath
	 *            下载后保存到本地的路径
	 * @return
	 */
	
	/**
	 * 从FTP服务器下载文件
	 * @param remoteFilePath 服务器文件路径
	 * @param localFilePath 文件保存路径。包含文件名
	 * @return true|false 成功|失败
	 */
	public boolean download(String remoteFilePath, String localFilePath) {
		InputStream inputStream = download(remoteFilePath);
		if(inputStream != null ){
			IOSystem.writeTo(inputStream, localFilePath);
			return true;
		}
		return false;
	}
	
	private String[] getDirectories(String path){
		if("/".equals(path)){
			return null;
		}
		
		int lastIndex = path.lastIndexOf("/");
		String directorStr = path.substring(0, lastIndex);
		
		return directorStr.split("/");
		
	}
	
	private String getFilename(String path){
		int lastIndex = path.lastIndexOf("/");
		if(lastIndex == -1){
			return path;
		}else{
			String filename = path.substring(lastIndex + 1);
			return filename;
		}
	}
	
	/**
	 * 从FTP服务器下载文件
	 * @param remoteFilePath 服务器文件路径
	 * @return InputStream 文件流
	 */
	public InputStream download(String remoteFilePath) {
		try {
			// add by tianjp for ftp-client, linux下假死问题 on 2015-04-20 21:00 start
			ftpClient.enterLocalPassiveMode();
			// add by tianjp for ftp-client, linux下假死问题 on 2015-04-20 21:00 end
			
			String isoRemoteFilePath = getISOString(remoteFilePath);
			InputStream inputStream = ftpClient.retrieveFileStream(isoRemoteFilePath);
			return inputStream;
		} catch (IOException e) {
			String msg = "FTP server downFile error!";
			log.error(msg, e);
		}
		return null;
	}
	
	/**
	 * FTP连接关闭
	 */
	public void shutdown() {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				String msg = "FTP server shutdown error!";
				log.error(msg, e);
				throw new RuntimeException(msg, e);
			}
		}
	}
	
	public void listRootFiles(){
		 try {
			FTPFile[]  ftpFiles = ftpClient.listFiles("/");
			
			for(FTPFile ftpFile : ftpFiles){
				log.debug(ftpFile.getName());
			}
		} catch (IOException e) {
			log.error("listRootFiles error!", e);
		}
	}
	
	private static String getUTF8String(String name) {
		return StringUtils.changeEncoding(name, "ISO-8859-1", "UTF-8");
	}

	private static String getISOString(String name) {
		return StringUtils.changeEncoding(name, "UTF-8", "ISO-8859-1");
	}


	/**
	 * 解析ftp文件路径，活动ftp地址、用户名、密码、文件名、文件相对路径
	 * 
	 * @param ftpFilePath
	 *            ftp路径 例如：ftp://song:song@172.16.4.220/bq/t.mpg
	 * @return 包含上述内容的Map对象
	 */
	public static Map<String, String> getFtpMap(String ftpFilePath) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String path = ftpFilePath.substring(ftpFilePath.indexOf("//") + 2);
			String[] msg = path.split("@");
			String[] user_pwd = msg[0].split(":");
			String ip_dir = msg[1];
			map.put("user", user_pwd[0]);
			map.put("password", user_pwd[1]);
			map.put("ip", ip_dir.substring(0, ip_dir.indexOf("/")));
			map.put("fileDir", ip_dir.substring(ip_dir.indexOf("/"), ip_dir.lastIndexOf("/") + 1));
			map.put("fileName", ip_dir.substring(ip_dir.lastIndexOf("/") + 1));
			map.put("fullName", ip_dir.substring(ip_dir.indexOf("/")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String ss = "ftp://s*fsfsf接口 _+ong:song@172.16.4.220/bq/xxx123/t.mpg";
		System.out.println(ss.matches("^ftp\\://.+\\:.+\\@([1-9]|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])(\\.([0-9]|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])){3}(\\/.+)+(\\.)+\\w+$"));
	}

}
