package com.dh.util;

import java.util.HashMap;
import java.util.Map;

/**
 * api类型限制
 */
public class ApiTypeUtils {

	/**默认类型*/
	public static int DEFAULTAUTH = 0;
	
	/**用户类型*/
	public static int USERAUTH = 1;
	
	/**token类型*/
	public static int TOKENAUTH = 2;
	
	/**无*/
	public static int NOAUTH = 3;
	
	private static Map<String, Integer> methodMap = null;
	
	/**初始化方法校验类型*/
	static{
		methodMap = new HashMap<>();
		
		methodMap.put("aws/company/poc/add", DEFAULTAUTH);
	}
	
	/**
	 * 获取方法map
	 * @return
	 */
	public static Map<String, Integer> getMethodMap(){
		return methodMap;
	}
	
	/**
	 * 根据路径获取对应type
	 * @param path  请求方法
	 * @return
	 */
	public static int getRightType(String path){
		if(methodMap.containsKey(path)){
			return methodMap.get(path);
		}
		return DEFAULTAUTH;
	}
}
