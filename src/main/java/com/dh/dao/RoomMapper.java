package com.dh.dao;

import org.apache.ibatis.annotations.Param;
import com.dh.model.Room;

import java.util.List;

public interface RoomMapper {

	Room getRoomStatus(Integer RoomId);

	Room getRoom(Integer RoomId);

	Room getRoomByOwnerId(Integer OwnerId);

	Room getRoomByDepartment(Integer Department);

	int createRoom(Room room);

	int deleteRoomById(Integer RoomId);

	int leaveRoom(Integer OwnerId);

	int match(Integer RoomId);

	int cancelMatch(Integer RoomId);

	List<Room> getPracticeRoom(Integer Limit);

	List<Room> getFightRoom(Integer Limit);

	int startFight(List<Integer> ids);

	void decreaseRoomNum(Integer RoomId);

	void increaseRoomNum(Integer RoomId);

}