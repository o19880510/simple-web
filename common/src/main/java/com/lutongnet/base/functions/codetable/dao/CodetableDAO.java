package com.lutongnet.base.functions.codetable.dao;

import java.util.Map;

import com.lutongnet.base.functions.codetable.model.vo.CodeTableConfigValue;

public interface CodetableDAO {

	public abstract Map<String, String> get(CodeTableConfigValue configValue);

}