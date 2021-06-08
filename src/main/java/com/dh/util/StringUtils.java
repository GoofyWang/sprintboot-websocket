package com.dh.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;


/**
 * 字符串工具类
 */
public class StringUtils {
	
	public static String lowerFirst(String str){
		if(isEmptyString(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(isEmptyString(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isEmptyString(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(val.toString().replaceAll("\\s*", ""));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 获得id
	 * @return
	 */
	public static String randomUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 判断字符串是否为空
	 * 为空返回真,非空返回假
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmptyString(String str) {
		if (str == null || str.length() <= 0 || "".equals(str)
				|| str.replaceAll("\\s*", "").length() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * printWrite在页面打印输出值
	 * @param response
	 * @param param
	 */
	public static void printWrite(HttpServletResponse response,String param) {
		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			pw = response.getWriter();
			pw.write(param);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw != null){
				pw.close();
			}
		}
	}
	
	/**
	 * 生成唯一订单号（16位）
	 * @return
	 */
	public static String getOrderIdByUUId() {
		int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {//有可能是负数
			hashCodeV = - hashCodeV;
		}
		// 0 代表前面补充0     
		// 4 代表长度为4     
		// d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}




}
