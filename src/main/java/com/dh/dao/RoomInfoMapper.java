package com.dh.dao;

import com.dh.model.vo.RoomInfoVO;
import com.dh.model.RoomInfo;
import java.util.List;

public interface RoomInfoMapper {

	RoomInfo getRoomInfo(Integer EmployeeId);

	List<RoomInfoVO> getRoomInfoList(Integer RoomId);

	List<RoomInfoVO> getRoomInfoListByRoomIds(List<Integer> Ids);

	RoomInfoVO	getRoomInfoVo(Integer Id);

	int insertInfo(RoomInfo roomInfo);

	void deleteRoomInfo(Integer EmployeeId);

	void deleteRoomInfoByRoomId(Integer RoomId);

	void deleteRoomInfoById(Integer id);


}