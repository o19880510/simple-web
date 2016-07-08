package com.lutongnet.system.listener.commander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Commander implements Runnable{
	private Logger log = LoggerFactory.getLogger(Commander.class);
	private Map<String, Action> commanderMap = new HashMap<String, Action>();
	private String temp = "AAAAAAAAAAAAAAAaa";
	public Commander(){
		commanderMap.put("exit", new ExitAction());
	}
	
	@Override
	public void run() {
		
		while(true){
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String readLine = null;
			try {
				while((readLine = reader.readLine())!=null){
//					log.debug(
					System.out.println("u input:" + readLine);
					System.out.println("u input:" + Arrays.toString(readLine.split("\\x20+")));
					System.out.println("log:" + log);
					System.out.println("commanderMap:" + commanderMap);
					System.out.println("temp:" + temp);
					Action action = commanderMap.get(readLine);
					if (action != null) {
						action.dos(readLine.split("\\x20+"));
					}
				}
			} catch (IOException e) {
				log.error("read input error!", e);
			}
		}
	}
	
}
