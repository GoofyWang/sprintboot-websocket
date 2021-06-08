package com.dh.annotation;

/**
 * 参数校验类型
 */
public class ValidType {
	
	/**身份证号*/
	public static final String idcard = "idcard";
	/**手机号*/
	public static final String mobile = "^1[3-9]{1}\\d{9}";
	/**电话*/
	public static final String phone = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
	/**邮箱*/
	public static final String email = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	/**数字*/
	public static final String num = "[0-9]";
	/**中文*/
	public static final String ch = "[\u4e00-\u9fa5]";
	/**英文*/
	public static final String en = "[A-Za-z]";
	/**数字或英文*/
	public static final String num_en = "[A-Za-z0-9]";
	
}
