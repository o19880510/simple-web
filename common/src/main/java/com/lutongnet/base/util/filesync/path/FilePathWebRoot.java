package com.lutongnet.base.util.filesync.path;

import com.lutongnet.base.util.IOSystem;

public class FilePathWebRoot implements FilePath {
	public String getPath() {
		String classpath = IOSystem.getClasspath();
		return classpath.substring(0, classpath.indexOf("/WEB-INF/classes"));
	}
}
