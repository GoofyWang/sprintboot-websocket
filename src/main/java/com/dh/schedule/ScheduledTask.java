package com.dh.schedule;
import com.dh.constant.WSConstant;
import com.dh.dao.MatchLogMapper;
import com.dh.dao.RoomMapper;
import com.dh.model.vo.RoomInfoVO;
import com.dh.model.MatchLog;
import com.dh.model.Room;
import com.dh.server.SocketServer;
import com.dh.dao.RoomInfoMapper;
import com.dh.service.RoomService;
import com.dh.util.JSONUtils;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private MatchLogMapper matchLogMapper;

    @Autowired
    private SocketServer socketServer;

    @Autowired
    private RoomService roomService;

//    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Scheduled(fixedDelay= 5000)
    public void findPracticeMatch() throws InterruptedException {
//        return;
        logger.info("开始匹配练习场");
        List<Room> roomList = roomMapper.getPracticeRoom(100);
        match(roomList);

    }

    @Scheduled(fixedDelay= 5000)
    public void findFightMatch() throws InterruptedException {
//        return;
        logger.info("开始匹配普通场");
        List<Room> roomList = roomMapper.getFightRoom(100);
        match(roomList);
    }

    @Scheduled(fixedDelay= 5000)
    public void findCompletedMatch() throws InterruptedException {
        logger.info("查找完成的比赛");
        List<MatchLog> matchLogs = matchLogMapper.getMatchShouldCompleted(100);
        for(MatchLog matchLog : matchLogs){
            roomService.matchComplete(matchLog, true);
        }

    }


    private void match(List<Room> roomList){
        if(roomList.size() <= 1){
            logger.info("目前匹配数量不足");
        } else {
            List<Integer> temp= new ArrayList<>();

            for(Room room: roomList ){
                logger.info("temp size: {}" , temp.size());
                if( (new Integer(temp.size())).equals(1) ){
                    temp.add(room.getRoomId());
                    logger.info("找到两个id");
                    //找到两个队伍进行对战
                    //更改房间状态
                    roomMapper.startFight(temp);

                    MatchLog matchLog = new MatchLog();
                    for(Integer room_id: temp ){
                        if(matchLog.getRoomA() == null){
                            matchLog.setRoomA(room_id);
                        } else {
                            matchLog.setRoomB(room_id);
                        }
                    }
                    logger.info("匹配房间 {} - {} ",matchLog.getRoomA(),matchLog.getRoomB());
                    matchLogMapper.createMatch(matchLog);

                    if(matchLog.getMatchId() != null){
                        //分发推送消息，开始对战
                        List<RoomInfoVO> roomInfoVOS = roomInfoMapper.getRoomInfoListByRoomIds(temp);
                        JSONObject jo_notify = JSONUtils.WSNotifyResult(WSConstant.RETURN_START_FIGHT,"开始对战", JSONUtils.Code.FIGHT );
                        HashMap<Object,Object> map = new HashMap<Object,Object>();
                        map.put("matchId",matchLog.getMatchId());
                        map.put("list",roomInfoVOS);
                        jo_notify.put("data",map);

                        sendRoomNotify(jo_notify,matchLog.getRoomA());
                        sendRoomNotify(jo_notify,matchLog.getRoomB());
                    } else {
                        //回滚房间状态 todo
                        logger.info("匹配房间失败 {} - {} ",matchLog.getRoomA(),matchLog.getRoomB());
                    }
                    temp = new ArrayList<>();
                } else {
                    logger.info("添加匹配id:{}" , room.getRoomId() );
                    temp.add(room.getRoomId());
                }
            }

        }
    }

    private void sendRoomNotify(JSONObject jo_notify, int room_id){
        List<RoomInfoVO> list = roomInfoMapper.getRoomInfoList(room_id);
        List<Integer> ids = new ArrayList<>();
        //发给在房间的其他人
        for (RoomInfoVO info : list) {
            ids.add(info.getEmployeeId());
        }
        if (ids.size() > 0) {
            socketServer.sendJoMessageToMany(jo_notify, ids);
        }

    }




}
