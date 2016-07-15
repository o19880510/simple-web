package woo.study.web.common.functions.codetable.datacenter;


import java.util.Map;

import woo.study.web.common.functions.datacenter.DataManagementHelper;
import woo.study.web.common.functions.datacenter.loader.DataLoader;
import woo.study.web.common.util.AssertHelper;

public class CodetableDataManagement {

	private DataLoader[]						simpleMapLoaders;
	private DataLoader[]						typedMapLoader;

	public Map<String, String> get(String key){
		Map<String, String> resultDatas = getFromSimple(key);
		if(AssertHelper.isEmpty(resultDatas)){
			resultDatas = getFromTyped(key);
		}
		return resultDatas;
	}
	
	private Map<String, String> getFromSimple(String key){
		
		for (DataLoader loader : getSimpleMapLoaders()) {
			String dataName = loader.getDataName();
			if(AssertHelper.notEmpty(dataName) && dataName.equals(key)){
				Map<String, String> data = DataManagementHelper.getDataContainer().get(dataName, Map.class);
				return data;
			}
		}
		
		return null;
	}
	
	private Map<String, String> getFromTyped(String key){
		
		for (DataLoader loader : getTypedMapLoader()) {
			String dataName = loader.getDataName();
			Map<String, Map<String, String>> data = DataManagementHelper.getDataContainer().get(dataName, Map.class);
			Map<String, String> resultDatas = data.get(key);
			if(AssertHelper.notEmpty(resultDatas)){
				return resultDatas;
			}
		}
		
		return null;
	}
	
	public DataLoader[] getSimpleMapLoaders() {
		return simpleMapLoaders;
	}

	public void setSimpleMapLoaders(DataLoader[] simpleMapLoaders) {
		this.simpleMapLoaders = simpleMapLoaders;
	}

	public DataLoader[] getTypedMapLoader() {
		return typedMapLoader;
	}

	public void setTypedMapLoader(DataLoader[] typedMapLoader) {
		this.typedMapLoader = typedMapLoader;
	}
}
