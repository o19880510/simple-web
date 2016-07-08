package com.lutongnet.base.functions.datacenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lutongnet.base.functions.datacenter.loader.CommonDataLoader;
import com.lutongnet.base.functions.datacenter.loader.DataLoader;
import com.lutongnet.base.util.AssertHelper;


public class DataLoadManagement implements DataLoadable, ContainerAvailable {
	
	private static Logger log = LoggerFactory.getLogger(DataLoadManagement.class);
	
	private List<DataLoader> dataLoaderList ;
	private Map<String, DataLoader> dataLoaderMap;
	
	private DataContainerNotify dataContainerNotify;

	public DataLoadManagement(){
		dataLoaderMap = new HashMap<String, DataLoader>();
	}
	
	@Override
	public void load(String... dataNames) {
		
		Set<String> alreadyLoadDataNameSet = new HashSet<String>();
		
		for(String dataName : dataNames){
			List<String> depList = findDeps(dataName);
			
			log.debug("DataLoadManagement load depList=" + depList);
			for(int i = depList.size() -1 ; i>=0 ;i--){
				
				String tempDataName = depList.get(i);
				if(!alreadyLoadDataNameSet.contains(tempDataName)){
					log.debug("DataLoadManagement load tempDataName=" + tempDataName);
					loadOne(tempDataName);
					alreadyLoadDataNameSet.add(tempDataName);
				}
			}
		}
	}
	

	@Override
	public void loadAll() {
		
		for(DataLoader dataLoader:dataLoaderList){
			
			CommonDataLoader commonDataLoader = (CommonDataLoader)dataLoader;
			commonDataLoader.setDataContainer((DataContainer)dataContainerNotify);
			
			Object value = commonDataLoader.loading();
			String key = commonDataLoader.getDataName();
			
			Map<String, Object> datasMap = new HashMap<String, Object>();
			datasMap.put(key, value);
			notifyCOntainer(datasMap);

			dataLoaderMap.put(key, dataLoader);
		}
	}
	
	private List<String> findDeps(String dataName){
		
		List<String> depList = new ArrayList<String>();
		
		String tempDataName = dataName;
		while(true){
			if(AssertHelper.isEmpty(tempDataName)){
				break;
			}
			CommonDataLoader commonDataLoader = (CommonDataLoader)dataLoaderMap.get(tempDataName);
			if(commonDataLoader == null){
				break;
			}else{
				depList.add(tempDataName);
				tempDataName = commonDataLoader.getDependDataName();
			}
		}
		log.debug("DataLoadManagement findDeps depList=" + depList);
		return depList;
	}
	
	private void loadOne(String dataName){
		CommonDataLoader commonDataLoader = (CommonDataLoader)dataLoaderMap.get(dataName);
		Object value = commonDataLoader.loading();
		
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put(dataName, value);
		notifyCOntainer(tempMap);
	}
	
	private void notifyCOntainer(Map<String, Object> dataMap){
		dataContainerNotify.notify(dataMap);
	}
	
	private void initHelper(){
		DataManagementHelper.init((DataContainer)this.dataContainerNotify);
	}
	
	public void setDataLoaderList(List<DataLoader> dataLoaderList) {
		this.dataLoaderList = dataLoaderList;
	}
	
	public List<DataLoader> getDataLoaderList() {
		return dataLoaderList;
	}

	public Map<String, DataLoader> getDataLoaderMap() {
		return dataLoaderMap;
	}

	public void setDataLoaderMap(Map<String, DataLoader> dataLoaderMap) {
		this.dataLoaderMap = dataLoaderMap;
	}

	public DataContainerNotify getDataContainerNotify() {
		return dataContainerNotify;
	}

	public void setDataContainerNotify(DataContainerNotify dataContainerNotify) {
		this.dataContainerNotify = dataContainerNotify;
		initHelper();
	}

	@Override
	public DataContainer getDataContainer() {
		return (DataContainer) dataContainerNotify;
	}
	
}
