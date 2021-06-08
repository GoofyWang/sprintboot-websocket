package com.dh.service.impl;

import com.dh.constant.WSConstant;
import com.dh.dao.DepartmentMapper;
import com.dh.dao.DepartmentScoreLogMapper;
import com.dh.dao.DepartmentScoreMapper;
import com.dh.dao.PermissionMapper;
import com.dh.dao.RoomInfoMapper;
import com.dh.dao.RoomMapper;
import com.dh.dao.MatchLogMapper;
import com.dh.dao.AnswerMapper;
import com.dh.dao.FightLogMapper;
import com.dh.dao.SysConfigMapper;
import com.dh.model.Answer;
import com.dh.model.DepartmentScore;
import com.dh.model.DepartmentScoreLog;
import com.dh.model.Employee;
import com.dh.model.FightLog;
import com.dh.model.MatchLog;
import com.dh.model.Permission;
import com.dh.model.SysConfig;
import com.dh.model.dto.ItemFightAnswerDTO;
import com.dh.model.vo.DepartmentInfoVO;
import com.dh.model.vo.DepartmentScoreListVO;
import com.dh.model.vo.RoomInfoVO;
import com.dh.server.SocketServer;
import com.dh.service.RoomService;
import com.dh.util.JSONUtils;
import com.dh.dao.ActivityItemBankMapper;
import com.dh.model.vo.ActivityItemBankVO;
import com.dh.model.Room;
import com.dh.model.RoomInfo;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dh.entity.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Date;
import java.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {

	private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

	@Autowired
	private RoomMapper roomMapper;

	@Autowired
	private RoomInfoMapper roomInfoMapper;

	@Autowired
	private MatchLogMapper matchLogMapper;

	@Autowired
	private FightLogMapper fightLogMapper;

	@Autowired
	private AnswerMapper answerMapper;

	@Autowired
	private ActivityItemBankMapper aibMapper;

	@Autowired
	private DepartmentScoreMapper departmentScoreMapper;

	@Autowired
	private DepartmentScoreLogMapper departmentScoreLogMapper;

	@Autowired
	private SocketServer socketServer;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private SysConfigMapper sysConfigMapper;

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public JSONObject webGetRoomStatus(Integer RoomId) {
		Room room = roomMapper.getRoomStatus(RoomId);
		JSONObject jo = new JSONObject();
		List<RoomInfoVO> roomInfoVOs = new ArrayList<>();
		Map<String,Object> data = new HashMap<>();
		if(room == null){
			jo = JSONUtils.WebErrorResult( "房间不存在", JSONUtils.ErrorCode.ROOMNOTEXIST );
		} else {
			switch(room.getStatus()){
				case WSConstant.ROOM_STATUS_LEAVE:
					jo = JSONUtils.WebErrorResult( "房间不存在", JSONUtils.ErrorCode.ROOMNOTEXIST );
					break;
				case WSConstant.ROOM_STATUS_READY:
					jo = JSONUtils.WebErrorResult( "房间等待中", JSONUtils.ErrorCode.ROOMREADY );
					//添加房间信息
					roomInfoVOs = roomInfoMapper.getRoomInfoList(RoomId);
					data.put("room",room);
					data.put("list",roomInfoVOs);
					jo.put("data",data);
					break;
				case WSConstant.ROOM_STATUS_MATCH:
					jo = JSONUtils.WebErrorResult( "房间匹配中", JSONUtils.ErrorCode.ROOMMATCH );
					roomInfoVOs = roomInfoMapper.getRoomInfoList(RoomId);
					data.put("room",room);
					data.put("list",roomInfoVOs);
					jo.put("data",data);
					break;
				case WSConstant.ROOM_STATUS_FIGHT:
					jo = JSONUtils.WebErrorResult( "房间对战中", JSONUtils.ErrorCode.ROOMFIGHT );
					roomInfoVOs = roomInfoMapper.getRoomInfoList(RoomId);
					data.put("room",room);
					data.put("list",roomInfoVOs);
					jo.put("data",data);
					break;
			}
		}
		return jo;
	}


	@Override
	public JSONObject webCreateRoom(Employee employee, Integer type) {

		if(!type.equals(WSConstant.ROOM_FIGHT)){
			type = WSConstant.ROOM_PRACTICE;
		}

		if(type.equals(WSConstant.ROOM_FIGHT)){

			//检查权限
			Permission permission = new Permission();
			permission.setEmployeeId(employee.getEmployeeId());
			permission.setPermission("room_permission");
			permission = permissionMapper.selectPermissionByPermission(permission);

			JSONObject jo = new JSONObject();

			if(permission == null){
				jo = JSONUtils.WebErrorResult("无开设房间权限", JSONUtils.ErrorCode.NOROOMPERMISSION );
				return jo;
			}


			SysConfig sysConfig = sysConfigMapper.selectByName("open_time");

			String str = sysConfig.getValue();

			JSONObject strtojo = JSONObject.parseObject(str);
			Calendar calendar = Calendar.getInstance();
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			int config_week = Integer.parseInt(strtojo.getString("week"));


			if( config_week != week ){
				jo = JSONUtils.WebErrorResult("未到对战赛开放时间", JSONUtils.ErrorCode.ROOMFIGHTNOTENABLE );
				return jo;
			}

			int hour = calendar.get(Calendar.HOUR);
			int AM_PM = calendar.get(Calendar.AM_PM);
			if(AM_PM == 1){
				hour = hour + 12;
			}

			int start = Integer.parseInt(strtojo.getString("start"));
			int end = Integer.parseInt(strtojo.getString("end"));


			if( hour < start || hour > end ){
				jo = JSONUtils.WebErrorResult("未到对战赛开放时间", JSONUtils.ErrorCode.ROOMFIGHTNOTENABLE );
				return jo;
			}


			Room Droom = roomMapper.getRoomByDepartment(employee.getDepartmentId());

			if(Droom != null && !Droom.getOwnerId().equals(employee.getEmployeeId()) ){
				jo = JSONUtils.WebErrorResult("已有同部门房间", JSONUtils.ErrorCode.ROOMDUPLICATE );
				return jo;
			}

		}

		Room room = roomMapper.getRoomByOwnerId(employee.getEmployeeId());

		if(room != null){
			JSONObject jo = JSONUtils.WebErrorResult( WSConstant.ROOM_ALREADY_CREATE, JSONUtils.ErrorCode.ALREADYCREATEROOM );
			List<RoomInfoVO> roomInfoVOs = roomInfoMapper.getRoomInfoList(room.getRoomId());
			Map<String,Object> data = new HashMap<>();
			data.put("room",room);
			data.put("list",roomInfoVOs);
			jo.put("data",data);
			return jo;
		} else {
			room = new Room();
		}

		//查询进入房间记录
		RoomInfo roomInfo = roomInfoMapper.getRoomInfo(employee.getEmployeeId());

		if(roomInfo != null){
			JSONObject jo = JSONUtils.WebErrorResult( WSConstant.ROOM_ALREADY_CREATE, JSONUtils.ErrorCode.ALREADYINOTHERROOM );
			List<RoomInfoVO> roomInfoVOs = roomInfoMapper.getRoomInfoList(roomInfo.getRoomId());
			room = roomMapper.getRoom(roomInfo.getRoomId());
			Map<String,Object> data = new HashMap<>();
			data.put("room",room);
			data.put("list",roomInfoVOs);
			jo.put("data",data);
			return jo;
		}

		room.setOwnerId(employee.getEmployeeId());
		room.setNum(1);
		room.setType(type);
		room.setDepartment(employee.getDepartmentId());

		roomMapper.createRoom(room);

		if(room.getRoomId() == null){
			JSONObject jo = JSONUtils.WebErrorResult( WSConstant.CREATE_FAIL, JSONUtils.ErrorCode.CREATEFAIL );
			return jo;
		}

		//添加进入房间记录
		RoomInfo insert = new RoomInfo();
		insert.setRoomId(room.getRoomId());
		insert.setEmployeeId(employee.getEmployeeId());
		roomInfoMapper.insertInfo(insert);

		if(insert.getId() == null){
			roomMapper.deleteRoomById(room.getRoomId());
			JSONObject jo = JSONUtils.WebErrorResult(WSConstant.CREATE_FAIL, JSONUtils.ErrorCode.CREATEFAIL );
			return jo;
		}

		JSONObject jo = JSONUtils.WebResult("成功" );
		jo.put("data", room);
		return jo;
	}

	@Override
	public JSONObject webJoinRoom(Employee employee, Integer roomId) {

		Room room = roomMapper.getRoom(roomId);

		if(room == null){
			JSONObject jo = JSONUtils.WebErrorResult( WSConstant.ROOM_DOESNT_EXIST, JSONUtils.ErrorCode.JOINFAIL );
			return jo;
		}

		//查询进入房间记录
		RoomInfo roomInfo = roomInfoMapper.getRoomInfo(employee.getEmployeeId());

		if(roomInfo != null){
			if(roomInfo.getRoomId().equals(room.getRoomId())){
				JSONObject jo = JSONUtils.WebErrorResult( WSConstant.ROOM_ALREADY, JSONUtils.ErrorCode.ALREADYINROOM );
				List<RoomInfoVO> roomInfoVOs = roomInfoMapper.getRoomInfoList(room.getRoomId());
				Map<String,Object> data = new HashMap<>();
				data.put("room",room);
				data.put("list",roomInfoVOs);
				jo.put("data",data);
				return jo;
			} else {
				JSONObject jo = JSONUtils.WebErrorResult( "已存在其他房间，与加入房间号不匹配", JSONUtils.ErrorCode.JOINROOMHASOTHER );
				List<RoomInfoVO> roomInfoVOs = roomInfoMapper.getRoomInfoList(roomInfo.getRoomId());
				room = roomMapper.getRoom(roomInfo.getRoomId());
				Map<String,Object> data = new HashMap<>();
				data.put("room",room);
				data.put("list",roomInfoVOs);
				jo.put("data",data);
				return jo;
			}
		}


		if(room.getNum() >= WSConstant.ROOM_NUM_LIMIT){
			JSONObject jo = JSONUtils.WebErrorResult( WSConstant.ROOM_FULL, JSONUtils.ErrorCode.JOINFAIL );
			return jo;
		}

		if(!room.getDepartment().equals(employee.getDepartmentId())){
			JSONObject jo = JSONUtils.WebErrorResult( "请加入自己部门房间", JSONUtils.ErrorCode.JOINFAIL );
			return jo;
		}



		//查询当前房间所有记录
		List<RoomInfoVO> list = roomInfoMapper.getRoomInfoList(room.getRoomId());
		if(list == null)
		{
			list = new ArrayList<>();
		} else {
			if(list.size() >= WSConstant.ROOM_NUM_LIMIT){
				//房间已满
				JSONObject jo = JSONUtils.WebErrorResult( WSConstant.ROOM_FULL, JSONUtils.ErrorCode.JOINFAIL );
				return jo;
			}
		}

		//添加进入房间记录
		RoomInfo insert = new RoomInfo();
		insert.setRoomId(room.getRoomId());
		insert.setEmployeeId(employee.getEmployeeId());
		roomInfoMapper.insertInfo(insert);

		//判断添加记录是否成功
		if(insert.getId() == null){
			JSONObject jo = JSONUtils.WebErrorResult( WSConstant.JOIN_FAIL, JSONUtils.ErrorCode.JOINFAIL );
			return jo;
		}
		//房间人数加1
		roomMapper.increaseRoomNum(room.getRoomId());

		list = roomInfoMapper.getRoomInfoList(room.getRoomId());

		JSONObject jo = JSONUtils.WebResult("成功" );

		//完整信息
		Map<String,Object> data = new HashMap<>();
		data.put("room",room);
		data.put("list",list);
		jo.put("data",data);

		//获取完整加入信息
		RoomInfoVO notifyInfo = roomInfoMapper.getRoomInfoVo(insert.getId());
		JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_JOIN_ROOM,"" , JSONUtils.Code.JOIN);
		jo_notify.put("data",notifyInfo);

		sendRoomNotifyByWeb(jo_notify,room.getRoomId(),employee.getEmployeeId(), false);

		return jo;
	}

	@Override
	public JSONObject webLeaveRoom(Integer employeeId,Integer roomId) {
		//判断房间是否在游戏中
		//todo
		RoomInfo roomInfo = roomInfoMapper.getRoomInfo(employeeId);

		if(roomInfo == null){
			JSONObject jo = JSONUtils.WebErrorResult(WSConstant.LEAVE_FAIL, JSONUtils.ErrorCode.LEAVEFAIL );
			return jo;
		}

		if(!roomInfo.getRoomId().equals(roomId)){
			JSONObject jo = JSONUtils.WebErrorResult("房间号错误", JSONUtils.ErrorCode.LEAVEFAIL );
			return jo;
		}

		//判断是否是房主
		Room room = roomMapper.getRoomByOwnerId(employeeId);

		logger.info("room: {}",room);

		if ( room != null ) {
			if(room.getStatus().equals(3)){
				JSONObject jo = JSONUtils.WebErrorResult("对战中不可离开", JSONUtils.ErrorCode.LEAVEFAIL );
				return jo;
			}
			if(room.getRoomId().equals(roomId) ){
//				List<RoomInfoVO> roomInfoVOList = roomInfoMapper.getRoomInfoList(roomId);
				//让其他人退出房间
				JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_ROOT_LEAVE_ROOM, "房主离开房间", JSONUtils.Code.ROOTLEAVE);
				sendRoomNotifyByWeb(jo_notify,roomId,employeeId, false);

				//删除其他人房间记录
				roomInfoMapper.deleteRoomInfoByRoomId(room.getRoomId());
				//房主离开房间
				roomMapper.leaveRoom(employeeId);

			} else {
				JSONObject jo = JSONUtils.WebErrorResult("房间出错", JSONUtils.ErrorCode.LEAVEFAIL );
				return jo;
			}

		} else {

			//删除房间进入信息
			roomInfoMapper.deleteRoomInfo(employeeId);
			//房间人数减一
			roomMapper.decreaseRoomNum(roomInfo.getRoomId());

			JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_LEAVE_ROOM, "有用户离开房间", JSONUtils.Code.LEAVE);
			jo_notify.put("data",roomInfo);
			sendRoomNotifyByWeb(jo_notify,roomInfo.getRoomId(),employeeId, false);

		}

		JSONObject jo = JSONUtils.WebResult("成功");
		return jo;
	}

	@Override
	public JSONObject webKick(Integer employeeId,Integer roomId, Integer kickId) {
		//判断是否是房主
		Room room = roomMapper.getRoomByOwnerId(employeeId);

		if ( room != null ) {
			if(room.getStatus().equals(1) && room.getRoomId().equals(roomId)){
				//判断是否存在被踢用户
				RoomInfo roomInfo = roomInfoMapper.getRoomInfo(kickId);
				if(roomInfo == null){
					JSONObject jo = JSONUtils.WebErrorResult("不存在此用户", JSONUtils.ErrorCode.KICKFAIL );
					return jo;
				}

				JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_LEAVE_ROOM, "有用户离开房间", JSONUtils.Code.LEAVE);
				jo_notify.put("data",roomInfo);
				//全部推送
				sendRoomNotifyByWeb(jo_notify,roomInfo.getRoomId(),1, true);

				//删除房间进入信息
				roomInfoMapper.deleteRoomInfoById(roomInfo.getId());
				//房间人数减一
				roomMapper.decreaseRoomNum(roomInfo.getRoomId());

				JSONObject jo = JSONUtils.WebResult("成功");
				return jo;

			} else {
				JSONObject jo = JSONUtils.WebErrorResult("参数错误", JSONUtils.ErrorCode.KICKFAIL );
				return jo;
			}
		} else {
			JSONObject jo = JSONUtils.WebErrorResult("不存在房间", JSONUtils.ErrorCode.KICKFAIL );
			return jo;
		}

	}

	private void sendRoomNotify(JSONObject jo_notify,int room_id, Client client, boolean includeSelf){
		List<RoomInfoVO> list = roomInfoMapper.getRoomInfoList(room_id);
		List<Integer> ids = new ArrayList<>();
		//发给在房间的其他人
		for (RoomInfoVO info : list) {
			if (!includeSelf && info.getEmployeeId() != client.getId()) {
				ids.add(info.getEmployeeId());
			}
		}
		if (ids.size() > 0) {
			socketServer.sendJoMessageToMany(jo_notify, ids);
		}
	}

	private void sendRoomNotifyByWeb(JSONObject jo_notify,int room_id,int id, boolean includeSelf){
		List<RoomInfoVO> list = roomInfoMapper.getRoomInfoList(room_id);
		List<Integer> ids = new ArrayList<>();
		//发给在房间的其他人
		for (RoomInfoVO info : list) {
//			if (!includeSelf || info.getEmployeeId() != id) {
				ids.add(info.getEmployeeId());
//			}
		}
		if (ids.size() > 0) {
			socketServer.sendJoMessageToMany(jo_notify, ids);
		}
	}

	public JSONObject webMatch(Integer employeeId,Integer roomId){

		Room room = roomMapper.getRoomByOwnerId(employeeId);

		if(room == null){
			JSONObject jo = JSONUtils.WebErrorResult( "房间不存在", JSONUtils.ErrorCode.MATCHFAIL );
			return jo;
		}

		if(room.getStatus() != WSConstant.ROOM_STATUS_READY ){
			JSONObject jo = JSONUtils.WebErrorResult( "房间不可以匹配", JSONUtils.ErrorCode.MATCHFAIL );
			return jo;
		}

		//检查房间人数
		//todo
//		if(room.getNum() != WSConstant.ROOM_NUM_LIMIT){
//			JSONObject jo = JSONUtils.WebErrorResult( "匹配失败，人数不足", JSONUtils.ErrorCode.MATCHFAIL );
//			return jo;
//		}

		roomMapper.match(room.getRoomId());
		JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_MATCH, "开始匹配",  JSONUtils.Code.MATCH);
		jo_notify.put("data",room);
		sendRoomNotifyByWeb(jo_notify,room.getRoomId(),employeeId, false);

		JSONObject jo = JSONUtils.WebResult("成功");
		return jo;

	}


	public JSONObject webCancelMatch(Integer employeeId,Integer roomId){

		Room room = roomMapper.getRoomByOwnerId(employeeId);

		if(room.getStatus() != WSConstant.ROOM_STATUS_MATCH ){
			JSONObject jo = JSONUtils.WebErrorResult("取消匹配失败", JSONUtils.ErrorCode.MATCHFAIL );
			return jo;
		}

		roomMapper.cancelMatch(room.getRoomId());

		JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_CANCEL_MATCH, "取消匹配", JSONUtils.Code.CANCELMATCH);
		jo_notify.put("data",room);
		sendRoomNotifyByWeb(jo_notify,room.getRoomId(),employeeId, false);

		JSONObject jo = JSONUtils.WebResult("成功");
		return jo;
	}




	public JSONObject getBankList(Integer employeeId,Integer roomId, Integer matchId){

		Room room = roomMapper.getRoom(roomId);

		if(room == null){
			JSONObject jo = JSONUtils.WebErrorResult( "题目获取失败", JSONUtils.ErrorCode.BANKFAIL );
			return jo;
		}

		if(room.getStatus() != WSConstant.ROOM_STATUS_FIGHT){
			JSONObject jo = JSONUtils.WebErrorResult( "题目获取失败", JSONUtils.ErrorCode.BANKFAIL );
			return jo;
		}

		Integer id = aibMapper.getRandomItemBankId();

		List<Integer> notIds = new ArrayList<>();
		notIds.add(id);

		for (int i = 1; i < WSConstant.QUESTION_NUM ; i++) {
			id = aibMapper.getRandomItemBankIdNotInIds(notIds);
			notIds.add(id);
		}

		List<ActivityItemBankVO> list = aibMapper.getRandomItemBank(notIds);

		JSONObject jo = JSONUtils.WebResult("成功");
		jo.put("data",list);
		return jo;
	}

	public JSONObject bankAnswer(Integer employeeId, ItemFightAnswerDTO ifaDTO){

		RoomInfo roomInfo = roomInfoMapper.getRoomInfo(employeeId);

		MatchLog matchLog = matchLogMapper.getMatch(ifaDTO.getMatchId());

		if(roomInfo == null || matchLog == null){
			JSONObject jo = JSONUtils.WebErrorResult(WSConstant.ANSWER_FAIL, JSONUtils.ErrorCode.ANSWERFAIL );
			return jo;
		}

//		获取正确答案
		List<Answer> realAnswer = answerMapper.getAnswerListByItembankId(ifaDTO.getItemBankId());

		List<Integer> realIds = new ArrayList<>();
		for (Answer answer : realAnswer) {
			logger.info("as:{}",answer);
			if(answer.getAnswerStatus().equals("Y")){
				realIds.add(answer.getAnswerId());
			}
		}

		logger.info("real ids:{}",realIds);
		logger.info("ids:{}",ifaDTO.getIbAnswerList());

		//判断list是否值相等
		int Status = 0;
		if(!ifaDTO.getIbAnswerList().isEmpty()
				&& realIds.containsAll(ifaDTO.getIbAnswerList())
				&& ifaDTO.getIbAnswerList().containsAll(realIds)){
			Status = 1;
		}

		//添加一条回答记录
		FightLog fightLog = new FightLog();
		if(ifaDTO.getAnswerUseTime() >= 60000 ){
			fightLog.setAnswerTime(60000);
		} else if(ifaDTO.getAnswerUseTime() < 1) {
			fightLog.setAnswerTime(1);
		} else {
			fightLog.setAnswerTime(ifaDTO.getAnswerUseTime());
		}
		fightLog.setEmployeeId(employeeId);
		fightLog.setItemBankId(ifaDTO.getItemBankId());
		fightLog.setMatchId(ifaDTO.getMatchId());
		fightLog.setRoomId(ifaDTO.getRoomId());
		fightLog.setStatus(Status);
		fightLogMapper.createFightLog(fightLog);

		if(fightLog.getFightId() != null){
			JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_GET_SCORE, "获得积分",  JSONUtils.Code.MATCH);
			jo_notify.put("data",fightLog);
			sendRoomNotifyByWeb(jo_notify,matchLog.getRoomA(),employeeId, false);
			sendRoomNotifyByWeb(jo_notify,matchLog.getRoomB(),employeeId, false);
			JSONObject jo = JSONUtils.WebResult("成功" );
			jo.put("data",fightLog);

			matchLogMapper.increaseSubmitNum(matchLog.getMatchId());

			if(matchLog.getSubmitNum().equals(79)){
				matchComplete(matchLog,true);
			}

			return jo;
		} else {
			JSONObject jo = JSONUtils.WebErrorResult(WSConstant.ANSWER_FAIL, JSONUtils.ErrorCode.ANSWERFAIL );
			return jo;
		}

	}

	@Override
	public void matchComplete(MatchLog matchLog, boolean isScheduled){

		logger.info("**************完成比赛请求" );

		if( !isScheduled ){
			matchLog = matchLogMapper.getMatch(matchLog.getMatchId());
			if(matchLog == null){
				logger.info("未找到match log");
				return;
			}


			if(matchLog.getSubmitNum() < WSConstant.TOTAL_QUESTION){
				logger.info("提交次数不符合要求" );
				//提交未达到对战结束要求
				return;
			}
		}

		//通过log统计所有参赛人员比分
		List<FightLog> fightLogs = fightLogMapper.getFightLogByMatchId(matchLog.getMatchId());
		Integer roomAScore = 0;
		Integer roomBScore = 0;
		Integer roomATime = 0;
		Integer roomBTime = 0;

		Map<String,Integer> RoomDetals =  new HashMap<String,Integer>();
//		Map<String,Integer> RoomADetals =  new HashMap<String,Integer>();
//		Map<String,Integer> RoomBDetals =  new HashMap<String,Integer>();

		logger.info("fightLogs ： {}",fightLogs );

		for (FightLog fightLog : fightLogs){
			if(fightLog.getRoomId().equals(matchLog.getRoomA())){
				//roomA
				if(!RoomDetals.containsKey(Integer.toString(fightLog.getEmployeeId()))){
					RoomDetals.put(Integer.toString(fightLog.getEmployeeId()),0);
				}
				roomATime = roomATime + fightLog.getAnswerTime();
				if(fightLog.getStatus().equals(1)){
					logger.info("roomA 计分 ：fightlog {}", fightLog );
					roomAScore = roomAScore + 1;
					RoomDetals.put(Integer.toString(fightLog.getEmployeeId()),(RoomDetals.get(Integer.toString(fightLog.getEmployeeId())) + 1) );
				}
			} else {
				//roomB
				if(!RoomDetals.containsKey(Integer.toString(fightLog.getEmployeeId()))){
					RoomDetals.put(Integer.toString(fightLog.getEmployeeId()),0);
				}
				roomBTime = roomBTime + fightLog.getAnswerTime();
				if(fightLog.getStatus().equals(1)){
					logger.info("roomb 计分 ：fightlog {}", fightLog );
					roomBScore = roomBScore + 1;
					RoomDetals.put(Integer.toString(fightLog.getEmployeeId()), (RoomDetals.get(Integer.toString(fightLog.getEmployeeId())) + 1) );
				}
			}
		}



//		Room room = roomMapper.getRoom(matchLog.getRoomA());

		boolean isAWin = false;

		if(roomAScore > roomBScore){
			// A win
			isAWin = true;
		} else if (roomAScore < roomBScore){
			// B win
			isAWin = false;
		} else {
			if(roomATime > roomBTime){
				isAWin = false;
			} else {
				isAWin = true;
			}
		}

		Room roomA = roomMapper.getRoom(matchLog.getRoomA());
		Room roomB = roomMapper.getRoom(matchLog.getRoomB());

		if(roomA == null || roomB == null){
			logger.info("room 信息未取到 match log ： {}", matchLog );
			return;
		}

		boolean hasWinScore = false;
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
		String create = dateFormat.format(date);
		DepartmentScoreLog departmentScoreLog = new DepartmentScoreLog();
		if(isAWin){
			if(roomA.getType().equals(WSConstant.ROOM_FIGHT)) {
				departmentScoreLog.setDepartmentId(roomA.getDepartment());
				departmentScoreLog.setCreateTime(create);
				int count = departmentScoreLogMapper.getCount(departmentScoreLog);
				logger.info("count: {} depart: {}",count, roomA.getDepartment());
				if(count < WSConstant.DAILY_FIGHT_TIMES){
					hasWinScore = true;
					//win log
					departmentScoreLog = new DepartmentScoreLog();
					departmentScoreLog.setMatchId(matchLog.getMatchId());
					departmentScoreLog.setRoomId(matchLog.getRoomA());
					departmentScoreLog.setScore(WSConstant.WIN_SCORE);
					departmentScoreLog.setDepartmentId(roomA.getDepartment());
					departmentScoreLogMapper.createLog(departmentScoreLog);
					DepartmentScore departmentScore = departmentScoreMapper.getInfo(roomA.getDepartment());
					if (departmentScore == null) {
						departmentScore = new DepartmentScore();
						departmentScore.setDepartmentId(roomA.getDepartment());
						departmentScore.setScore(WSConstant.WIN_SCORE);
						departmentScore.setTimes(roomAScore);
						departmentScoreMapper.createLog(departmentScore);
					} else {
						departmentScore.setScore(WSConstant.WIN_SCORE);
						departmentScore.setTimes(roomAScore);
						departmentScoreMapper.addScore(departmentScore);
					}
				}

				departmentScoreLog.setDepartmentId(roomB.getDepartment());
				departmentScoreLog.setCreateTime(create);
				count = departmentScoreLogMapper.getCount(departmentScoreLog);

				if(count < WSConstant.DAILY_FIGHT_TIMES) {
					//lose log
					departmentScoreLog = new DepartmentScoreLog();
					departmentScoreLog.setMatchId(matchLog.getMatchId());
					departmentScoreLog.setRoomId(matchLog.getRoomB());
					departmentScoreLog.setScore(WSConstant.LOSE_SCORE);
					departmentScoreLog.setDepartmentId(roomB.getDepartment());
					departmentScoreLogMapper.createLog(departmentScoreLog);
				}

			}

			JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_MATCH_COMPLETE, "比赛完成",  JSONUtils.Code.MATCHCOMPLETE);
			Map<String,Object> data = new HashMap<>();
			Map<String,Object> match = new HashMap<>();
			match.put("status","win");
			match.put("scoreType",roomA.getType());
			if(	hasWinScore == true){
				match.put("score",WSConstant.WIN_SCORE);
			} else {
				match.put("score",0);
			}
			data.put("info",RoomDetals);
			data.put("match",match);
			jo_notify.put("data",data);
			sendRoomNotifyByWeb(jo_notify,matchLog.getRoomA(),1, false);
			jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_MATCH_COMPLETE, "比赛完成",  JSONUtils.Code.MATCHCOMPLETE);
			data = new HashMap<>();
			match = new HashMap<>();
			match.put("status","lose");
			match.put("scoreType",roomB.getType());
			match.put("score",WSConstant.LOSE_SCORE);
			data.put("match",match);
			data.put("info",RoomDetals);
			jo_notify.put("data",data);
			sendRoomNotifyByWeb(jo_notify,matchLog.getRoomB(),1, false);
		} else {
			if(roomB.getType().equals(WSConstant.ROOM_FIGHT)){

				departmentScoreLog.setDepartmentId(roomB.getDepartment());
				departmentScoreLog.setCreateTime(create);
				int count = departmentScoreLogMapper.getCount(departmentScoreLog);
				logger.info("count: {} depart: {}",count, roomB.getDepartment());

				if(count < WSConstant.DAILY_FIGHT_TIMES) {
					hasWinScore = true;

					departmentScoreLog = new DepartmentScoreLog();
					departmentScoreLog.setMatchId(matchLog.getMatchId());
					departmentScoreLog.setRoomId(matchLog.getRoomB());
					departmentScoreLog.setScore(WSConstant.WIN_SCORE);
					departmentScoreLog.setDepartmentId(roomB.getDepartment());
					departmentScoreLogMapper.createLog(departmentScoreLog);

					DepartmentScore departmentScore = departmentScoreMapper.getInfo(roomB.getDepartment());
					if (departmentScore == null) {
						departmentScore = new DepartmentScore();
						departmentScore.setDepartmentId(roomB.getDepartment());
						departmentScore.setScore(WSConstant.WIN_SCORE);
						departmentScore.setTimes(roomBScore);
						departmentScoreMapper.createLog(departmentScore);
					} else {
						departmentScore.setScore(WSConstant.WIN_SCORE);
						departmentScore.setTimes(roomBScore);
						departmentScoreMapper.addScore(departmentScore);
					}
				}

				departmentScoreLog.setDepartmentId(roomA.getDepartment());
				departmentScoreLog.setCreateTime(create);
				count = departmentScoreLogMapper.getCount(departmentScoreLog);

				if(count < WSConstant.DAILY_FIGHT_TIMES) {
					//lose log
					departmentScoreLog = new DepartmentScoreLog();
					departmentScoreLog.setMatchId(matchLog.getMatchId());
					departmentScoreLog.setRoomId(matchLog.getRoomA());
					departmentScoreLog.setScore(WSConstant.LOSE_SCORE);
					departmentScoreLog.setDepartmentId(roomA.getDepartment());
					departmentScoreLogMapper.createLog(departmentScoreLog);
				}

			}
			JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_MATCH_COMPLETE, "比赛完成",  JSONUtils.Code.MATCHCOMPLETE);
			Map<String,Object> data = new HashMap<>();
			Map<String,Object> match = new HashMap<>();
			match.put("status","lose");
			match.put("scoreType",roomA.getType());
			match.put("score",WSConstant.LOSE_SCORE);
			data.put("info",RoomDetals);
			data.put("match",match);
			jo_notify.put("data",data);
			sendRoomNotifyByWeb(jo_notify,matchLog.getRoomA(),1, false);
			jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_MATCH_COMPLETE, "比赛完成",  JSONUtils.Code.MATCHCOMPLETE);
			data = new HashMap<>();
			match = new HashMap<>();
			match.put("status","win");
			match.put("scoreType",roomB.getType());
			if(	hasWinScore == true){
				match.put("score",WSConstant.WIN_SCORE);
			} else {
				match.put("score",0);
			}
			data.put("match",match);
			data.put("info",RoomDetals);
			jo_notify.put("data",data);
			sendRoomNotifyByWeb(jo_notify,matchLog.getRoomB(),1, false);
		}

		//修改match log 状态 并且把房间置位初始状态
		matchLogMapper.finishMatch(matchLog.getMatchId());
		roomMapper.cancelMatch(matchLog.getRoomA());
		roomMapper.cancelMatch(matchLog.getRoomB());

	}

	@Override
	public JSONObject getRank() {
		List<DepartmentScoreListVO> departmentScoreListVOList = departmentScoreMapper.getList(10);
		JSONObject jo = JSONUtils.WebResult("成功");
		jo.put("data",departmentScoreListVOList);
		return jo;
	}

	@Override
	public JSONObject getSelfRank(Employee employee ) {
		List<DepartmentScoreListVO> departmentScoreListVOList = departmentScoreMapper.getList(100);

		int rankId = 1;
		int score = 0;

		for ( DepartmentScoreListVO departmentScoreListVO: departmentScoreListVOList ){
			if(departmentScoreListVO.getDepartmentId().equals(employee.getDepartmentId())){
				score = departmentScoreListVO.getScore().intValue();
				break;
			} else {
				rankId++;
			}
		}

		DepartmentInfoVO departmentInfoVO = departmentMapper.getDepartmentInfo(employee.getDepartmentId());

		JSONObject jo = JSONUtils.WebResult("成功");

		Map<String,Object> data = new HashMap<>();
		data.put("rank",rankId);
		data.put("score",score);
		data.put("dapartmentName",departmentInfoVO.getDepartmentName());

		jo.put("data",data);
		return jo;
	}

}
