package woo.study.web.common.functions.codetable.dao;

import java.util.Map;

import woo.study.web.common.functions.codetable.model.vo.CodeTableConfigValue;

public interface CodetableDAO {

	public abstract Map<String, String> get(CodeTableConfigValue configValue);

}