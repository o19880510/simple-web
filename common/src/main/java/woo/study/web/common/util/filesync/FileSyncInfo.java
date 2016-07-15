package woo.study.web.common.util.filesync;

import java.io.Serializable;

public class FileSyncInfo implements Serializable{

	private static final long	serialVersionUID	= 1L;

	public static enum Status {
		EXIST, NOT_EXIST, UPDATE, REMOVE;
	}
	
	private String	relativePath;
	private String	hash;
	private long	lastModified;
	private Status	status;
	

	public FileSyncInfo() {
		super();
	}


	public FileSyncInfo(String relativePath, String hash, long lastModified) {
		this.relativePath = relativePath;
		this.hash = hash;
		this.lastModified = lastModified;
	}
	
	

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
	
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((relativePath == null) ? 0 : relativePath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileSyncInfo other = (FileSyncInfo) obj;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (relativePath == null) {
			if (other.relativePath != null)
				return false;
		} else if (!relativePath.equals(other.relativePath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileSyncInfo [relativePath=" + relativePath + ", hash=" + hash + ", lastModified=" + lastModified
				+ ", status=" + status + "]";
	}

}
