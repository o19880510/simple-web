package com.lutongnet.base.util.parse;

import java.util.Map;

import com.lutongnet.base.util.ResultMapUtils;
import com.lutongnet.base.util.parse.data.Adaptable;
import com.lutongnet.base.util.parse.data.DefaultDataAdapter;

public class ResultMapParser extends CommonMapParser implements Adaptable {

	public static CommonMapParser success() {
		return new ResultMapParser(ResultMapUtils.success());
	}

	public static CommonMapParser failture() {
		return new ResultMapParser(ResultMapUtils.failture());
	}

	public static CommonMapParser sysErr() {
		return new ResultMapParser(ResultMapUtils.sysErr());
	}

	public static CommonMapParser result(int errorCode) {
		return new ResultMapParser(ResultMapUtils.result(errorCode));
	}

	private ResultMapParser(Map result) {
		super.dataAdapter = new DefaultDataAdapter(result);
	}

	@Override
	public Map<String, Object> parse() {

		Map resultMap = (Map) getDataAdapter().getData();

		resultMap.putAll(super.parse());

		return resultMap;
	}

}
