package woo.study.web.common.util;

import java.util.Map;

import woo.study.web.common.util.IOSystem;
import woo.study.web.common.util.JSON2Helper;

public class JSON2HelperTest {
	
	public static void main(String[] args) {
		String json = IOSystem.readToString(JSON2HelperTest.class.getResourceAsStream("JSONHelperTest.txt"));
		
		Map map = JSON2Helper.toObject(json, Map.class);
		System.out.println(map);
		
	}
}
