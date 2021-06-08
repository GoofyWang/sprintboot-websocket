package com.dh.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "对战答题记录")
public class FightLog {

    private Integer FightId;

    private Integer MatchId;

    private Integer RoomId;

    private Integer EmployeeId;

    private Integer ItemBankId;

    private Integer AnswerTime;

    private Integer Status;

    private Date CreateTime;

    public Integer getFightId() {
        return FightId;
    }

    public void setFightId(Integer FightId){this.FightId = FightId;}

    public Integer getMatchId() {
        return MatchId;
    }

    public void setMatchId(Integer MatchId){this.MatchId = MatchId;}

    public Integer getRoomId() {
        return RoomId;
    }

    public void setRoomId(Integer RoomId){this.RoomId = RoomId;}

    public Integer getItemBankId() {
        return ItemBankId;
    }

    public void setItemBankId(Integer ItemBankId){this.ItemBankId = ItemBankId;}

    public Integer getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(Integer EmployeeId){this.EmployeeId = EmployeeId;}

        public Integer getAnswerTime() {
        return AnswerTime;
    }

    public void setAnswerTime(Integer AnswerTime){this.AnswerTime = AnswerTime;}

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status){this.Status = Status;}

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime){this.CreateTime = CreateTime;}

}