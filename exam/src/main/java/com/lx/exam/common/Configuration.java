package com.lx.exam.common;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	private static Map<String,String> config=new HashMap<String,String>();
	public static String get(String key){
		return config.get(key);
	}
}
