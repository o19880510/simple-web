package woo.study.web.common.util.filesync.path;

import woo.study.web.common.util.IOSystem;

public class FilePathWebRoot implements FilePath {
	public String getPath() {
		String classpath = IOSystem.getClasspath();
		return classpath.substring(0, classpath.indexOf("/WEB-INF/classes"));
	}
}
