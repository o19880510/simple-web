package com.lutongnet.base.util.file;


import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.lutongnet.base.util.AssertHelper;
import com.lutongnet.base.util.IOSystem;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * 
 * 压缩文件帮助类
 * 
 * @author tianjp
 *
 */
public class ArchiveHelper {
	
	// 回车符
	public static final String ENTER = "\r\n";
	
	/**
	 * 將rar文件解壓到指定目錄
	 * 
	 * @param rarFile rar文件路径
	 * @param destDir 指定目录
	 */
	public static void unrar(String rarFile, String destDir) {

		Archive archive = null;
		try {
			String targetDir = destDir.endsWith("/") ? destDir : destDir + "/";

			archive = new Archive(new File(rarFile));
			
			for (FileHeader fh : archive.getFileHeaders()) {
				if (fh.isDirectory()){
					continue;
				}
				String fileName = fh.getFileNameString().trim();
				fileName = targetDir + fileName.replaceAll("\\\\", "/");
				IOSystem.makeDir(fileName);

				FileOutputStream fos = new FileOutputStream(fileName);
				archive.extractFile(fh, fos);
				fos.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("unrar archive extract file.", e);
		} finally {
			try {
				archive.close();
			} catch (IOException e) {
				throw new RuntimeException("unrar archive close().", e);
			}
		}
	}

	/**
	 * 將zip文件解壓到指定目錄
	 * 
	 * @param zipFile zip文件路径
	 * @param destDir 指定目录
	 */
	public static void unzip(String zipFile, String destDir) {
		ZipFile zip = null;
		try {
			
			String targetDir = destDir.endsWith("/") ? destDir : destDir + "/";
			
			zip = new ZipFile(zipFile);
			Enumeration<ZipEntry> entries = zip.getEntries();
			List<ZipEntry> entryList = Collections.list(entries);
			
			for(ZipEntry zipEntry : entryList){
				if (zipEntry.isDirectory()){
					continue;
				}
				
				InputStream in = zip.getInputStream(zipEntry);
				String fileName = targetDir + zipEntry.getName();
				IOSystem.writeTo(in, fileName);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException("unzip archive extract file.", e);
		} finally {
			try {
				zip.close();
			} catch (IOException e) {
				throw new RuntimeException("unzip zip close().", e);
			}
		}
	}

	/** 打印用WinRar压缩的文档 */
	
	/**
	 * 获取rar文件列表
	 * 
	 * @param rarFile
	 * @param lineSplitChar
	 * @return
	 */
	public static String getRarEntryList(File rarFile, String lineSplitChar) {
		
		Archive archive = null;
		try {
			archive = new Archive(rarFile);
			StringBuilder entryString = new StringBuilder();
			
			for (FileHeader fh : archive.getFileHeaders()) {
				String fileName = fh.getFileNameString();
				entryString.append(fileName).append(lineSplitChar);
			}
			
			return entryString.toString();
			
		} catch (Exception e) {
			throw new RuntimeException("getRarEntryList error.", e);
		} finally {
			try {
				archive.close();
			} catch (IOException e) {
				throw new RuntimeException("getRarEntryList archive close error.", e);
			}
		}
	}
	
	/**
	 * 获取rar文件列表。默认使用回车符分割
	 * @param rarFile
	 * @return
	 */
	public static String getRarEntryList(File rarFile){
		return getRarEntryList(rarFile, ENTER);
	}
	/**
	 * 获取rar文件列表
	 * @param rarFile
	 * @param lineSplitChar
	 * @return
	 */
	public static String getRarEntryList(String rarFile, String lineSplitChar) {
		return getRarEntryList(new File(rarFile), lineSplitChar);
	}
	
	/**
	 * 获取rar文件列表。默认使用回车符分割
	 * @param rarFile
	 * @return
	 */
	public static String getRarEntryList(String rarFile) {
		return getRarEntryList(new File(rarFile), ENTER);
	}
	
	/** 打印用WinZip压缩的文档 */
	
	/**
	 * 获取zip文件列表
	 * @param zipFile
	 * @param lineSplitChar
	 * @return
	 */
	public static String getZipEntryList(File zipFile, String lineSplitChar) {
		ZipFile archive = null;
		try {
			archive = new ZipFile(zipFile);
			StringBuilder entryString = new StringBuilder();
			
			Enumeration<ZipEntry> entries = archive.getEntries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				String fileName = entry.getName();
				
				entryString.append(fileName).append(lineSplitChar);
			}
			
			return entryString.toString();
		} catch (Exception e) {
			throw new RuntimeException("getZipEntryList error.", e);
		} finally {
			try {
				archive.close();
			} catch (IOException e) {
				throw new RuntimeException("getZipEntryList archive close error.", e);
			}
		}

	}
	
	/**
	 * 获取zip文件列表。默认使用回车符分割
	 * @param zipFile
	 * @return
	 */
	public static String getZipEntryList(File zipFile) {
		return getZipEntryList(zipFile, ENTER);
	}
	
	/**
	 * 获取zip文件列表
	 * @param zipFile
	 * @param lineSplitChar
	 * @return
	 */
	public static String getZipEntryList(String zipFile, String lineSplitChar) {
		return getZipEntryList(new File(zipFile), lineSplitChar);
	}
	
	/**
	 * 获取zip文件列表。默认使用回车符分割
	 * @param zipFile
	 * @return
	 */
	public static String getZipEntryList(String zipFile) {
		return getZipEntryList(new File(zipFile), ENTER);
	}
	
	
	
	/**
	 * 将目录打包为zip文件
	 * @param source 源目录
	 * @param target 目标地址。包含文件名
	 */
	public static void zip(String source, String target){
		File sourceFile = new File(source);
		if(sourceFile.exists()){
			IOSystem.makeDir(target);
			try {
				FileOutputStream outputStream;
				outputStream = new FileOutputStream(target);
				ZipOutputStream zipOut = new ZipOutputStream(outputStream);
				
				zip(zipOut, sourceFile, "");
				
				zipOut.flush();
				zipOut.close();
			} catch (Exception e) {
				throw new RuntimeException("zip error! source=" + source + ", target=" + target, e);
			}
			
		}
	}
	
	private static void zip(ZipOutputStream zipOut, File file, String dir) throws IOException{
		
		if(file.isDirectory()){
			if(AssertHelper.notEmpty(dir)){
				dir += "/";
				zipOut.putNextEntry(new ZipEntry(dir));
			}
			
			for(File childFile : file.listFiles()){
				zip(zipOut, childFile, dir + childFile.getName());
			}
		} else {
			zipOut.putNextEntry(new ZipEntry(dir));
			zipOut.write(IOSystem.readToBytes(new FileInputStream(file)));
		}
	}
}
