package woo.study.web.common.util;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * IO流方法集合
 * 数据输入、输出相关方法集合
 * 
 * @author tianjp
 *
 */
public class IOSystem {
	
	/**
	 * 将内容写入到给定的path。注意若path已经存在，会覆盖原文件
	 * @param content 输入内容
	 * @param path 输出地址
	 */
	public static void writeTo(String content,String path){
		
		writeTo(content, path, false);
	}
	
	/**
	 * 将内容写入到给定的path
	 * @param content 输入内容
	 * @param path 输出地址
	 * @param append 是否追加。true 追加，false 不追加
	 */
	public static void writeTo(String content,String path,boolean append){
		
		try {
			
			makeDir(path);
			
			FileWriter fileWriter = new FileWriter(path,append);
			fileWriter.write(content);
			fileWriter.flush();
			fileWriter.close();
			
		} catch (Exception e) {
			throw new RuntimeException("Write to file failure! exception is : " + e);
		}
	}
	
	/**
	 * 将内容写入到给定的path
	 * @param content 输入字节数组
	 * @param path 输出地址
	 */
	public static void writeTo(byte[] content,String path){
		try{
			makeDir(path);
			
			FileOutputStream fileOut = new FileOutputStream(path);
			
			fileOut.write(content);
			fileOut.flush();
			fileOut.close();
			
		}catch(Exception e){
			throw new RuntimeException("Write to file failure! exception is : " + e);
		}
	}
	
	/**
	 * 将内容写入到给定的path。注意若path已经存在，会覆盖原文件
	 * @param content 输入内容
	 * @param path 输出地址
	 */
	public static void writeTo(InputStream inputStream, String path) {
		byte[] fileBytes = readToBytes(inputStream);
		writeTo(fileBytes, path);
	}
	
	/**
	 * 新建文件夹
	 * @param path 路径
	 */
	public static void makeDir(String path){
		
		path = path.replaceAll("\\\\", "/");
		
		int endIndex = path.lastIndexOf("/");
		if(endIndex != -1){
			String dirPath = path.substring(0, endIndex);
			File file = new File(dirPath);
			
			if(!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	/**
	 * 通过path读取内容
	 * @param path 文件地址
	 * @return byte[]
	 */
	public static byte[] read(String path){
		try{
			return readToBytes(getInputStream(path));
		}catch(Exception e){
			throw new RuntimeException("Read file failure! exception is : " + e);
		}
	}
	
	/**
	 * 通过path获取InputStream流
	 * @param path	文件地址
	 * @return InputStream流
	 */
	public static InputStream getInputStream(String path){
		
		try {
			return new FileInputStream(path);
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException("getInputStream failure! exception is : " + e);
		}
	}
	
	/**
	 * 读取InputStream流中的数据
	 * @param in 输入流
	 * @return byte[] 输出字节数组
	 */
	public static byte[] readToBytes(InputStream in){
		try {
			
			byte[] bytes = new byte[1024];
			int count = 0;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			while((count = in.read(bytes)) != -1){
				byteOut.write(bytes, 0, count);
			}
			
			return byteOut.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("IOSystem readToBytes failure: "+e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				throw new RuntimeException("IOSystem readToBytes InputStream close() failure: "+e);
			}
		}
		
	}
	
	/**
	 * 读取InputStream流中的数据
	 * @param in 输入流
	 * @return String 输出字符串，UTF-8格式
	 */
	public static String readToString(InputStream in){
		return StringUtils.toString(readToBytes(in));
	}
	
	/**
	 * 读取path地址中的数据
	 * @param path 输入地址
	 * @return String 输出字符串，UTF-8格式
	 */
	public static String readToString(String path){
		return StringUtils.toString(read(path));
	}
	
	/**
	 * 判断文件或者目录是否存在
	 * @param path 输入地址
	 * @return boolean true存在，false 不存在
	 */
	public static boolean isFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	/**
	 * 判断文件或者目录是否存在
	 * @param path 输入地址
	 * @return boolean false存在，true不存在
	 */
	public static boolean isFileNotExist(String path) {
		return !isFileExist(path);
	}
	
	/**
	 * 解压缩zip格式并读取数据
	 * @param in 输入流
	 * @return String 字符串数据，UTF-8格式
	 * @throws IOException
	 */
	public static String unGzipToString(InputStream in) throws IOException {
		return StringUtils.toString(unGzip(in));
	}
	
	/**
	 * 解压缩zip格式并读取数据
	 * @param in 输入流
	 * @return byte[] 字节数组
	 * @throws IOException
	 */
	public static byte[] unGzip(InputStream in) throws IOException {
		GZIPInputStream gzipInputStream = new GZIPInputStream(in);
		return readToBytes(gzipInputStream);
	}
	
	/**
	 * 删除文件或者目录
	 * @param path 路径
	 */
	public static void delete(String path) {
		File file = new File(path);
		file.delete();
	}
	
	
	/**
	 * 从Reader中读取数据，并转换为字符串
	 * @param reader 
	 * @return String
	 */
	public static String read(Reader reader){
		StringBuilder sb = new StringBuilder();
		char[] chars = new char[1024];
		
		int count;
		try {
			while( (count = reader.read(chars)) != -1){
				sb.append(new String(chars, 0, count));
			}
		} catch (IOException e) {
			throw new RuntimeException("IOSystem read failure: "+e);
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException("IOSystem read Reader close() failure: "+e);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取类的存放位置
	 * @return 类文件夹的绝对路径
	 */
	public static String getClasspath(){
		String classRootPath = null;
		URL runPath = IOSystem.class.getResource("/");
		if( runPath == null){
			classRootPath = System.getProperty("user.dir") + "/";
		}else{
			classRootPath = runPath.getPath();
			try {
				classRootPath =  URLDecoder.decode(classRootPath, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("Decode url error! exception is : " + e);
			}
			
			String os = System.getProperties().getProperty("os.name").toLowerCase();
			if(os.indexOf("windows") != -1){
				classRootPath = classRootPath.substring(1);
			}
		}
		
		return classRootPath;
	}
	
	
	/**
	 * 列出目录下所有文件
	 * @param root 根目录
	 * @param dir 相对目录
	 * @return
	 */
	public static List<String> listFiles(String root, String dir) {

		File rootDir = new File(root + dir);
		List<String> fileList = new ArrayList<String>();
		
		if(!rootDir.exists() || rootDir.isFile()){
			if(AssertHelper.notEmpty(dir)){
				fileList.add(dir);
			}
			return fileList;
		}
		
		File[] files = rootDir.listFiles();

		LinkedList list = new LinkedList();
		for(File tempfile : files){
			if (tempfile.isDirectory()) {
				list.add(tempfile);
			}
			fileList.add(getFileRelativePath(tempfile.getAbsolutePath(), root));
		}

		File tempfile;
		while (!list.isEmpty()) {
			tempfile = (File) list.removeFirst();
			if (tempfile.isDirectory()) {
				File[] tempFiles = tempfile.listFiles();
				if (tempFiles != null && tempFiles.length > 0){
					for(File pTemoFile: tempFiles){
						if (pTemoFile.isDirectory()) {
							list.add(pTemoFile);
						}
						fileList.add(getFileRelativePath(pTemoFile.getAbsolutePath(), root));
					}
				}
			} else {
				fileList.add(getFileRelativePath(tempfile.getAbsolutePath(), root));
			}
		}
		return fileList;
	}
	
	/**
	 * 获取文件sha1值
	 * @param file 
	 * @return
	 */
	public static String getFileSHA1(File file) {
		DigestInputStream digestInputStream = null;
		try {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			
			digestInputStream = new DigestInputStream(new FileInputStream(file), messageDigest);
			
			byte[] buffer = new byte[1024];
			while (digestInputStream.read(buffer) > 0){
			}
			
			messageDigest = digestInputStream.getMessageDigest();
			
			byte[] resultByteArray = messageDigest.digest();
			
			return StringUtils.byteArrayToHex(resultByteArray);
			
		} catch (Exception e) {
			return null;
		} finally {
			try {
				digestInputStream.close();
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 获取文件sha1值
	 * @param filePath 文件绝对路径
	 * @return
	 */
	public static String getFileSHA1(String filePath) {
		return getFileSHA1(new File(filePath));
	}
	
	private static String getFileRelativePath(String absPath, String root){
		int index = absPath.indexOf(root) + root.length() + 1;
		String path = absPath.substring(index);
		return path;
	}
}
