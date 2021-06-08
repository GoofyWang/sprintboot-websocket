package com.dh.util;

/**
 * 系统常量类
 */
public class SystemConstants {
	
	/**7天*/
	public static final long EXPIRE_TIME_DAY_7 = 7 * 24 * 60 * 60L;
	
	/**2小时*/
	public static final long EXPIRE_TIME_2 = 2 * 60 * 60L;

	/**是  可用*/
	public static final String Y = "Y";
	
	/**否  不可用*/
	public static final String N = "N";
	
	public final static String HEADERS_X_USER = "x-user";
    public final static String HEADERS_X_NONCE = "x-nonce";
    public final static String HEADERS_X_DATE = "x-date";
    public final static String HEADERS_X_SIGNATURE = "x-signature";
    
public static class Code{
    	
    	/** 成功 */
		public static int SUCCESS = 200;
		/** 参数不正确 */
		public static int ERRORPARAM = 301;
		/** 系统错误 */
		public static int SYSTEMERROR =303;
		/**无效参数*/
		public static int INVALIDPARAM = 400;
		/**找不到资源路径*/
		public static int INVALIDPATH = 404;
		/**重复提交*/
		public static int DOUBLESUBMIT = 405;
		/** 上传图片异常 */
		public static int FILEERROR = 506;
		/** 用户名或密码错误 */
		public static int ERRORLOGIN = 507;
		/**未登录 */
		public static int LOGINFAILURE=508;
		/** 登录信息错误 */
		public static int LOGINERROR = 509;
		/**实名认证失败*/
		public static int VERIFYERROR = 606;
    }
	
}
