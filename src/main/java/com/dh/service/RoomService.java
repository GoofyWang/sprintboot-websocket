package com.dh.service;

import com.dh.entity.Client;
import com.alibaba.fastjson.JSONObject;
import com.dh.model.Employee;
import com.dh.model.MatchLog;
import com.dh.model.dto.ItemFightAnswerDTO;

public interface RoomService {

	JSONObject webCreateRoom(Employee employee, Integer type);

	JSONObject webJoinRoom(Employee employee,Integer roomId);

	JSONObject webLeaveRoom(Integer employeeId,Integer roomId);

	JSONObject webKick(Integer employeeId,Integer roomId,Integer kickId);

	JSONObject webMatch(Integer employeeId,Integer roomId);

	JSONObject webCancelMatch(Integer employeeId,Integer roomId);

	JSONObject getBankList(Integer employeeId,Integer roomId, Integer matchId);

	JSONObject bankAnswer(Integer employeeId, ItemFightAnswerDTO ifaDTO);

	JSONObject getRank();

	JSONObject getSelfRank(Employee employee);

	JSONObject webGetRoomStatus(Integer roomId);


	void matchComplete(MatchLog matchLog, boolean isScheduled);

}
