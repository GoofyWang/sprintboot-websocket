package com.dh.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "对战加分记录")
public class DepartmentScoreLog {

    private Integer Id;

    private Integer MatchId;

    private Integer RoomId;

    private Integer DepartmentId;

    private Integer Score;

    private String CreateTime;

    public Integer getId(){ return Id; }

    public void setId(Integer Id){this.Id = Id;}

    public Integer getMatchId() {
        return MatchId;
    }

    public void setMatchId(Integer MatchId){this.MatchId = MatchId;}

    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer DepartmentId){this.DepartmentId = DepartmentId;}

    public Integer getRoomId() {
        return RoomId;
    }

    public void setRoomId(Integer RoomId){this.RoomId = RoomId;}

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer Score){this.Score = Score;}

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime){this.CreateTime = CreateTime;}

}