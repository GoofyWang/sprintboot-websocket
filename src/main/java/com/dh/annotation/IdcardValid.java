package com.dh.annotation;

import com.dh.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证校验
 */
public class IdcardValid {

	private final static Map<String, String> zoneNum = new HashMap<>();
	private final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    private final static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
	
	static {
		zoneNum.put("11", "北京");
        zoneNum.put("12", "天津");
        zoneNum.put("13", "河北");
        zoneNum.put("14", "山西");
        zoneNum.put("15", "内蒙古");
        zoneNum.put("21", "辽宁");
        zoneNum.put("22", "吉林");
        zoneNum.put("23", "黑龙江");
        zoneNum.put("31", "上海");
        zoneNum.put("32", "江苏");
        zoneNum.put("33", "浙江");
        zoneNum.put("34", "安徽");
        zoneNum.put("35", "福建");
        zoneNum.put("36", "江西");
        zoneNum.put("37", "山东");
        zoneNum.put("41", "河南");
        zoneNum.put("42", "湖北");
        zoneNum.put("43", "湖南");
        zoneNum.put("44", "广东");
        zoneNum.put("45", "广西");
        zoneNum.put("46", "海南");
        zoneNum.put("50", "重庆");
        zoneNum.put("51", "四川");
        zoneNum.put("52", "贵州");
        zoneNum.put("53", "云南");
        zoneNum.put("54", "西藏");
        zoneNum.put("61", "陕西");
        zoneNum.put("62", "甘肃");
        zoneNum.put("63", "青海");
        zoneNum.put("64", "宁夏");
        zoneNum.put("65", "新疆");
        zoneNum.put("71", "台湾");
        zoneNum.put("81", "香港");
        zoneNum.put("82", "澳门");
        zoneNum.put("91", "外国");
	}
	
	/**
	 * 身份证号校验
	 * @param idcard
	 * @return
	 */
	public static boolean idcardCheck(String idcard){
		int idlen = idcard.length();
		if(idlen != 15 || idlen != 18){
			return false;
		}
		char[] arr = idcard.toUpperCase().toCharArray();
		int power = 0;
		int len = arr.length;
		for(int i = 0; i < len; i++){
			if(i == len - 1 && arr[i] == 'X'){
				break;
			}
			if(arr[i] < '0' || arr[i] > '9'){
				return false;
			}
			if(i < len -1){
				power += (arr[i] - '0') * POWER_LIST[i];
			}
		}
		//校验区码
		if(!zoneNum.containsKey(idcard.substring(0, 2))){
			return false;
		}
		//校验年份
		int year = 0;
		if(idlen == 15){
			year = getIdcardYear(idcard);
		}else{
			year = Integer.parseInt(idcard.substring(6,10));
		}
		if(year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)){
			return false;
		}
		//校验月份
		String month = idlen == 15 ? idcard.substring(8, 10) : idcard.substring(10, 12);  
		int imonth = Integer.parseInt(month);
		if(imonth < 1 || imonth > 12){
			return false;
		}
		//校验天数
		String day = idlen == 15 ? idcard.substring(10, 12) : idcard.substring(12, 14);  
		int iday = Integer.parseInt(day);
		if(iday < 1 || iday > 31){
			return false;
		}
		//校验“校验码”
		if(idlen == 15) return true;
		return arr[len - 1] == PARITYBIT[power%11];
	}
	
	/**
	 * 获取15位身份证年份
	 * @param idcard  身份证号
	 * @return
	 */
	private static int getIdcardYear(String idcard){
		//获取出生年月日
		String birthday = idcard.substring(6, 12);
		Date birthdate = DateUtil.StringToDate(birthday, "yyMMdd");
		return DateUtil.getYear(birthdate);
	}
	
}
