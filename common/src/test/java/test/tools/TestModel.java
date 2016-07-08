package test.tools;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

public class TestModel implements org.springframework.ui.Model {

	private Map<String, Object>	attrMap	= new HashMap<String, Object>();

	public Model addAttribute(String paramString, Object paramObject) {
		attrMap.put(paramString, paramObject);
		return this;
	}

	public Model addAttribute(Object paramObject) {
		return null;
	}

	public Model addAllAttributes(Collection<?> paramCollection) {
		return null;
	}

	public Model addAllAttributes(Map<String, ?> paramMap) {
		return null;
	}

	public Model mergeAttributes(Map<String, ?> paramMap) {
		return null;
	}

	public boolean containsAttribute(String paramString) {
		return false;
	}

	public Map<String, Object> asMap() {
		return attrMap;
	}

}
