package com.lutongnet.base.util.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lutongnet.base.util.parse.data.Adaptable;
import com.lutongnet.base.util.parse.data.DataAdapter;
import com.lutongnet.base.util.parse.data.DataProducer;
import com.lutongnet.base.util.parse.data.DefaultDataAdapter;

public abstract class CommonMapParser implements MapParser {

	private List<MapParser>	mapParserList;
	protected DataAdapter dataAdapter;
	
	public CommonMapParser() {
		super();
		mapParserList = new ArrayList<MapParser>();
		dataAdapter = new DefaultDataAdapter();
	}
	
	public CommonMapParser(Object object) {
		this();
		dataAdapter = new DefaultDataAdapter(object);
	}
	public CommonMapParser(DataAdapter dataAdapter) {
		this();
		this.dataAdapter = dataAdapter;
	}

	public Map<String, Object> parse() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		for (MapParser parser : mapParserList) {
			
			if(this instanceof DataProducer && parser instanceof Adaptable){
				Object value = ((DataProducer)this).data();
				
				Adaptable adaptable = (Adaptable) parser;
				adaptable.getDataAdapter().setData(value);
			}
			
			map.putAll(parser.parse());
		}
		return map;
	}

	public CommonMapParser append(MapParser mapParser) {
		this.mapParserList.add(mapParser);
		return this;
	}
	
	public DataAdapter getDataAdapter() {
		return dataAdapter;
	}
	
}
