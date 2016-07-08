package com.lutongnet.base.util.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lutongnet.base.util.parse.data.Adaptable;
import com.lutongnet.base.util.parse.data.DataProducer;
import com.lutongnet.base.util.parse.data.DefaultDataAdapter;

public class ListParser extends CommonMapParser implements DataProducer, Adaptable {

	private Object	tempObj;
	private String	name;

	public ListParser() {
		this.name = "objects";
	}

	public ListParser(String name) {
		this.name = name;
	}

	public ListParser(List<? extends Object> list) {
		this();
		dataAdapter = new DefaultDataAdapter(list);
	}

	public ListParser(List<? extends Object> list, String name) {
		this(list);
		this.name = name;
	}

	@Override
	public Map<String, Object> parse() {

		Map merMap = new HashMap();

		List<Map> objects = new ArrayList<Map>();
		List<Object> objectList = (List<Object>) getDataAdapter().getData();
		for (Object obj : objectList) {
			tempObj = obj;
			objects.add(super.parse());
		}

		merMap.put(name, objects);

		return merMap;
	}

	@Override
	public Object data() {
		return this.tempObj;
	}

}
