package com.dh.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "对战房间")
public class MatchLog {
	
    private Integer MatchId;

    private Integer RoomA;

    private Integer RoomB;

    private Integer Status;

    private Integer submitNum;

    private Date CreateTime;

    public Integer getMatchId() {
        return MatchId;
    }

    public void setMatchId(Integer MatchId){this.MatchId = MatchId;}

    public Integer getRoomA() {
        return RoomA;
    }

    public void setRoomA(Integer RoomA){this.RoomA = RoomA;}

    public Integer getRoomB() {
        return RoomB;
    }

    public void setRoomB(Integer RoomB){this.RoomB = RoomB;}

    public Integer getStatus(){ return Status;}

    public void setStatus() {this.Status = Status;}

    public Integer getSubmitNum(){ return submitNum;}

    public void setSubmitNum() {this.submitNum = submitNum;}

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime){this.CreateTime = CreateTime;}

}