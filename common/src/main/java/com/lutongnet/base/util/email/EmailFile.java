package com.lutongnet.base.util.email;

import java.io.File;

/**
 * Email 附件
 * @author tianjp
 *
 */
public class EmailFile {

	private String	conentId;
	private String	filePath;
	private File	file;

	public EmailFile(File file) {
		this.file = file;
	}

	public EmailFile(String filePath) {
		this.filePath = filePath;
	}

	public EmailFile(String conentId, String filePath) {
		this(filePath);
		this.conentId = conentId;
	}

	public EmailFile(String conentId, File file) {
		this(file);
		this.conentId = conentId;
	}

	public String getConentId() {
		return conentId;
	}

	public void setConentId(String conentId) {
		this.conentId = conentId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "EmailFile [conentId=" + conentId + ", filePath=" + filePath + ", file=" + file + "]";
	}
}
