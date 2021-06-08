package com.dh.server;

import com.dh.constant.WSConstant;
import com.dh.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import com.alibaba.fastjson.JSONObject;
import com.dh.model.Employee;
import com.dh.util.JSONUtils;
import com.dh.service.RoomService;
import com.dh.service.EmployeeService;
import com.dh.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.dh.util.RedisUtil;
import com.dh.dao.EmployeeMapper;


@ServerEndpoint(value = "/socketServer/{token}")
@Component
public class SocketServer {

	private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);


//	private static CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();


	// 保存所有Client的WebSocket会话实例:
	private static Map<Integer, Client> socketServers = new ConcurrentHashMap<>();

	private static Map<String, Integer> sessionIds = new ConcurrentHashMap<>();

	/**
	 *
	 * websocket封装的session,信息推送，就是通过它来信息推送
	 */
	private Session session;

	@Resource
	private RedisUtil redisUtil;

//	private static RoomService roomService;
//
//	@Autowired
//	public static void setRoomService(RoomService roomService) {
//		SocketServer.roomService = roomService;
//	}

	private static ApplicationContext applicationContext;
	private  RoomService roomService;
	private  EmployeeService employeeService;

	public static void setApplicationContext(ApplicationContext applicationContext){
		SocketServer.applicationContext = applicationContext;
	}
	/**
	 *
	 * 用户连接时触发，我们将其添加到
	 * 保存客户端连接信息的socketServers中
	 *
	 * @param session
	 * @param userName
	 */
	@OnOpen
	public void open(Session session,@PathParam(value="token")String token){

		roomService = applicationContext.getBean(RoomService.class);
		employeeService = applicationContext.getBean(EmployeeService.class);
//		redisUtil = applicationContext.getBean(RedisUtil.class);

		Employee emp = employeeService.getEmployeeInfoByToken(token);

		if(emp == null || emp.getEmployeeId() == null || emp.getEmployeeId() < 1){
			JSONObject jo = JSONUtils.WebErrorResult("鉴权失败", JSONUtils.Code.UNKNOWN );
			String message =  jo.toJSONString();
			session.getAsyncRemote().sendText(message);
			logger.info("客户端:【{}】token验证失败",token);
			if (session.isOpen()){
				try {
					// 关闭连接
//					CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.CLOSE_UNSUPPORTED,"鉴权失败！");
					CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE,"鉴权失败！");
					session.close(closeReason);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			int id = emp.getEmployeeId();
			this.session = session;
			socketServers.put(id,new Client(token,session,id));
			sessionIds.put(session.getId(),id);
			logger.info("客户端:【{}】连接成功",token);
		}
	}

	/**
	 *
	 * 收到客户端发送信息时触发
	 * 我们将其推送给客户端
	 * 其实也就是服务端本身，为了达到前端聊天效果才这么做的
	 *
	 * @param message
	 */
	@OnMessage
	public void onMessage(String message){

//		Client client = socketServers.stream().filter( cli -> cli.getSession() == session)
//				.collect(Collectors.toList()).get(0);
		logger.info("send message : {}",message);

		int id = sessionIds.get(session.getId());

		Client client = socketServers.get(id);

		if(message.equals("ping")){
			sendMessage("ping",id);
			return;
		}

		JSONObject json = JSONUtils.stringToObj(message);

//		String data = json.getString("data");

//		switch(json.getString("act")){
//			case WSConstant.CREATE_ROOM:
//				roomService.createRoom(data,client);
//				break;
//			case WSConstant.JOIN_ROOM:
//				roomService.joinRoom(data,client);
//				break;
//			case WSConstant.LEAVE_ROOM:
//				roomService.leaveRoom(client);
//				break;
//			case WSConstant.MATCH:
//				roomService.match(client);
//				break;
//			case WSConstant.CANCEL_MATCH:
//				roomService.cancelMatch(client);
//				break;
//		}

//		sendMessage(client.getToken()+"***action:"+ json.getString("act") + "***message:" + json.getString("msg")   , client.getToken());

//		logger.info("客户端:【{}】发送信息:{}",client.getToken(),message);
	}

	/**
	 *
	 * 连接关闭触发，通过sessionId来移除
	 * socketServers中客户端连接信息
	 */
	@OnClose
	public void onClose(){

		int id = sessionIds.get(session.getId());
		Client client = socketServers.get(id);
		logger.info("客户端:【{}】断开连接",client.getId());
		socketServers.remove(id);
		sessionIds.remove(session.getId());
		logger.info("当前socketsession:【{}】",socketServers.toString());
		logger.info("当前sessionids:【{}】",sessionIds.toString());

//		socketServers.forEach(client ->{
//			if (client.getSession().getId().equals(session.getId())) {
//
//				logger.info("客户端:【{}】断开连接",client.getToken());
//				socketServers.remove(client);
//
//			}
//		});
	}

	/**
	 *
	 * 发生错误时触发
	 * @param error
	 */
    @OnError
    public void onError(Throwable error) {

    	if(session.getId() == null){
    		return;
		}

		int id = sessionIds.get(session.getId());

		Client client = socketServers.get(id);
		logger.info("客户端:【{}】发生异常",client.getId());
		socketServers.remove(id);
		sessionIds.remove(session.getId());

		logger.info("当前socketsession:【{}】",socketServers.toString());
		logger.info("当前sessionids:【{}】",sessionIds.toString());

		error.printStackTrace();

//		socketServers.forEach(client ->{
//			if (client.getSession().getId().equals(session.getId())) {
//				socketServers.remove(client);
//				logger.error("客户端:【{}】发生异常",client.getToken());
//				error.printStackTrace();
//			}
//		});
    }

	/**
	 *
	 * 信息发送的方法，通过客户端的userName
	 * 拿到其对应的session，调用信息推送的方法
	 * @param message
	 * @param userName
	 */
	public synchronized static void sendMessage(String message,int id) {
//		socketServers.forEach(client ->{
//			if (token.equals(client.getToken())) {
//				try {
//					client.getSession().getBasicRemote().sendText(message);
//
//					logger.info("服务端推送给客户端 :【{}】",client.getToken(),message);
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
		try {
			if(socketServers.get(id) == null){
				logger.info("已离线 id:【{}】",id);
			} else {
				socketServers.get(id).getSession().getBasicRemote().sendText(message);
				logger.info("服务端推送给客户端 :【{}】{}",socketServers.get(id).getId(),message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 信息发送的方法，通过客户端的userName
	 * 拿到其对应的session，调用信息推送的方法
	 * @param message
	 * @param userName
	 */
	public synchronized static void sendJoMessage(JSONObject jo,int id) {

		String message =  jo.toJSONString();

		try {
			socketServers.get(id).getSession().getBasicRemote().sendText(message);
			logger.info("服务端推送给客户端 :【{}】",socketServers.get(id).getId(),message);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized static void sendJoMessageToMany(JSONObject jo,List<Integer> ids) {
		String message =  jo.toJSONString();
		for (int id : ids) {
			logger.info("noyify id:{} message:{}",id,message);
			sendMessage(message,id);
		}
	}

	/**
	 *
	 * 获取服务端当前客户端的连接数量，
	 * 因为服务端本身也作为客户端接受信息，
	 * 所以连接总数还要减去服务端
	 * 本身的一个连接数
	 *
	 * 这里运用三元运算符是因为客户端第一次在加载的时候
	 * 客户端本身也没有进行连接，-1 就会出现总数为-1的情况，
	 * 这里主要就是为了避免出现连接数为-1的情况
	 *
	 * @return
	 */
	public synchronized static int getOnlineNum(){
		return 1;
//		return socketServers.stream().filter(client -> !client.getToken().equals(SYS_USERNAME))
//				.collect(Collectors.toList()).size();
	}

	/**
	 *
	 * 获取在线用户名，前端界面需要用到
	 * @return
	 */
	public synchronized static List<String> getOnlineUsers(){
		return null;
//		List<String> onlineUsers = socketServers.stream()
//				.filter(client -> !client.getToken().equals(SYS_USERNAME))
//				.map(client -> client.getToken())
//				.collect(Collectors.toList());
//
//	    return onlineUsers;
	}

	/**
	 *
	 * 信息群发，我们要排除服务端自己不接收到推送信息
	 * 所以我们在发送的时候将服务端排除掉
	 * @param message
	 */
	public synchronized static void sendAll(String message) {
		//群发，不能发送给服务端自己
//		socketServers.stream().filter(cli -> cli.getToken() != SYS_USERNAME)
//				.forEach(client -> {
//			try {
//				client.getSession().getBasicRemote().sendText(message);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
//
//		logger.info("服务端推送给所有客户端 :【{}】",message);
	}

	/**
	 *
	 * 多个人发送给指定的几个用户
	 * @param message
	 * @param persons
	 */
	public synchronized static void SendMany(String message,int [] ids) {
		for (int id : ids) {
			sendMessage(message,id);
		}
	}
}
