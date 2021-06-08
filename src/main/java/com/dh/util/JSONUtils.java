package com.dh.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import java.util.Map;
import java.util.List;

/**
 * JSON工具类
 */
public class JSONUtils {
	
	/**
	 * 成功 (带条数)
	 * @param count 总条数
	 * @param data  
	 * @return
	 */
	public static<T> JSONObject successRespose(long count, List<T> data){
		JSONObject jo = new JSONObject();
		jo.put("code", Code.SUCCESS);
		jo.put("msg", "成功");
		jo.put("count", count);
		jo.put("data", data);
		return jo;
	}
	
	/**
	 * 成功
	 * @param data  返回数据
	 * @return
	 */
	public static JSONObject successRespose(Object data){
		JSONObject jo = new JSONObject();
		jo.put("code", Code.SUCCESS);
		jo.put("msg", "成功");
		jo.put("data", data);
		return jo;
	}
	
	/**
	 * 登录异常
	 * @param message  错误信息
	 * @return
	 */
	public static JSONObject loginFailure(String message){
		JSONObject jo = new JSONObject();
		jo.put("code", Code.ERRORLOGIN);
		jo.put("msg", message);
		jo.put("data", null);
		return jo;
	}
	
	/**
	 * 未授权
	 * @param data  授权路径
	 * @return
	 */
	public static JSONObject unauthorized(String data){
		JSONObject jo = new JSONObject();
		jo.put("code", Code.UNAUTHORIZED);
		jo.put("msg", "未授权");
		jo.put("data", data);
		return jo;
	}
	
	public static JSONObject emptyResponse(String message){
		JSONObject jo = new JSONObject();
		jo.put("code", Code.EMPTYPARAM);
		jo.put("msg", message);
		jo.put("data", null);
		return jo;
	}
	
	public static JSONObject doubleSumint(String msg){
		JSONObject jo = new JSONObject();
		jo.put("code", Code.DOUBLESUBMIT);
		jo.put("msg", msg);
		jo.put("data", null);
		return jo;
	}
	
	/**
	 * 系统异常
	 * @param message
	 * @return
	 */
	public static JSONObject systemErrorResponse(String message){
		JSONObject jo = new JSONObject();
		jo.put("code", Code.SYSTEMERROR);
		jo.put("msg", message);
		jo.put("data", null);
		return jo;
	}
	
	public static final class Code{
		/** 成功 */
		public static int SUCCESS = 200;
		public static int NOTIFYSUCCESS = 201;
		/** 系统错误 */
		public static int SYSTEMERROR =303;
		/** 必要参数为空*/
		public static int EMPTYPARAM = 301;
		/**重复提交*/
		public static int DOUBLESUBMIT = 405;
		/** 上传图片异常 */
		public static int FILEERROR = 506;
		/** 用户名或密码错误 */
		public static int ERRORLOGIN = 507;
		/**未授权 */
		public static int UNAUTHORIZED=508;

		/**用户检验失败 */
		public static int UNKNOWN=509;

		/**有用户加入房间**/
		public static int JOIN = 2001;
		/**有用户离开房间**/
		public static int LEAVE = 2002;
		/**房主离开房间**/
		public static int ROOTLEAVE = 2003;
		/**开始匹配**/
		public static int MATCH = 2004;
		/**取消匹配**/
		public static int CANCELMATCH = 2005;
		/**开始对战**/
		public static int FIGHT = 2006;

		/**比赛完成**/
		public static int MATCHCOMPLETE = 2007;

	}

	public static final class ErrorCode{
		/** 加入房间失败 */
		public static int JOINFAIL = 1001;

		/** 創建房间失败 */
		public static int CREATEFAIL = 1002;

		/** 匹配失败 */
		public static int MATCHFAIL = 1004;

		/** 离开失败 */
		public static int LEAVEFAIL = 1005;

		/** 获取试题失败 */
		public static int BANKFAIL = 1005;

		/** 答题失败 */
		public static int ANSWERFAIL = 1006;

		/** 离开失败 */
		public static int KICKFAIL = 1007;

		/** 已经在本房间内 */
		public static int ALREADYINROOM = 2001;

		/** 創建房间失败，已有房间 */
		public static int ALREADYCREATEROOM = 2002;

		/** 創建房间失败，房主已有房间 */
		public static int ALREADYINOTHERROOM = 2003;

		/** 加入房间，用户已有其他房间 */
		public static int JOINROOMHASOTHER = 2004;

		/** 房间不存在 */
		public static int ROOMNOTEXIST = 2005;
		/** 房间等待中 */
		public static int ROOMREADY = 2006;
		/** 房间匹配中 */
		public static int ROOMMATCH = 2007;
		/** 房间对战中 */
		public static int ROOMFIGHT = 2008;

		/** 对战匹配未开放 */
		public static int ROOMFIGHTNOTENABLE = 2010;

		/** 无开设房间权限 */
		public static int NOROOMPERMISSION = 2011;

		/** 部门房间重复 */
		public static int ROOMDUPLICATE = 2012;

	}

	public static JSONObject stringToObj(String s){
		JSONObject jo = JSON.parseObject(s);
		return jo;
	}

	public static JSONObject JSONObjectPutList(JSONObject jo, List list){
		String jsonStr = JSONObject.toJSONString( list );
		jo.put("data",jsonStr);
		return jo;
	}

	public static JSONObject WSResult(String act, String msg){
		JSONObject jo = new JSONObject();
		jo.put("act", act);
		jo.put("msg", msg);
		jo.put("code", Code.SUCCESS);
		return jo;
	}


	public static JSONObject WSNotifyResult(String act, String msg, int code ){
		JSONObject jo = new JSONObject();
		jo.put("act", act);
		jo.put("msg", msg);
		jo.put("code", code);
		return jo;
	}

	public static JSONObject WSErrorResult(String act, String msg, int errorCode){
		JSONObject jo = new JSONObject();
		jo.put("act", act);
		jo.put("msg", msg);
		jo.put("code", errorCode);
		return jo;
	}

	public static JSONObject WebResult(String msg){
		JSONObject jo = new JSONObject();
		jo.put("msg", msg);
		jo.put("code", Code.SUCCESS);
		return jo;
	}

	public static JSONObject WebErrorResult(String msg, int errorCode){
		JSONObject jo = new JSONObject();
		jo.put("msg", msg);
		jo.put("code", errorCode);
		return jo;
	}

	public static JSONObject VerifyErrorResult(){
		JSONObject jo = new JSONObject();
		jo.put("msg", "用户验证失败");
		jo.put("code", Code.UNKNOWN);
		return jo;
	}


}
